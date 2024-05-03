package com.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ems.service.AdminImpl;

@Controller
@RequestMapping("/ems")
public class Home {
	private AdminImpl adminImpl;
	public Home(AdminImpl adminImpl) {
		super();
		this.adminImpl = adminImpl;
	}
	@GetMapping("/home")
	public String home() {
		return "redirect:/Home.html";
	}
	@PostMapping("/adminlogin")
	public String adminAuthenticate(@RequestParam("name") String name,
			@RequestParam("password") String password) {
		if(adminImpl.isAuthentic(name, password)==true)return "Success.html";
		return "Failure.html";
	}
}
