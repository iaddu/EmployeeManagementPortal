package com.ems.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ems.model.Employee;
@Repository
public interface EmployeeDao  extends JpaRepository<Employee, Integer>{
	public Optional<Employee> findEmployeeByEmail(String email);
    public void deleteByEmail(String email);
    public Employee getByEmail(String email);
    public Optional<Employee> findEmployeeByempId(int empId);
    @Modifying 
    @Transactional
    @Query(value = "DELETE FROM emp_skill WHERE emp_id = :empId", nativeQuery = true)
    void deleteEmpSkillsByEmployeeId(int empId);
}
