package com.ems.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ems.model.Employee;

public class EmployeeDaoTest {
	/*
	 	public Optional<Employee> findEmployeeByEmail(String email);
    public void deleteByEmail(String email);
    public Employee getByEmail(String email);
    public Optional<Employee> findEmployeeByempId(int empId);
    @Transactional
    @Query(value = "DELETE FROM emp_skill WHERE emp_id = :empId", nativeQuery = true)
    void deleteEmpSkillsByEmployeeId(int empId);
	 */
	
	@Mock 
	private EmployeeDao empDao;
	
	private Employee employee;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		employee=new Employee();
		employee.setFirstName("adi");
		employee.setEmail("adi@123");
		employee.setEmpId(1);
	}
	@Test
	public void testFindEmployeeByEmail() {
		String email="adi@123";
		when(empDao.findEmployeeByEmail(email)).thenReturn(Optional.of(employee));
		Optional<Employee> result=empDao.findEmployeeByEmail(email);
		assertNotNull(result.get());
		assertEquals(email, result.get().getEmail());
		assertEquals("adi",result.get().getFirstName());
		
	}
	
	@Test
	public void testDeleteByEmail() {
		String email="adi@123";
		empDao.deleteByEmail(email);
		verify(empDao,times(1)).deleteByEmail(email);
	}
	
	@Test
	public void testGetByEmail() {
		String email="adi@123";
		when(empDao.getByEmail(email)).thenReturn(employee);
		Employee result=empDao.getByEmail(email);
		assertNotNull(result);
		assertEquals(email,result.getEmail());
		assertEquals("adi",result.getFirstName());
	}
	

	
	@Test
	public void testFindEmployeeByEmpId() {
		when(empDao.findEmployeeByempId(1)).thenReturn(Optional.of(employee));
		Optional<Employee> result=empDao.findEmployeeByempId(1);
		assertNotNull(result.get());
		assertEquals(1,result.get().getEmpId());
	} 
	
	@Test
	public void testDeleteEmpSkillsByEmployeeId() {
		empDao.deleteEmpSkillsByEmployeeId(1);
		verify(empDao,times(1)).deleteEmpSkillsByEmployeeId(1);
	}
}
