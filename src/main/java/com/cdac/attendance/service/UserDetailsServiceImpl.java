package com.cdac.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cdac.attendance.dao.EmployeeDao;
import com.cdac.attendance.model.Employee;
import com.cdac.attendance.security.MyUserDetails;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee =employeeDao.findEmployeeByEmail(username);
//		System.err.println(employee);
		if(employee==null) {
			throw new UsernameNotFoundException("Could not find User");
		}
		return new MyUserDetails(employee);
	}

}
