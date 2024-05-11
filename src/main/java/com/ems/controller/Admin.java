package com.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ems.model.Employee;
import com.ems.model.User;
import com.ems.service.EmpService;
import com.ems.service.UserService;

@Controller
@RequestMapping("/admin")
public class Admin {
	@Autowired
	PasswordEncoder passwordEncoder;
    @Autowired
 	UserService userService; 
    
    @Autowired
 	EmpService empService; 
    
	@GetMapping("/home")
	public String adminHome() {
		return "redirect:/adminfiles/adminhome.html";
	}
	
	 @PostMapping("/registerEmp")
	    public String registerEmployee(
	        @RequestParam("firstName") String firstName,
	        @RequestParam("lastName") String lastName,
	        @RequestParam("email") String email,
	        @RequestParam("address") String address,
	        @RequestParam("phone") String phone,
	        @RequestParam("gender") String gender,
	        @RequestParam("role") String role,
	        @RequestParam("password") String password
	        //@RequestParam("skills")List<String> skills
	    )
	 {
		 
	        Employee emp=new Employee(firstName,lastName,email,address,phone,gender,role,password);
	        emp.setPassword(passwordEncoder.encode(emp.getPassword()));
	        empService.createEmp(emp);
	     
	        return "redirect:/adminfiles/adminhome.html"; // Redirect to the admin home page
	    }
}
