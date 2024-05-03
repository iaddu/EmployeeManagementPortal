package com.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.model.Admin;
@Repository
public interface AdminLoginRepository extends JpaRepository<Admin, Integer>{
	Admin findByName(String name);
	
}
