package com.ems.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ems.model.Employee;

public class EmployeeResponseDtoTest {
	
	@InjectMocks
	private EmployeeResponseDTO ert;
	
	@Mock
	private Employee employee;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		when(employee.getFirstName()).thenReturn("adi");
		ert.setEmployee(employee);
		ert.setManagerName("rahul");
	}
	
	@Test
	public void testGettersAndSetters() {
		assertEquals("rahul",ert.getManagerName());
		assertEquals("adi",ert.getEmployee().getFirstName());
	}
	

}
