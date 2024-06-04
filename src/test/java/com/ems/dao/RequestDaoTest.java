package com.ems.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ems.model.Request;

public class RequestDaoTest {
 	
	@Mock
	private RequestDao requestDao;
	
	private Request request;
	
	@BeforeEach
	public void setUp() {
	  MockitoAnnotations.openMocks(this);
	  request=new Request();
	  request.setReqId(1);
	}
	
	@Test
	public void testFindRequestByreqId() {
		when(requestDao.findRequestByreqId(1)).thenReturn(Optional.of(request));
		Optional<Request> result=requestDao.findRequestByreqId(1);
		assertEquals(1,result.get().getReqId());
	}
}
