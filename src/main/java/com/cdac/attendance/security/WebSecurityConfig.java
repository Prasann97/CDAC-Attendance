package com.cdac.attendance.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cdac.attendance.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
//		System.err.println(passwordEncoder());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/home-page").hasAnyAuthority("MAINTAINER", "VIEWER", "ADMIN")
		.antMatchers("/search-absent-students").hasAnyAuthority("MAINTAINER", "VIEWER", "ADMIN")	
		.antMatchers("/absent-student-list").hasAnyAuthority("MAINTAINER", "VIEWER", "ADMIN")
		.antMatchers("/attendance-home-page").hasAnyAuthority("MAINTAINER", "ADMIN")
//		.antMatchers("/attendance-home-page").hasAnyAuthority("MAINTAINER", "ADMIN")
		.antMatchers("/upload-student-sheet").hasAnyAuthority("ADMIN")
		.antMatchers("/mark-attendance").hasAnyAuthority("MAINTAINER", "ADMIN")
		.antMatchers("/save-attendance").hasAnyAuthority("MAINTAINER", "ADMIN")
		.antMatchers("/registration/**").hasAuthority("ADMIN")
		.antMatchers("/role-assign").hasAuthority("ADMIN")
		
//				.antMatchers("/")
//				.permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
//				 .loginPage("/login")
				.permitAll()
				.and()
				.logout()
				.permitAll()
				.and()
				.exceptionHandling()
				.accessDeniedPage("/403");

		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
}
