package com.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ems.model.Employee;
import com.ems.model.Project;
import com.ems.service.AuthUtils;
import com.ems.service.EmpService;
import com.ems.service.ProjectService;

@RestController
@RequestMapping("/emp")
public class EmployeeHome {
	
	
	@Autowired
	private AuthUtils authUtils;
	
	@Autowired
	private EmpService empService;
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping("/home")
	public RedirectView employeeHome() {
		return new RedirectView("/employeefiles/employeehome.html");
	}
	
	@GetMapping("/getAllEmployee")
	 public List<Employee> getAllEmployee(){
		List<Employee> allEmployeeList= empService.getAllEmployee();
		return allEmployeeList;
	 } 
	
	 @GetMapping("/viewEmployee")
	 public Employee getEmployee(){
		 String email = authUtils.getLoggedInUserEmail();
		 System.out.println(email);
		 Employee employee=null;
		 if(email!=null)
		 employee= empService.getEmployee(email);
	   	return employee;
	 } 
	
	 @GetMapping("/getAllProject")
	 public List<Project> getAllProject(){
		List<Project> allProjectList= projectService.getAllProject();
		return allProjectList;
	 } 
	 
	

}
