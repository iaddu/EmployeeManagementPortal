package com.ems.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class RequestTest {
	@Mock
	Request request;
	
	@BeforeEach
	public void setUp() {
		request=new Request();
		request.setReqId(1);
		request.setEmpId(0);
		request.setManagerId(2);
		request.setProId(3);
		request.setReqStatus("pending");
		request.setToStatus("assign");
	}
	
	@Test
	public void testGettersAndSetters() {
		assertEquals(1,request.getReqId());
		assertEquals(0,request.getEmpId());
		assertEquals(2,request.getManagerId());
		assertEquals(3,request.getProId());
		assertEquals("pending",request.getReqStatus());
		assertEquals("assign",request.getToStatus());
		
	}

}
