package com.ems.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequestDtoTest {
	private RequestDTO requestDto;
	
	@BeforeEach
	public void setUp() {
		requestDto=new RequestDTO();
		requestDto.setEmpId(0);
		requestDto.setProId(1);
		requestDto.setTo("assign");
		
	}
	
	@Test
	public void testGetters() {
		assertEquals(0,requestDto.getEmpId());
		assertEquals(1,requestDto.getProId());
		assertEquals("assign",requestDto.getTo());
	}
}
