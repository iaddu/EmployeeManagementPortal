package com.ems.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ems.model.Employee;
import com.ems.model.Skill;
import com.ems.service.EmailService;
import com.ems.service.EmpService;
import com.ems.service.ProjectService;
import com.ems.service.SkillService;
import com.ems.service.UserService;

@Controller
@RequestMapping("/admin")
public class Admin {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
   
	@Autowired
 	private UserService userService; 
    
    @Autowired
 	private EmpService empService;  
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private ProjectService projectService;
   
	
    @GetMapping("/home")
	public String adminHome() {
		return "redirect:/adminfiles/adminhome.html";
	}
	@Autowired
	public SkillService skillService;
	
	 @PostMapping("/registerEmp")
	    public String registerEmployee(
	        @RequestParam("firstName") String firstName,
	        @RequestParam("lastName") String lastName,
	        @RequestParam("email") String email,
	        @RequestParam("address") String address,
	        @RequestParam("phone") String phone,
	        @RequestParam("gender") String gender,
	        @RequestParam("role") String role,
	        @RequestParam("dob") String dob,
	        @RequestParam("skills")Set<String> skills
	    )
	    
	 {
		 String password=empService.createPassword();
	     Set<Skill> st=skillService.getSkillSet(skills);
         Employee emp=new Employee(firstName,lastName,email,address,phone,gender,role,password,dob,st);
 		 String text="Congratulations you are successfully registered\nUsername: "+email
 				+"Password: "+password+
 				"\n";
 			//emailService.sendMail("iadityapatel1729@gmail.com",email,text);
			emailService.sendMail("iadityapatel1729@gmail.com","adityapatel91221@gmail.com",text);
 			System.out.println("email sended successfully");
	        emp.setPassword(passwordEncoder.encode(password));
	        
	        
	        empService.createEmp(emp);
	        return "redirect:/adminfiles/adminhome.html"; // Redirect to the admin home page
	    }
	 
	 @PostMapping("/deleteEmp")
	 public String deleteEmp(@RequestParam("email") String email) {
		 	empService.deleteEmp(email);
	        return "redirect:/adminfiles/adminhome.html"; // Redirect to the admin home page
	 }
	 

	 @PostMapping("/addProject")
		 public String addProject(@RequestParam("proName") String proName) {
			 	projectService.createService(proName);
		        return "redirect:/adminfiles/adminhome.html"; // Redirect to the admin home page
		 }
		 
	 
	 }
	    
