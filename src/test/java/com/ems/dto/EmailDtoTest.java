package com.ems.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmailDtoTest {
	
	private EmailDTO emailDto;
	
	@BeforeEach
	public void setUp() {
		emailDto=new EmailDTO();
		emailDto.setEmail("adi@gmail.com");
	}
	
	@Test
	public void testGetters() {
		assertEquals("adi@gmail.com",emailDto.getEmail());
	}

}
