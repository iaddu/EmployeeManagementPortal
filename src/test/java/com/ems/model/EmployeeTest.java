package com.ems.model; 

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class EmployeeTest {
	
    private Employee employee;
    
    private Skill skill;
    
    private Project project;

    @BeforeEach
    public void setUp() {
        employee = new Employee();
        skill=new Skill();
        project=new Project();
    }

    @Test
    public void testGettersAndSetters() {
        employee.setEmpId(1);
        employee.setFirstName("adi");
        employee.setLastName("patel");
        employee.setEmail("adi@123.com");
        employee.setAddress("indore");
        employee.setPhone("98787");
        employee.setGender("Male");
        employee.setRole("Developer");
        employee.setPassword("password");
        employee.setDob("1990-01-01");

        assertEquals(1, employee.getEmpId());
        assertEquals("adi", employee.getFirstName());
        assertEquals("patel", employee.getLastName());
        assertEquals("adi@123.com", employee.getEmail());
        assertEquals("indore", employee.getAddress());
        assertEquals("98787", employee.getPhone());
        assertEquals("Male", employee.getGender());
        assertEquals("Developer", employee.getRole());
        assertEquals("password", employee.getPassword());
        assertEquals("1990-01-01", employee.getDob());
    }

    @Test
    public void testSkills() {
        Set<Skill> skills = new HashSet<>();
        skill.setSkillId(1);
        skill.setSkillName("Java");
        skills.add(skill);

        employee.setHaveSkills(skills);

        assertNotNull(employee.getHaveSkills());
        assertEquals(1, employee.getHaveSkills().size());
        assertEquals("Java", employee.getHaveSkills().iterator().next().getSkillName());
    }
    
    @Test
    public void testProject() {
    	Set<Project> projects=new HashSet<>();
    	project.setProId(0);
    	project.setProName("emp");
    	employee.setHaveProject(projects);
    	assertNotNull(employee.getHaveProject());
    }
    
    
}
