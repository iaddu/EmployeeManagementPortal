package com.ems.controller;

import java.util.ArrayList;
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

import com.ems.dto.ProjectEmployeeDTO;
import com.ems.dto.RequestDTO;
import com.ems.model.EmailDTO;
import com.ems.model.Employee;
import com.ems.model.Project;
import com.ems.model.Request;
import com.ems.service.AuthUtils;
import com.ems.service.EmpService;
import com.ems.service.ProjectService;
import com.ems.service.RequestService;

@RestController
@RequestMapping("/manager")
public class ManagerHome {
	@Autowired
	private EmpService empService;
	
	@Autowired
	private AuthUtils authUtils;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private RequestService requestService;
	@GetMapping("/home")
	public RedirectView managerHome() {
		return new RedirectView("/managerfiles/html/managerHome.html");
	}

	 @GetMapping("/getAllEmployee")
	 public List<Employee> getAllEmployee(){
		 
		List<Employee> allEmployeeList= empService.getAllEmployee();
		//System.out.print(allEmployeeList);
		return allEmployeeList;
	 } 
	 
	 @GetMapping("/getAllEmployeeOnly")
	 public List<Employee> getAllEmployeeOnly() {
		 List<Employee> allEmployeeList=empService.getAllEmployeeOnly();
		 //System.out.println(allEmployeeList);
		 return allEmployeeList;
	 }
	 
	 @GetMapping("/getAllProject")
	 public List<Project> getAllProject(){
		List<Project> allProjectList= projectService.getAllProject();
		return allProjectList;
	 } 
	 
	 @PostMapping("/viewEmployee")
	 public Employee getEmployee(@RequestBody EmailDTO emailDTO){
		Employee employee= empService.getEmployee(emailDTO.getEmail());
		return employee;
	 } 
	 
	 @PostMapping("/requestAdmin")
	 public ResponseEntity<Void> requestAdmin(@RequestBody RequestDTO requestDTO) {
	     String email = authUtils.getLoggedInUserEmail();
	     Employee manager = empService.getEmployee(email);
	     Request request = new Request(manager.getEmpId(), requestDTO.getEmpId(), requestDTO.getProId(), "pending", requestDTO.getTo());
	     requestService.createRequest(request);
	     return ResponseEntity.ok().build();
	 }
	 
	 @PostMapping("/changePassword")
	 public void changePassword(@RequestParam("upass") String upass) {
 		String email = authUtils.getLoggedInUserEmail();
		String encodedpassword= passwordEncoder.encode(upass);
		empService.changePassword(email,encodedpassword);	
	 }
	 
	 
	 @GetMapping("/myProfile")
	 public Employee getEmployee(){
		 String email = authUtils.getLoggedInUserEmail();
		 Employee employee=null;
		 if(email!=null)
		 employee= empService.getEmployee(email);
		 System.out.println(employee);
		 return employee;
	 } 
	 
	 
	 @GetMapping("/myProject")
	 public ResponseEntity<List<ProjectEmployeeDTO>> myEmployees() {
	     String email = authUtils.getLoggedInUserEmail();
	     Employee employee = empService.getEmployee(email);
	     Set<Project> myProjects = employee.getHaveProject();
	     
	     List<ProjectEmployeeDTO> projectEmployeeDTOs = new ArrayList<>();
	     
	     for (Project project : myProjects) {
	         Set<Employee> empSet = project.getHaveEmployee();
	         ProjectEmployeeDTO projectEmployeeDTO = new ProjectEmployeeDTO(project, empSet);
	         projectEmployeeDTOs.add(projectEmployeeDTO);
	     }
	     System.out.println(projectEmployeeDTOs);
	     return new ResponseEntity<>(projectEmployeeDTOs, HttpStatus.OK);
	 }
}
