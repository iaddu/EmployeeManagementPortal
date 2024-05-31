package com.ems.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.ems.model.Employee;
import com.ems.model.Project;

public class ProjectEmployeeDTO {
    private int projectId;
    private String projectName;
    private Set<EmployeeDTO> employees;

    // Constructors
    public ProjectEmployeeDTO() {
    }

    public ProjectEmployeeDTO(Project project, Set<Employee> employees) {
        this.projectId = project.getProId();
        this.projectName = project.getProName();
        this.employees = employees.stream()
                                  .map(EmployeeDTO::new)
                                  .collect(Collectors.toSet());
    }

    // Getters and Setters
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Set<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeDTO> employees) {
        this.employees = employees;
    }

	@Override
	public String toString() {
		return "ProjectEmployeeDTO [projectId=" + projectId + ", projectName=" + projectName + ", employees="
				+ employees + "]";
	}
    
}

