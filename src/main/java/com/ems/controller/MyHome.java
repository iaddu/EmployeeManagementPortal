package com.ems.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/ems")
public class MyHome {
	@GetMapping("/greet")
	public void greet() {
		System.out.println("welcommen sie frau ");
	}
}
