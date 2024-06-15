package com.ems.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ems.dto.AssignManagerRequestDTO;
import com.ems.dto.AssignRequestDTO;
import com.ems.dto.EmailDTO;
import com.ems.dto.ProjectDTO;
import com.ems.error.ResourceNotFoundException;
import com.ems.model.Employee;
import com.ems.model.Project;
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
		return new RedirectView("/adminfiles/html/adminhome.html");
	}
     

        @PostMapping("/registerEmp")
        public ResponseEntity<String> registerEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("address") String address,
            @RequestParam("phone") String phone,
            @RequestParam("gender") String gender,
            @RequestParam("role") String role,
            @RequestParam("dob") String dob,
            @RequestParam("skills") Set<String> skills
        ) {
            String password = empService.createPassword();
            Set<Skill> st = skillService.getSkillSet(skills);

            Employee emp = new Employee(firstName, lastName, email, address, phone, gender, role, password, dob, st);
            String text = "Hello! " + firstName + " " + lastName + ",\n\n" +
                          "Congratulations! You have successfully registered.\n\n" +
                          "Your account details are as follows:\n" +
                          "Username: " + email + "\n" +
                          "Password: " + password + "\n\n" +
                          "Thank you for joining our team!";
            try {
            	
                emp.setPassword(passwordEncoder.encode(password));
                empService.createEmp(emp);

                emailService.sendMail("iadityapatel1729@gmail.com", email, text);
                //emailService.sendMail("iadityapatel1729@gmail.com", "adityapatel91221@gmail.com", text);
                System.out.println("Email sent successfully");

            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.badRequest().body("Email already exists. Please use a different email.");
            }

            return ResponseEntity.ok("Employee registered successfully.");
        }
    
	 /*
	 @DeleteMapping("/deleteEmp")
	 public Employee deleteEmp(@RequestBody EmailDTO emailDTO) {
		 	System.out.println(1);
		 	System.out.println(emailDTO.getEmail());
		 	Employee emp=empService.deleteEmp(emailDTO.getEmail());
 		 	return emp;
	 }*/
        @DeleteMapping("/deleteEmp")
        public ResponseEntity<String> deleteEmp(@RequestBody EmailDTO emailDTO) {
            System.out.println(1);
            System.out.println(emailDTO.getEmail());
            String responseMessage = empService.deleteEmp(emailDTO.getEmail());
            return ResponseEntity.ok(responseMessage);
        }
	 
	 @PostMapping("/addProject")
		 public RedirectView addProject(@RequestParam("proName") String proName) {
			 	projectService.createService(proName);
			 	return new RedirectView("/adminfiles/html/adminhome.html"); 
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
	 public ResponseEntity<String> assignManager(@RequestBody AssignManagerRequestDTO assignManagerRequest) {
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
	 /*
		
	 @PostMapping("/assignRequest")
	 public void assignRequest(@RequestBody AssignRequestDTO assignRequest) {
	empService.assignThisRequest(assignRequest.getReqId(),assignRequest.getEmpId(), assignRequest.getManagerId(), assignRequest.getProId());
	 }
	 */
	 @PostMapping("/assignRequest")
	    public ResponseEntity<String> assignRequest(@RequestBody AssignRequestDTO assignRequest) {
	        try {
	            empService.assignThisRequest(assignRequest.getReqId(), assignRequest.getEmpId(), assignRequest.getManagerId(), assignRequest.getProId());
	            return ResponseEntity.ok("Request assigned successfully");
	        } catch (ResourceNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");
	        }
	    }
	 
	  @PostMapping("/unassignRequest")
	    public ResponseEntity<String> unassignRequest(@RequestBody AssignRequestDTO assignRequest) {
	        try {
	            empService.unassignThisRequest(assignRequest.getReqId(), assignRequest.getEmpId());
	            return ResponseEntity.ok("Request unassigned successfully");
	        } catch (ResourceNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");
	        }
	    }
 
	 @PostMapping("/rejectRequest")
	    public ResponseEntity<String> rejectRequest(@RequestBody AssignRequestDTO assignRequest) {
	        try {
	            empService.rejectThisRequest(assignRequest.getReqId());
	            return ResponseEntity.ok("Request rejected successfully");
	        } catch (ResourceNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");
	        }
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
	     return new RedirectView("/adminfiles/html/adminhome.html"); 
	    }
}
	    
