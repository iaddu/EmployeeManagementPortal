package com.ems.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="project")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="proId")
	private int proId;
	
	@Column(name="proName")
	private String proName;
	
	
	
	@OneToMany(mappedBy="assignedProject")
	private Set<Employee> haveEmployee;
	
	//many project can have one common manager
	@ManyToOne
	@JoinColumn(name = "managerId")
	private Employee haveManager;
	
	
	
	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Project [proId=" + proId + ", proName=" + proName + ", haveEmployee=" + haveEmployee + ", haveManager="
				+ haveManager + "]";
	}
	public Set<Employee> getHaveEmployee() {
		return haveEmployee;
	}
	public void setHaveEmployee(Set<Employee> haveEmployee) {
		this.haveEmployee = haveEmployee;
	}
	public Employee getHaveManager() {
		return haveManager;
	}
	public void setHaveManager(Employee haveManager) {
		this.haveManager = haveManager;
	}
	public Project(String proName) {
		super();
		this.proName = proName;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	
}
