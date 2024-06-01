package com.ems.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AssignRequestDtoTest {
	
	private AssignRequestDTO art;
	
	@BeforeEach
	public void setUp() {
		String empId="1";
		String managerId="2";
		String proId="3";
		String reqId="0";
		art=new AssignRequestDTO(reqId,proId,managerId,empId);
	}
	
	@Test
	public void testGetters() {
		assertEquals("1",art.getEmpId());
		assertEquals("2",art.getManagerId());
		assertEquals("3",art.getProId());
		assertEquals("0",art.getReqId());
	}
}
