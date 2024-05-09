package com.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dao.EmployeeDao;
import com.ems.dao.UserDao;
import com.ems.model.Employee;
import com.ems.model.User;

@Service
public class UserService {
	@Autowired
	UserDao userDao;
	
	@Autowired
	EmployeeDao empDao;
	
	public void createUser(User user) {
		userDao.save(user);
	}
	
}
