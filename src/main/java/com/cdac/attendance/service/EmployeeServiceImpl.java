package com.cdac.attendance.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cdac.attendance.dao.EmployeeDao;
import com.cdac.attendance.model.Employee;
import com.cdac.attendance.model.EmployeeDto;
import com.cdac.attendance.model.Role;
//import com.example.attendance.dao.RolesDao;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired(required = true)
	private EmployeeDao employeeDao;
	
//	@Autowired
//	private RolesDao rolesDao;
	
	@Autowired(required = true)
	private PasswordEncoder passwordEncoder;
	
	
	
	

//	@Autowired
//    private PasswordEncoder passwordEncoder;
	
	@Override
	public void saveUser(EmployeeDto employeeDto) {
		
		
		Employee user = new Employee();
        user.setName(employeeDto.getName());
        user.setEmail(employeeDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(employeeDto.getPassword()));

//        Role role = roleDao.findByName("ROLE_ADMIN");
//        if(role == null){
//            role = checkRoleExist();
//        }
//        user.setRoles(Arrays.asList(role));
        employeeDao.save(user);
		
//		
//		 Role role = roleDao.findByName(ApplicationConstants.USER);
//
//	        if (role == null)
//	            role = roleDao.save(new Role(ApplicationConstants.USER));

//	        Employee employee = new Employee(employeeDto.getName(), employeeDto.getEmail(), passwordEncoder.encode(employeeDto.getPassword()),
//	                Arrays.asList(role));
//	        employeeDao.save(employee);
		
	}

	@Override
	public Employee findUserByEmail(String email) {
		return employeeDao.findEmployeeByEmail(email);
	}

	@Override
	public void generateExcelSheet() {

		
	}

	@Override
	public List<EmployeeDto> findAllUsers() {
		List<Employee> employeeList = employeeDao.findAll();
        return employeeList.stream()
                .map((employee) -> mapToUserDto(employee))
                .collect(Collectors.toList());
	}
	
	private EmployeeDto mapToUserDto(Employee employee){
        EmployeeDto userDto = new EmployeeDto();
        userDto.setName(employee.getName());
        userDto.setEmail(employee.getEmail());
        return userDto;
    }

//	@Override
//	public void saveUser(EmployeeDto userDto) {
//		// TODO Auto-generated method stub
//		
//	}

//    private Role checkRoleExist(){
//        Role role = new Role();
//        role.setName("ROLE_ADMIN");
//        return roleDao.save(role);
//    }

}
