package com.cdac.attendance.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdac.attendance.dao.EmployeeDao;
import com.cdac.attendance.dao.RoleDao;
import com.cdac.attendance.model.Employee;
import com.cdac.attendance.model.EmployeeDto;
import com.cdac.attendance.model.Role;
import com.cdac.attendance.service.EmployeeService;
import com.cdac.attendance.service.RoleServies;

@Controller
public class AdminController {

	@Autowired(required = true)
	private EmployeeService employeeService;
	
	@Autowired(required = true)
	private EmployeeDao employeeDao;
	
	@Autowired(required = true)
	private RoleServies roleServies;
	
	@Autowired(required = true)
	private RoleDao roleDao;
	
	@GetMapping("/registration")
    public String registrationForm(Model model) {
        EmployeeDto employee = new EmployeeDto();
        model.addAttribute("employee", employee);
        return "registration";
    }
	
	@PostMapping("/registration/save")
    public String registration(
             @ModelAttribute("employee") EmployeeDto employeeDto,
            BindingResult result,
            Model model) {
        Employee existingUser = employeeService.findUserByEmail(employeeDto.getEmail());

        if (existingUser != null)
            result.rejectValue("email", null,
                    "User already registered !!!");

        if (result.hasErrors()) {
            model.addAttribute("user", employeeDto);
            return "/registration";
        }

        employeeService.saveUser(employeeDto);
        model.addAttribute("successMessage", "Registration successful.");
        return "home-page";
    }
	
	@GetMapping("/role-assign")
    public String assignRoleToEmployee(Model model) {
		List <Employee> employeeList=employeeDao.getEmployeeListForAssignRole();
		List <Role> rolesList=roleDao.findAll();
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("rolesList", rolesList.stream().filter(x->!x.getName().equals("ADMIN")).collect(Collectors.toList()));
		return "role-assign";
    }
	
	@PostMapping("/role-assign")
    public String assignRoleToEmployee(@RequestParam("employeeId") Long employeeId,
			@RequestParam("roleId") Long roleId,Model model) {
//		System.err.println(employeeId+" , "+roleId);
		roleServies.saveEmployeeRole(employeeId,roleId);
		return "home-page";
    }
	
}
