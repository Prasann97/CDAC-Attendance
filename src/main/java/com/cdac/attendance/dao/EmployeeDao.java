package com.cdac.attendance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cdac.attendance.model.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

	Employee findEmployeeByEmail(String email);
	
	@Query(value="select * from employee where id not in (select employee_id from employees_roles)",nativeQuery = true)
	List<Employee> getEmployeeListForAssignRole();
}
