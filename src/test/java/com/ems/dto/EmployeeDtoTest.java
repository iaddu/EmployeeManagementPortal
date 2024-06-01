package com.ems.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeDtoTest {
	
	private EmployeeDTO empDto;
	
	@BeforeEach
	public void setUp() {
		empDto=new EmployeeDTO();
		empDto.setEmpId(1);
		empDto.setFirstName("adi");
		empDto.setLastName("patel");
		
	}
	
	@Test
	public void testGetters() {
		assertEquals(1,empDto.getEmpId());
		assertEquals("adi",empDto.getFirstName());
		assertEquals("patel",empDto.getLastName());
	}

}
