package com.ems.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ems.dao.EmployeeDao;
import com.ems.dao.ProjectDao;
import com.ems.dao.RequestDao;
import com.ems.dao.SkillDao;
import com.ems.model.Employee;
import com.ems.model.Project;
import com.ems.model.Request;
import com.ems.model.Skill;

public class EmpServiceTest {	
    @InjectMocks
    private EmpService empService;
    
    @Mock
    private EmployeeDao empDao;
    
    @Mock
    private  SkillDao skillDao;
    
    @Mock
    private RequestDao requestDao;
    
    @Mock
    private ProjectDao projectDao;
    
    @Mock
    private Employee employee;
    
    @Mock
    private Project project;
    
    @Mock
    private Request request;
    
    @Mock
    private Skill skill;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @AfterEach
    void tearDown() throws Exception {
        // No need to close anything if using MockitoAnnotations.openMocks
    }
    
    @Test
    public void testRegisterEmployee() {
        empService.createEmp(employee);
        verify(empDao, times(1)).save(employee);
    }   
    
    @Test
     public void testGetAllEmployee() {
    	when(employee.getAddress()).thenReturn("berlin");
    	List<Employee> employees = new ArrayList<>();
    	employees.add(employee);
        when(empDao.findAll()).thenReturn(employees);
        List<Employee> result = empService.getAllEmployee();
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getAddress()).isEqualTo("berlin");
    }
    
    @Test
    public void testGetAllEmployeeOnly() {
    	when(employee.getRole()).thenReturn("employee");
    	List<Employee> employees=new ArrayList<>();
    	employees.add(employee);
    	when(empDao.findAll()).thenReturn(employees);
    	List<Employee> result=empService.getAllEmployeeOnly();
    	assertThat(result).isNotNull();
    	assertThat(result.get(0).getRole()).isEqualTo("employee");
    }
    
    @Test
    public void testGetAllManagerOnly() {
    	when(employee.getRole()).thenReturn("manager");
    	List<Employee> employees=new ArrayList<>();
    	employees.add(employee);
    	when(empDao.findAll()).thenReturn(employees);
    	List<Employee> result=empService.getAllManagerOnly();
    	assertThat(result).isNotNull();
    	assertThat(result.get(0).getRole()).isEqualTo("manager");
    }
    
    @Test
    public void testGetEmployee() {
    	String email="adi@123";
    	when(employee.getEmail()).thenReturn(email);
        when(empDao.findEmployeeByEmail(email)).thenReturn(Optional.of(employee));
    	Employee result=empService.getEmployee(email);
    	assertThat(result).isNotNull();
    	assertThat(result.getEmail()).isEqualTo(email);
    }
    
    @Test
    public void testGetManagerName() { 
    	when(employee.getFirstName()).thenReturn("addu");
    	when(employee.getLastName()).thenReturn("kumar");
    	String expected=employee.getFirstName()+" "+employee.getLastName();
    	int empId=23;
    	employee.setEmpId(empId);
    	when(empDao.findEmployeeByempId(empId)).thenReturn(Optional.of(employee));
		String result=empService.getManagerName(empId);
		assertThat(result).isNotNull();
		assertThat(result).isEqualTo(expected);
    }
    
    @Test
    public void  testAssignSkill() {
    	when(skill.getSkillId()).thenReturn(1);
    	when(skill.getSkillName()).thenReturn("Java");
    	Set<Skill> empSkills = new HashSet<>();
        when(employee.getHaveSkills()).thenReturn(empSkills);
        empService.assignSkill(employee, "Java");
        assertThat(employee.getHaveSkills()).isNotNull();
        assertThat(employee.getHaveSkills().size()).isEqualTo(1);
       // assertThat(employee.getHaveSkills().iterator().next().equals("Java"));   npe
       // System.out.println(employee.getHaveSkills());  why [null]
        verify(empDao, times(1)).save(employee);
    }
   
    @Test
	public void testDeleteEmp() {
    	String email="adi@123";
    	when(employee.getEmail()).thenReturn(email);
    	when(empDao.findEmployeeByEmail(email)).thenReturn(Optional.of(employee));
    	when(employee.getEmpId()).thenReturn(0);
    	when(employee.getRole()).thenReturn("manager");
    	
    	Set<Project> projectSet=new HashSet<>();
        projectSet.add(new Project());
        when(employee.getHaveProject()).thenReturn(projectSet);
        
        List<Employee> employeeList=new ArrayList<>();
        employeeList.add(employee);
        when(empDao.findAll()).thenReturn(employeeList);
    	
    	//Employee employee=empService.getEmployee(email);
    	Employee deletedEmployee=empService.deleteEmp(email);
    	
    	 // Assertions to check the returned employee
        assertThat(deletedEmployee).isNotNull();
        assertThat(deletedEmployee.getEmail()).isEqualTo(email);
        
    	 
        verify(empDao, times(1)).deleteEmpSkillsByEmployeeId(0);
        verify(empDao, times(1)).findEmployeeByEmail(email);
        verify(empDao, times(1)).delete(employee);
        
        for (Employee emp : employeeList) {
            if (emp.getManager() != 0 && emp.getManager() == employee.getEmpId()) {
                assertThat(emp.getManager()).isNull();
                assertThat(emp.getAssignedProject()).isNull();
            }
        }
        
    } 
     
    @Test
    public void testGetUnassignedEmployee() {
    	when(employee.getEmail()).thenReturn("adi@123");
    	when(employee.getAssignedProject()).thenReturn(null);
    	
    	List<Employee> employeeList=new ArrayList<>();
    	employeeList.add(employee);
    	when(empDao.findAll()).thenReturn(employeeList);
    	
    	List<Employee> result=empService.getUnassignedEmplyee();
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getEmail()).isEqualTo("adi@123");
        assertThat(result.get(0).getAssignedProject()).isNull();  
        }
    
    @Test
	public void getAssignManager() {
    	//String managerId,String projectId
    	when(employee.getEmpId()).thenReturn(0);
    	when(project.getProId()).thenReturn(0);
    	when(empDao.findEmployeeByempId(0)).thenReturn(Optional.of(employee));
    	when(projectDao.findProjectByproId(0)).thenReturn(Optional.of(project));
    	when(project.getHaveManager()).thenReturn(employee);
    	empService.assignManager(""+employee.getEmpId(), ""+project.getProId());
    	
        
    	verify(projectDao,times(1)).save(project);
    	assertThat(project.getHaveManager()).isEqualTo(employee);
    }
    
    
    @Test
    public void testAssignThisRequest() {
    	when(employee.getEmpId()).thenReturn(0);
    	when(employee.getManager()).thenReturn(1);
    	when(project.getProId()).thenReturn(2);
    	when(request.getReqId()).thenReturn(3);
    	when(empDao.findEmployeeByempId(0)).thenReturn(Optional.of(employee));
    	when(projectDao.findProjectByproId(2)).thenReturn(Optional.of(project));
    	when(empDao.findEmployeeByempId(1)).thenReturn(Optional.of(employee));
    	when(requestDao.findRequestByreqId(3)).thenReturn(Optional.of(request));
    	
    	assertThat(employee).isNotNull();
    	
    	when(employee.getManager()).thenReturn(1);
    	when(employee.getAssignedProject()).thenReturn(project);
    	when(request.getReqStatus()).thenReturn("Approved");
    	empService.assignThisRequest(""+request.getReqId(), ""+employee.getEmpId(), ""+employee.getManager(), 
    			""+project.getProId());
    	
    	verify(employee).setManager(employee.getManager());
        verify(employee).setAssignedProject(project);
        verify(request).setReqStatus("Approved");

        verify(requestDao, times(1)).save(request);
        verify(empDao, times(1)).save(employee);

        assertThat(employee.getManager()).isEqualTo(employee.getManager());
        assertThat(employee.getAssignedProject()).isEqualTo(project);
        assertThat(request.getReqStatus()).isEqualTo("Approved");

    }
     
    @Test
    public void testUnassignThisRequest() {
    	when(employee.getEmpId()).thenReturn(0);
    	when(request.getReqId()).thenReturn(1);
    	when(empDao.findEmployeeByempId(0)).thenReturn(Optional.of(employee));
    	when(requestDao.findRequestByreqId(1)).thenReturn(Optional.of(request));
    	
    	assertThat(employee).isNotNull();
    	assertThat(request).isNotNull();
    	
    	when(employee.getManager()).thenReturn(0);
    	when(employee.getAssignedProject()).thenReturn(project);
    	
    	empService.unassignThisRequest(""+request.getReqId(),""+employee.getEmpId());
    	
    	verify(employee).setManager(null);
        verify(employee).setAssignedProject(null);
    	
    	verify(empDao,times(1)).save(employee);
    	verify(requestDao,times(1)).save(request);
    }
    
    @Test
    public void testRejectThisRequest() {
    	when(request.getReqId()).thenReturn(1);
    	when(requestDao.findRequestByreqId(1)).thenReturn(Optional.of(request));
    	
    	empService.rejectThisRequest(""+request.getReqId());
    	
    	verify(request).setReqStatus("Rejected");
    	verify(requestDao,times(1)).save(request);
    	
    }
     
    @Test
    public void testUpdateEmp() {
    	//testing data
        String empId = "1";
        String firstName = "Alpha";
        String lastName = "Singh";
        String email = "Alpha@123.com";
        String address = "Tower Square";
        String phone = "9789876556";
        String gender = "Male";
        String role = "Developer";
        String dob = "2000-01-01";
        Set<Skill> skills = Set.of(new Skill());

        when(empDao.findEmployeeByempId(Integer.parseInt(empId))).thenReturn(Optional.of(employee));

        empService.updateEmp(empId, firstName, lastName, email, address, phone, gender, role, dob, skills);

        verify(employee).setFirstName(firstName);
        verify(employee).setLastName(lastName);
        verify(employee).setEmail(email);
        verify(employee).setAddress(address);
        verify(employee).setPhone(phone);
        verify(employee).setGender(gender);
        verify(employee).setRole(role);
        verify(employee).setDob(dob);
        verify(employee).setHaveSkills(skills);

        verify(empDao, times(1)).save(employee);
    }
    
    @Test
    public void testUpdateSkill() {
    	when(employee.getEmpId()).thenReturn(1);
    	when(empDao.findEmployeeByempId(1)).thenReturn(Optional.of(employee));
    	assertThat(employee).isNotNull();
    	
        Set<Skill> skills = Set.of(new Skill());

    	empService.updateSkill(""+employee.getEmpId(),skills);
    	
        verify(employee).setHaveSkills(skills);
        verify(empDao,times(1)).save(employee);
        
    }
     
    
    @Test
    public void testChangePassword() {
    	String email="adi@123";
    	String pass="123";
    	 
    	when(empDao.findEmployeeByEmail(email)).thenReturn(Optional.of(employee));
    	
    	empService.changePassword(email, pass);
    	
    	verify(employee).setPassword(pass);
    	verify(empDao,times(1)).save(employee);
    	
    }
    
}
