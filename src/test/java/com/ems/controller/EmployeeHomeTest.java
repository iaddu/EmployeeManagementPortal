package com.ems.controller;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

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
    
    @Mock
    private AssignManagerRequest assignManagerRequest;
    
    
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
    private AssignRequest assignRequest;
    
    @Mock
	private AuthUtils authUtils;
    
    
    

    @Autowired
    private ObjectMapper objectMapper; // Used for object serialization/deserialization
    
    

}
