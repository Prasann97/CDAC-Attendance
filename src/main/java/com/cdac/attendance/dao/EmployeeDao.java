package com.cdac.attendance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.attendance.model.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

	Employee findEmployeeByEmail(String email);
}
