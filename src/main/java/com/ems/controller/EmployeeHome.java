package com.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/emp")
public class EmployeeHome {
	
	@GetMapping("/home")
	public String employeeHome() {
		return "redirect:/employeefiles/employeehome.html";
	}

}
