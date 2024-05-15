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
	Optional<Employee> findEmployeeByEmail(String email);
    public void deleteByEmail(String email);
    public Employee getByEmail(String email);
    @Modifying
    /*The @Modifying annotation in Spring Data JPA is used to indicate
     *  that a method modifies the database state. By default, 
     *  Spring Data JPA assumes that methods annotated with @Query 
     *  only read data from the database, not modify it
     */
    @Transactional
    @Query(value = "DELETE FROM emp_skill WHERE emp_id = :empId", nativeQuery = true)
    void deleteEmpSkillsByEmployeeId(int empId);
}
