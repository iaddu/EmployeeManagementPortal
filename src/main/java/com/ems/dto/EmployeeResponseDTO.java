package com.ems.dto;

import com.ems.model.Employee;

public class EmployeeResponseDTO {
	private Employee employee;
	private String managerName;
	
	public EmployeeResponseDTO(Employee employee, String managerName) {
		super();
		this.employee = employee;
		this.managerName = managerName;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	@Override
	public String toString() {
		return "EmployeeResponse [employee=" + employee + ", managerName=" + managerName + "]";
	}
	
	

}
