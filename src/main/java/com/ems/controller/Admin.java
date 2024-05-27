package com.ems.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ems.model.EmailDTO;
import com.ems.model.Employee;
import com.ems.model.Project;
import com.ems.model.ProjectDTO;
import com.ems.model.Request;
import com.ems.model.Skill;
import com.ems.service.EmailService;
import com.ems.service.EmpService;
import com.ems.service.ProjectService;
import com.ems.service.RequestService;
import com.ems.service.SkillService;
import com.ems.service.UserService;

@RestController
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
   
	@Autowired
	private SkillService skillService;
	
	@Autowired
	private RequestService requestService;
	
    @GetMapping("/home")
	public RedirectView adminHome() {
		return new RedirectView("/adminfiles/adminhome.html");
	}

	
	 @PostMapping("/registerEmp")
	    public RedirectView registerEmployee(
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
	        return new RedirectView("/adminfiles/adminhome.html"); // Redirect to the admin home page
	    }
	 
	 @PostMapping("/deleteEmp")
	 public Employee deleteEmp(@RequestBody EmailDTO emailDTO) {
		 	System.out.println(1);
		 	System.out.println(emailDTO.getEmail());
		 	Employee emp=empService.deleteEmp(emailDTO.getEmail());
		 	System.out.println(emp);
		 	return emp;
	 }
	 
	 @PostMapping("/addProject")
		 public RedirectView addProject(@RequestParam("proName") String proName) {
			 	projectService.createService(proName);
			 	return new RedirectView("/adminfiles/adminhome.html"); // Redirect to the admin home page
		 }
		 
	 @GetMapping("/getUnassignedEmployee")
	 public List<Employee> getUnassignedEmployee(){
		List<Employee> unassignedEmployeeList= empService.getUnassignedEmplyee();
		return unassignedEmployeeList;
	 } 
	 
	 @GetMapping("/getAllEmployee")
	 public List<Employee> getAllEmployee(){
		List<Employee> allEmployeeList= empService.getAllEmployee();
		return allEmployeeList;
	 } 
	 
	 @GetMapping("/getAllManagerOnly")
	 public List<Employee> getAllEmployeeOnly() {
		 List<Employee> allManagerList=empService.getAllManagerOnly();
		 //System.out.println(allEmployeeList);
		 return allManagerList;
	 }
	 
	 @GetMapping("/getAllProject")
	 public List<Project> getAllProject(){
		List<Project> allProjectList= projectService.getAllProject();
		return allProjectList;
	 } 
	 
	 //single employee
	 @PostMapping("/viewEmployee")
	 public Employee getEmployee(@RequestBody EmailDTO emailDTO){
		Employee employee= empService.getEmployee(emailDTO.getEmail());
		return employee;
	 } 
	
	 @PostMapping("/viewProject")
	 public Project getProject(@RequestBody ProjectDTO projectDTO){
		Project project= projectService.getProject(projectDTO.getProId());
		return project;
	 } 
	 
	 @PostMapping("/assignManager")
	 public ResponseEntity<String> assignManager(@RequestBody AssignManagerRequest assignManagerRequest) {
	     try {
	         empService.assignManager(assignManagerRequest.getManagerId(), assignManagerRequest.getProId());
	         return ResponseEntity.ok("Manager assigned successfully!");
	     } catch (RuntimeException e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error assigning manager: " + e.getMessage());
	     }
	 }
	 
	 @GetMapping("/getAllRequest")
	 public List<Request> getAllRequest(){
     List<Request> allRequestList= requestService.getAllRequest();
		return allRequestList;
	 } 
		
	 @PostMapping("/assignRequest")
	 public void assignRequest(@RequestBody AssignRequest assignRequest) {
	empService.assignThisRequest(assignRequest.getReqId(),assignRequest.getEmpId(), assignRequest.getManagerId(), assignRequest.getProId());
	 }
	 
	 @PostMapping("/unassignRequest")
	 public void unassignRequest(@RequestBody AssignRequest assignRequest) {
	empService.unassignThisRequest(assignRequest.getReqId(),assignRequest.getEmpId());
	 }
	 
	 @PostMapping("/rejectRequest")
	 public void rejectRequest(@RequestBody AssignRequest assignRequest) {
	empService.rejectThisRequest(assignRequest.getReqId());
	 }
	 
	 @PostMapping("/updateEmp")
	    public RedirectView updateEmployee(
	    	@RequestParam("empId") String empId,
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
		 System.out.println("in the admin");
	     Set<Skill> st=skillService.getSkillSet(skills);
	   empService.updateEmp(empId,firstName,lastName,email,address,phone,gender,role,dob,st);
	        return new RedirectView("/adminfiles/adminhome.html"); // Redirect to the admin home page
	    }
}
	    
