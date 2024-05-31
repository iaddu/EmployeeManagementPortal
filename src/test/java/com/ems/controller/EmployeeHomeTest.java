package com.ems.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ems.dto.AssignManagerRequestDTO;
import com.ems.dto.AssignRequestDTO;
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
@WithMockUser(username="emp",roles={"EMPLOYEE"})
public class EmployeeHomeTest {

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
    private AssignRequestDTO assignRequest;
   
    @Autowired
    private ObjectMapper objectMapper; // Used for object serialization/deserialization
    
    @Test
    public void testEmployeeHome() throws Exception{
    	mockMvc.perform(get("/emp/home"))
    	.andExpect(status().is3xxRedirection());
    } 
    
    @Test
    public void testGetAllEmployee() throws Exception {
    	List<Employee> employeeList=new ArrayList<>();
    	when(employee.getFirstName()).thenReturn("adi");
    	employeeList.add(employee);
    	when(employeeService.getAllEmployee()).thenReturn(employeeList);
    	
    	mockMvc.perform(get("/emp/getAllEmployee"))
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$[0].firstName").value("adi"));
    } 
    
    @Test
    public void testGetEmployee() throws Exception {
        String email = "adi@123";
        Employee emp = new Employee();
        emp.setFirstName("adi");
        emp.setManager(0); 
        emp.setEmail(email);

        when(authUtils.getLoggedInUserEmail()).thenReturn(email);
        when(employeeService.getEmployee(email)).thenReturn(emp);
        when(employeeService.getManagerName(0)).thenReturn("adi");

        mockMvc.perform(get("/emp/viewEmployee"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.employee.firstName").value("adi"))
               .andExpect(jsonPath("$.managerName").value("adi"));

        verify(authUtils).getLoggedInUserEmail();
        verify(employeeService).getEmployee(email);
        verify(employeeService).getManagerName(0);
    } 
    	
    @Test
    public void testGetAllProject() throws Exception {
    	List<Project> projectList=new ArrayList<>();
    	projectList.add(project);
    	when(project.getProName()).thenReturn("emp");
    	when(projectService.getAllProject()).thenReturn(projectList);
    	mockMvc.perform(get("/emp/getAllProject"))
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$[0].proName").value("emp"));
    }
     
    @Test
    public void testUpdateSkill() throws Exception{
    	String empId="0";
    	Set<String> skills=new HashSet<>();
    	skills.add("Java");
    	Set<Skill> skillSet=new HashSet<>();
    	when(skillService.getSkillSet(skills)).thenReturn(skillSet);
    	mockMvc.perform(post("/emp/updateSkill")
    			.param("empId",empId)
    			.param("skills","Java"))
    	.andExpect(status().isOk());
    	
    	verify(skillService).getSkillSet(skills);
    }
     
    @Test
    public void testChangePassword() throws Exception{
    	String upass="upass";
    	when(authUtils.getLoggedInUserEmail()).thenReturn("adi@123");
    	when(passwordEncoder.encode(upass)).thenReturn("encodedUpass");
    	mockMvc.perform(post("/emp/changePassword")
    			.param("upass", upass))
    	.andExpect(status().isOk());
    	verify(employeeService).changePassword("adi@123", "encodedUpass");
    	
    	
    	
    }
}
