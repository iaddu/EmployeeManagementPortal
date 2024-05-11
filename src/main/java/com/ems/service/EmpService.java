package com.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dao.EmployeeDao;
import com.ems.model.Employee;

@Service
public class EmpService {
	@Autowired
	EmployeeDao empDao;
	
	public void createEmp(Employee emp) {
		empDao.save(emp);
	}
	
}
