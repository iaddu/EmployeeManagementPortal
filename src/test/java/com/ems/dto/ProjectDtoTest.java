package com.ems.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjectDtoTest {
	
	private ProjectDTO projectDto;
	
	@BeforeEach
	public void setUp() {
		projectDto=new ProjectDTO();
		projectDto.setProId("1");
	}
	
	@Test
	public void testGetters() {
		assertEquals("1",projectDto.getProId());
	}

}
