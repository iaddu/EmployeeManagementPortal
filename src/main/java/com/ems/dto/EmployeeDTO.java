package com.ems.dto;

import com.ems.model.Employee;

public class EmployeeDTO {
    private int empId;
    private String firstName;
    private String lastName;

    // Constructors
    public EmployeeDTO() {
    }

    public EmployeeDTO(Employee employee) {
        this.empId = employee.getEmpId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
    }

    // Getters and Setters
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
