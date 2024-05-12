package com.ems.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ems.model.Employee;
import com.ems.service.EmailService;
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
    
    @Autowired
    EmailService emailService;
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
	        @RequestParam("role") String role
	        //@RequestParam("skills")List<String> skills
	    )
	 {
		 Random rand=new Random();
			String capital_letters="ABCDEFGHIJKLMNOPQRSTUVWXYX";
			String small_letters="abcdefghijklmnopqrstuvwxyz";
			String numbers="0123456789";
			String values=capital_letters+small_letters+numbers;
			char pass[]=new char[6];
			for(int i=0;i<6;i++){
			pass[i]=values.charAt(rand.nextInt(values.length()));
			}
			String password="";
			for(char it:pass) {
				password+=it;
			}
			
 Employee emp=new Employee(firstName,lastName,email,address,phone,gender,role,password);
 		String text="Congratulations you are successfully registered\nUsername: "+email
 				+"Password: "+password+
 				"\n";
 			emailService.sendMail("iadityapatel1729@gmail.com",email,text);
 			System.out.println("email sended successfully");
	        emp.setPassword(passwordEncoder.encode(password));
	        empService.createEmp(emp);
	        
	        return "redirect:/adminfiles/adminhome.html"; // Redirect to the admin home page
	    }
}