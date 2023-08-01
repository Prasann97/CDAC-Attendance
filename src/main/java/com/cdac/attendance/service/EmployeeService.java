package com.cdac.attendance.service;

import java.util.List;

import com.cdac.attendance.model.Employee;
import com.cdac.attendance.model.EmployeeDto;

public interface EmployeeService {

	void saveUser(EmployeeDto userDto);

    Employee findUserByEmail(String email);
    
    void generateExcelSheet();
    
    List<EmployeeDto> findAllUsers();

}
