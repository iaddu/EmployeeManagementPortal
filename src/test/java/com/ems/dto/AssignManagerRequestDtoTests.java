package com.ems.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AssignManagerRequestDtoTests {
	private AssignManagerRequestDTO amrt;
	
	@BeforeEach
	public void setUp() {
		amrt=new AssignManagerRequestDTO();
		 amrt.setManagerId("1");
		 amrt.setProId("2");
	}
	
	@Test
	public void testGettersAndSetters() {
		assertEquals("1",amrt.getManagerId());
		assertEquals("2",amrt.getProId());
	}

}
