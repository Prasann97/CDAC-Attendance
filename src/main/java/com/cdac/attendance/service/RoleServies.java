package com.cdac.attendance.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RoleServies {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void saveEmployeeRole(Long employeeId,Long roleId) {
		jdbcTemplate.update("INSERT INTO employees_roles (employee_id, role_id) VALUES (?, ?)", employeeId,roleId);
	}

}
