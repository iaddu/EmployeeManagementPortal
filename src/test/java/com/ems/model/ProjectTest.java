package com.ems.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjectTest {
	
    private Project project;
	
    private Employee employee;

    @BeforeEach
    void setUp() {
    	project=new Project();
    	employee=new Employee();
        employee.setFirstName("adi");
        project.setProName("ems");
        project.setProId(0);
        project.setHaveManager(employee);
    }
	
    @Test
    public void testGetterAndSetters() {
        Set<Employee> employeeSet = new HashSet<>();
        employeeSet.add(employee);
        project.setHaveEmployee(employeeSet);
        assertEquals("ems", project.getProName());
        assertEquals(0, project.getProId());
        assertEquals(employeeSet, project.getHaveEmployee());
        assertEquals(1,project.getHaveEmployee().size());
        assertEquals("adi",project.getHaveEmployee().iterator().next().getFirstName());
    }
}
