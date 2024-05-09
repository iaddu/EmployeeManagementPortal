package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ems.model.User;
import com.ems.service.UserService;

@Controller
@RequestMapping("/ems")
public class Home {
	@Autowired
	PasswordEncoder passwordEncoder;
    @Autowired
 	UserService userService; 
    
	@GetMapping("/happy")
    public String unhappy() {
		User user=new User("adi@123","123","ADMIN");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.createUser(user);
		System.out.println("hello");
		return "redirect:/home/home.html";
  }
}
