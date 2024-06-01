package com.ems.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ems.model.Project;

public class ProjectEmployeeDtoTest {
	
	 private ProjectEmployeeDTO proEmpDto;
	 
	 private Project project;
	 
	 private EmployeeDTO employeeDto;
	 
	 @BeforeEach
	 public void setUp() {
		 project=new Project();
		 employeeDto=new EmployeeDTO();
		 proEmpDto=new ProjectEmployeeDTO();
		 
		 project.setProId(0);
		 project.setProName("ems");
		 
		 employeeDto.setFirstName("adi");
		 
		 Set<EmployeeDTO> empSet=new HashSet<>();
		 empSet.add(employeeDto);

		 
		 proEmpDto.setProjectId(project.getProId());
		 proEmpDto.setProjectName(project.getProName());
		 proEmpDto.setEmployees(empSet);
	 }
	 
	 @Test
	 public void testGetters() {
		 assertEquals(0,proEmpDto.getProjectId());
		 assertEquals("ems",proEmpDto.getProjectName());
		 assertEquals(1,proEmpDto.getEmployees().size());
		 assertEquals("adi",proEmpDto.getEmployees().iterator().next().getFirstName());
	 }

}
