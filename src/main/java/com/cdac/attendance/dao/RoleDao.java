package com.cdac.attendance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cdac.attendance.model.Role;

public interface RoleDao extends JpaRepository<Role, Long>{

	@Query(value = "insert into employees_roles (employee_id,role_id) values (:employeeId,:roleId)", nativeQuery = true)
	public void saveEmployeeRole(Long employeeId, Long roleId);
}
