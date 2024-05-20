package com.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ems.model.Employee;
import com.ems.service.EmpService;

@RestController
@RequestMapping("/manager")
public class ManagerHome {
	@Autowired
	private EmpService empService;
	
	@GetMapping("/home")
	public RedirectView managerHome() {
		return new RedirectView("/managerfiles/managerHome.html");
	}

	 @GetMapping("/getAllEmployee")
	 public List<Employee> getAllEmployee(){
		 
		List<Employee> allEmployeeList= empService.getAllEmployee();
		System.out.print(allEmployeeList);
		return allEmployeeList;
	 } 
}
