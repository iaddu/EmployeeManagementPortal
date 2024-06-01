package com.ems.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SkillTest {
	@InjectMocks
	private Skill skill;
	
	@Mock
	private Employee employee;
	
	@BeforeEach
	void setUp() {
        MockitoAnnotations.openMocks(this);
        skill.setSkillId(1);
        skill.setSkillName("java");
        when(employee.getFirstName()).thenReturn("adi");
        Set<Employee> empSet = new HashSet<>();
        empSet.add(employee);
        skill.setEmployee(empSet);
	}
	
	@Test
	void testGettersAndSetters() {
		 assertEquals(1, skill.getSkillId());
	        assertEquals("java", skill.getSkillName());
	        assertEquals(1, skill.getEmployee().size());
	        assertEquals("adi", skill.getEmployee().iterator().next().getFirstName());
		
	}

}
