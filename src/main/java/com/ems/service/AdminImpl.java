package com.ems.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ems.model.Admin;
import com.ems.repository.AdminLoginRepository;

@Service
public class AdminImpl implements AdminInter{
	
	private AdminLoginRepository adminLoginRepository;
    /*private final BCryptPasswordEncoder passwordEncoder;
    
	public AdminImpl(AdminLoginRepository adminLoginRepository, BCryptPasswordEncoder passwordEncoder) {
		this.adminLoginRepository = adminLoginRepository;
		this.passwordEncoder = passwordEncoder;
	}*/
	public boolean isAuthentic(String name,String password) {
		/*Admin admin=adminLoginRepository.findByName(name);
		if(admin==null)return false;
		return passwordEncoder.matches(password, admin.getPassword());
		*/
		return true;
	}
	
	
}
