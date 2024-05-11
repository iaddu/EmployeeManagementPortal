package com.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dao.UserDao;
import com.ems.model.User;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	/*
	 //this was created at the time of registering an admin
	public void createUser(User user) {
		userDao.save(user);
	}*/
	
}
