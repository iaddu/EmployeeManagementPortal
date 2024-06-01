package com.ems.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.ems.dto.AssignManagerRequestDTO;
import com.ems.dto.AssignRequestDTO;
import com.ems.dto.EmailDTO;
import com.ems.dto.ProjectDTO;
import com.ems.model.Employee;
import com.ems.model.Project;
import com.ems.model.Request;
import com.ems.model.Skill;
import com.ems.service.EmailService;
import com.ems.service.EmpService;
import com.ems.service.ProjectService;
import com.ems.service.RequestService;
import com.ems.service.SkillService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username="admin",roles={"ADMIN"})
public class AdminTest {

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
    private ObjectMapper objectMapper;  
    
    @Test
    public void testAdminHome() throws Exception{
    	mockMvc.perform(get("/admin/home"))
        .andExpect(status().is3xxRedirection());
    }
    
    @Test
    public void testGetAllProjects() throws Exception {
        // Perform a GET request to /admin/getAllProject endpoint with authentication
    		List<Project> projectList=new ArrayList<>();
    		when(project.getProName()).thenReturn("ems");
    		projectList.add(project);
    		when(projectService.getAllProject()).thenReturn(projectList);
    		
    		mockMvc.perform(get("/admin/getAllProject"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].proName").value("ems"));
    }
    
    @Test
    public void getAllEmployee() throws Exception{
    	List<Employee> employeeList=new ArrayList<>();
    	when(employee.getFirstName()).thenReturn("adi");
    	employeeList.add(employee);
    	when(employeeService.getAllEmployee()).thenReturn(employeeList);
    	
    	mockMvc.perform(get("/admin/getAllEmployee"))
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$[0].firstName").value("adi"));
    }  
    @Test
    public void getAllEmployeeOnly() throws Exception {
    	List<Employee> employeeList=new ArrayList<>();
    	when(employee.getRole()).thenReturn("manager");
    	employeeList.add(employee);
    	when(employeeService.getAllManagerOnly()).thenReturn(employeeList);
    	
    	mockMvc.perform(get("/admin/getAllManagerOnly"))
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$[0].role").value("manager"));
    }
     
    @Test
    public void testGetUnassignedEmployee() throws Exception {
         List<Employee> unassignedEmployeeList = new ArrayList<>();
        when(employee.getRole()).thenReturn("employee");
        unassignedEmployeeList.add(employee);

         when(employeeService.getUnassignedEmplyee()).thenReturn(unassignedEmployeeList);

         mockMvc.perform(get("/admin/getUnassignedEmployee"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].role").value("employee"));
    }
  
    @Test
    public void testGetAllRequest() throws Exception{
    	List<Request> requestList=new ArrayList<>();
    	when(request.getReqStatus()).thenReturn("assigned");
    	requestList.add(request);
    	when(requestService.getAllRequest()).thenReturn(requestList);
    	 mockMvc.perform(get("/admin/getAllRequest"))
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$[0].reqStatus").value("assigned"));
    }
    
    
    	@Test
    	public void testRegisterEmployee() throws Exception {
            String mockPassword = "mockPassword";
            when(employeeService.createPassword()).thenReturn(mockPassword);
            when(passwordEncoder.encode(mockPassword)).thenReturn("encodedPassword");

            Set<String> skillNames = new HashSet<>();
            skillNames.add("Java");
            skillNames.add("Spring");

            Set<Skill> skills = new HashSet<>();
            Skill skill1 = new Skill();
            skill1.setSkillName("Java");
            skills.add(skill1);
            Skill skill2 = new Skill();
            skill2.setSkillName("Spring");
            skills.add(skill2);

            when(skillService.getSkillSet(skillNames)).thenReturn(skills);

            mockMvc.perform(post("/admin/registerEmp")
                    .param("firstName", "John")
                    .param("lastName", "Doe")
                    .param("email", "john.doe@example.com")
                    .param("address", "123 Main St")
                    .param("phone", "1234567890")
                    .param("gender", "Male")
                    .param("role", "Developer")
                    .param("dob", "1990-01-01")
                    .param("skills", "Java,Spring"))
                    .andExpect(status().is3xxRedirection());

            verify(employeeService).createPassword();
            verify(passwordEncoder).encode(mockPassword);
            verify(skillService).getSkillSet(skillNames);
            verify(emailService).sendMail(eq("iadityapatel1729@gmail.com"), eq("adityapatel91221@gmail.com"), any(String.class));
            verify(employeeService).createEmp(org.mockito.ArgumentMatchers.argThat(employee -> 
            employee.getFirstName().equals("John") &&
            employee.getLastName().equals("Doe") &&
            employee.getEmail().equals("john.doe@example.com") &&
            employee.getAddress().equals("123 Main St") &&
            employee.getPhone().equals("1234567890") &&
            employee.getGender().equals("Male") &&
            employee.getRole().equals("Developer") &&
            employee.getPassword().equals("encodedPassword") &&
            employee.getDob().equals("1990-01-01") &&
            employee.getHaveSkills().equals(skills)
        ));
    	}
      	
    	@Test
        public void testDeleteEmployee() throws Exception {
            String email = "test@abc.com";
            when(employeeService.deleteEmp(email)).thenReturn(employee);

            when(emailDto.getEmail()).thenReturn(email);

            mockMvc.perform(delete("/admin/deleteEmp")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(emailDto)))
                    .andExpect(status().isOk());

            verify(employeeService).deleteEmp(email);
        } 
    	
    	@Test
    	public void testAddProject() throws Exception{
    		String proName="emp";
    		when(project.getProName()).thenReturn(proName);
    		
    		mockMvc.perform(post("/admin/addProject")
    				.param("proName",proName))		
                    .andExpect(status().is3xxRedirection());
    		
    		verify(projectService).createService(proName);
    	}
    	
    	 
    	@Test
    	public void testGetEmployee() throws Exception {
    		when(employee.getFirstName()).thenReturn("adi");
    	    when(employeeService.getEmployee(emailDto.getEmail())).thenReturn(employee);
    	    
    		mockMvc.perform(post("/admin/viewEmployee")
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(objectMapper.writeValueAsString(emailDto)))
    				.andExpect(status().isOk())
    				.andExpect(jsonPath("$.firstName").value("adi"));
    	}
    	 
    	@Test
    	public void testGetProject() throws Exception{
    		Project pro=new Project();
    		pro.setProName("ems");
    		pro.setProId(0);
    		when(projectDto.getProId()).thenReturn("0");;
    		when(projectService.getProject(""+project.getProId())).thenReturn(pro);
    		mockMvc.perform(post("/admin/viewProject")
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(objectMapper.writeValueAsString(projectDto)))
    				.andExpect(status().isOk())
    				.andExpect(jsonPath("$.proName").value("ems"));	
    	} 
    	
    	    	 
    	@Test
    	public void testAssignManager() throws Exception {
    		when(assignManagerRequest.getManagerId()).thenReturn("1");
    		when(assignManagerRequest.getProId()).thenReturn("0");
    		mockMvc.perform(post("/admin/assignManager")
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(objectMapper.writeValueAsString(assignManagerRequest)))
    				.andExpect(status().isOk());
    			
    		verify(employeeService).assignManager("1","0");
    	} 
    	
    	@Test
    	public void testAssignRequest() throws Exception{
    		when(assignRequest.getEmpId()).thenReturn("0");
    		when(assignRequest.getManagerId()).thenReturn("1");
    		when(assignRequest.getProId()).thenReturn("2");
    		when(assignRequest.getReqId()).thenReturn("3");
    		
    		mockMvc.perform(post("/admin/assignRequest")
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(objectMapper.writeValueAsString(assignRequest)))
    				.andExpect(status().isOk());
    		verify(employeeService).assignThisRequest("3", "0", "1", "2");
    	}
    	 
    	@Test
    	public void testUnassignRequest() throws Exception{
    		when(assignRequest.getEmpId()).thenReturn("0");
    		when(assignRequest.getReqId()).thenReturn("3");
    		
    		mockMvc.perform(post("/admin/unassignRequest")
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(objectMapper.writeValueAsString(assignRequest)))
    				.andExpect(status().isOk());
    		verify(employeeService).unassignThisRequest("3", "0");
    	} 
    	
    	@Test
    	public void testRejectRequest() throws Exception{
    		when(assignRequest.getReqId()).thenReturn("0");
    		
    		mockMvc.perform(post("/admin/rejectRequest")
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(objectMapper.writeValueAsString(assignRequest)))
    				.andExpect(status().isOk());
    		verify(employeeService).rejectThisRequest("0");
    	}
    
    @Test
    public void testUpdateEmployee() throws Exception{
    	Set<String> skillNames=new HashSet<>();
    	Set<Skill> skills=new HashSet<>();
    	skillNames.add("java");
    	when(skill.getSkillName()).thenReturn("java");
    	skills.add(skill);
    	mockMvc.perform(post("/admin/updateEmp")
    			.param("empId", "0")
    			.param("firstName", "adi")
    			.param("lastName", "patel")
    			.param("email", "adi@123")
    			.param("address", "tower square")
    			.param("phone", "2342")
    			.param("gender", "male")
    			.param("role","employee")
    			.param("dob","6.12.1999")
    			.param("skills", "java"))
                .andExpect(status().is3xxRedirection());
    	verify(skillService).getSkillSet(skillNames);
    	Set<Skill> result=skillService.getSkillSet(skillNames);
    	verify(employeeService).updateEmp("0","adi","patel","adi@123","tower square","2342","male","employee","6.12.1999",
    			result);
    }	
}
