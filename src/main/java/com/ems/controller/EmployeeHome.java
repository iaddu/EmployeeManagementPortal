package com.ems.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ems.model.Employee;
import com.ems.model.Project;
import com.ems.model.Skill;
import com.ems.service.AuthUtils;
import com.ems.service.EmpService;
import com.ems.service.ProjectService;
import com.ems.service.SkillService;

@RestController
@RequestMapping("/emp")
public class EmployeeHome {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthUtils authUtils;
	
	@Autowired
	private EmpService empService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private SkillService skillService;
	
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
	 public EmployeeResponse getEmployee(){
		 String email = authUtils.getLoggedInUserEmail();
		 Employee employee=null;
		 if(email!=null)
		 employee= empService.getEmployee(email);
		 String managerName=empService.getManagerName(employee.getManager());
	   	return new EmployeeResponse(employee,managerName);
	 } 
	
	 @GetMapping("/getAllProject")
	 public List<Project> getAllProject(){
		List<Project> allProjectList= projectService.getAllProject();
		return allProjectList;
	 } 
	 
	 @PostMapping("/updateSkill")
	 public void updateSkill(@RequestParam("empId") String empId,
			 @RequestParam("skills") Set<String> skills) 
	 {  
		Set<Skill> st=skillService.getSkillSet(skills);
		empService.updateSkill(empId,st);
	 }
	 
	 @PostMapping("/changePassword")
	 public void changePassword(@RequestParam("upass") String upass) {
		 System.out.println(upass);
		 String email = authUtils.getLoggedInUserEmail();
		String encodedpassword= passwordEncoder.encode(upass);
		empService.changePassword(email,encodedpassword);	
	 }
}
