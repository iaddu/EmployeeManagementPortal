package com.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dao.RequestDao;
import com.ems.model.Request;

@Service
public class RequestService {
	@Autowired
	private RequestDao requestDao;
	public void createRequest(Request request) {
		requestDao.save(request);
	}
	
	public List<Request> getAllRequest(){
		return requestDao.findAll();
	}
	
}
