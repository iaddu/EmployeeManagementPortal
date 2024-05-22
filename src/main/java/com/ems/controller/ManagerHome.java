package com.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ems.model.EmailDTO;
import com.ems.model.Employee;
import com.ems.model.Request;
import com.ems.service.AuthUtils;
import com.ems.service.EmpService;
import com.ems.service.RequestService;

@RestController
@RequestMapping("/manager")
public class ManagerHome {
	@Autowired
	private EmpService empService;
	
	@Autowired
	private AuthUtils authUtils;
	
	@Autowired
	private RequestService requestService;
	@GetMapping("/home")
	public RedirectView managerHome() {
		return new RedirectView("/managerfiles/managerHome.html");
	}

	 @GetMapping("/getAllEmployee")
	 public List<Employee> getAllEmployee(){
		 
		List<Employee> allEmployeeList= empService.getAllEmployee();
		System.out.print(allEmployeeList);
		return allEmployeeList;
	 } 
	 @PostMapping("/viewEmployee")
	 public Employee getEmployee(@RequestBody EmailDTO emailDTO){
		Employee employee= empService.getEmployee(emailDTO.getEmail());
		
		return employee;
	 } 
	 @PostMapping("/requestAdmin")
	 public void requestAdmin(@RequestBody RequestDTO requestDTO) {
		 String email = authUtils.getLoggedInUserEmail();
		 Employee manager=empService.getEmployee(email);
		 Request request=new Request(manager.getEmpId(),requestDTO.getEmpId(), requestDTO.getProId(), "pending",requestDTO.getTo());
		 requestService.createRequest(request);
	 }
}
