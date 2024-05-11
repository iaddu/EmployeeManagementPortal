package com.ems.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.model.Employee;
import com.ems.model.User;
@Repository
public interface EmployeeDao  extends JpaRepository<Employee, Integer>{
	Optional<Employee> findEmployeeByEmail(String email);
}
