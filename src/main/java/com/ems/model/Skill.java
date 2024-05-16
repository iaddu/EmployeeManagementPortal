package com.ems.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="skill")
public class Skill {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
	@Column(name="skillId")
	private int skillId;
	
	@Column(name="skillName")
	private String skillName;
	
	
	@ManyToMany(mappedBy="haveSkills")
	private Set<Employee> employee;
	
	public Set<Employee> getEmployee() {
		return employee;
	}
	
	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}
	
	public Skill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Skill(int skillId, String skillName) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

}
