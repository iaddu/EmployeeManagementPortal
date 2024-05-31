package com.ems.controller;

 import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.ems.dto.AssignManagerRequestDTO;
import com.ems.dto.AssignRequestDTO;
import com.ems.dto.RequestDTO;
import com.ems.model.EmailDTO;
import com.ems.model.Employee;
import com.ems.model.Project;
import com.ems.model.ProjectDTO;
import com.ems.model.Request;
import com.ems.model.Skill;
import com.ems.service.AuthUtils;
import com.ems.service.EmailService;
import com.ems.service.EmpService;
import com.ems.service.ProjectService;
import com.ems.service.RequestService;
import com.ems.service.SkillService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username="adi",roles={"MANAGER"})
public class ManagerHomeTest {
	  @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private PasswordEncoder passwordEncoder;
	    
	    @MockBean
	    private ProjectService projectService;
	    
	    @MockBean
	    private EmpService employeeService;
	    
	    @MockBean
	    private RequestService requestService;
	    
	    @MockBean
	    private SkillService skillService;
	    
	    @MockBean
	    private EmailService emailService;
	    
	    @MockBean
		private AuthUtils authUtils;
	    
	    @Mock
	    private AssignManagerRequestDTO assignManagerRequest;
	    
	    
	    @Mock
	    private Project project;
	    
	    @Mock
	    private Skill skill;

	    @Mock
	    private Employee employee;
	    
	    @Mock
	    private Request request;
	    
	    @Mock
	    private EmailDTO emailDto;
	    
	    @Mock
	    private ProjectDTO projectDto;
	    
	    @Mock
	    private RequestDTO requestDto;
	    
	    @Mock
	    private AssignRequestDTO assignRequest;
	   
	    @Autowired
	    private ObjectMapper objectMapper;  
	    
	    @Test
	    public void testManagerHome()throws Exception {
	    	mockMvc.perform(get("/manager/home"))
	    	.andExpect(status().is3xxRedirection());
	    } 
	    @Test
	    public void testGetAllEmployee() throws Exception{
	    	List<Employee> employeeList=new ArrayList<>();
	    	employeeList.add(employee);
	    	when(employee.getFirstName()).thenReturn("adi");
	    	
	    	when(employeeService.getAllEmployee()).thenReturn(employeeList);
	    	
	    	mockMvc.perform(get("/manager/getAllEmployee"))
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$[0].firstName").value("adi"));
	    } 
	    @Test
	    public void testGetAllProject() throws Exception{
	    	List<Project> projectList=new ArrayList<>();
	    	projectList.add(project);
	    	when(project.getProName()).thenReturn("ems");
	    	
	    	when(projectService.getAllProject()).thenReturn(projectList);
	    	
	    	mockMvc.perform(get("/manager/getAllProject"))
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$[0].proName").value("ems"));
	    } 
	    @Test
	    public void testGetEmployee()throws Exception {
	    	when(emailDto.getEmail()).thenReturn("adi@123");
	    	when(employee.getFirstName()).thenReturn("adi");
	    	when(employeeService.getEmployee(emailDto.getEmail())).thenReturn(employee);
	    	
	    	mockMvc.perform(post("/manager/viewEmployee")
	    			.contentType(MediaType.APPLICATION_JSON)
	    			.content(objectMapper.writeValueAsString(emailDto)))
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$.firstName").value("adi"));
	    	
	    } 
	    @Test
	    public void testRequestAdmin() throws Exception {
	        String email = "adi@123";
	        String empId = "1";
	        String proId = "2";
	        String to = "assign";
	        String status = "pending";
	       
	        when(requestDto.getEmpId()).thenReturn(Integer.parseInt(empId));
	        when(requestDto.getProId()).thenReturn(Integer.parseInt(proId));
	        when(requestDto.getTo()).thenReturn(to);
	       
	        when(employee.getEmpId()).thenReturn(1);
	        when(employee.getEmail()).thenReturn(email);
	       
	        when(authUtils.getLoggedInUserEmail()).thenReturn(email);
	        when(employeeService.getEmployee(email)).thenReturn(employee);
	        
	        mockMvc.perform(post("/manager/requestAdmin")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(requestDto)))
	                .andExpect(status().isOk()); 
	    } 
	    
	    @Test
	    public void testChangePassword() throws Exception{
	    	String upass="upass";
	    	when(authUtils.getLoggedInUserEmail()).thenReturn("adi@123");
	    	when(passwordEncoder.encode(upass)).thenReturn("encodedUpass");
	    	mockMvc.perform(post("/manager/changePassword")
	    			.param("upass", upass))
	    	.andExpect(status().isOk());
	    	verify(employeeService).changePassword("adi@123", "encodedUpass");
	    }
	    }

