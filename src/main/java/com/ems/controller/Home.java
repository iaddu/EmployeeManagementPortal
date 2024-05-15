package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ems.model.Skill;
import com.ems.service.SkillService;
import com.ems.service.UserService;

@Controller
@RequestMapping("/home")
public class Home {
	@Autowired
	PasswordEncoder passwordEncoder;
    @Autowired
 	UserService userService; 
    
    @Autowired
    private SkillService skillService;
    //This was created to add Admin 
     //And also at that time /ems/happy is permitted to all in SecurityConfig
	/*@GetMapping("/happy")
    public String unhappy() {
		User user=new User("adi@123","123","ADMIN");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.createUser(user);
		System.out.println("hello");
		return "redirect:/homefiles/home.html";
  }*/
    
    

}
