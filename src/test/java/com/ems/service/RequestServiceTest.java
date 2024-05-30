package com.ems.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ems.dao.RequestDao;
import com.ems.model.Request;

public class RequestServiceTest {
	 
	@InjectMocks
	private RequestService requestService;
	
	@Mock
	private Request request;
	
	@Mock
	private RequestDao requestDao;
	
	@BeforeEach
	public void setUp() {
        MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testCreateRequest() {
		requestService.createRequest(request);
		verify(requestDao,times(1)).save(request);
	}
	 
	@Test
	public void testGetAllRequest() {
		List<Request> requestList=new  ArrayList<>();
		when(request.getReqId()).thenReturn(0);
		requestList.add(request);
		when(requestDao.findAll()).thenReturn(requestList);
		List<Request> result=requestService.getAllRequest();
		assertThat(result.get(0).getReqId()).isEqualTo(0);
	}
}
