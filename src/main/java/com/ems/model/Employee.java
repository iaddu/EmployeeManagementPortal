package com.ems.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
	@Column(name="empId")
	private int empId;
	
	@Column(name="firstName")
    private String firstName;
	
	@Column(name="lastName")
    private String lastName;
	
	
	@Column(name="email",unique=true)
    private String email;
	
	@Column(name="address")
    private String address;
	
	@Column(name="phone")
    private String phone;
	
	@Column(name="gender")
    private String gender;
	
	@Column(name="role")
    private String role;
	
	@Column(name="password")
    private String password;
	
	@Column(name="dob")
	private String dob;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="emp_skill",
			joinColumns=@JoinColumn(name="empId"),
			inverseJoinColumns=@JoinColumn(name="skillId"))
	private Set<Skill> haveSkills;
	
	public Employee() {
    }
    public Employee(String firstName, String lastName, String email, String address, String phone,
			String gender, String role,String password,String dob,Set<Skill> skillSet) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.gender = gender;
		this.role = role;
		this.password=password;
		this.dob=dob;
		this.haveSkills=skillSet;
	}
	// Getters and Setters for each field
    
// Default constructor
	
    public Set<Skill> getHaveSkills() {
		return haveSkills;
	}


	public void setHaveSkills(Set<Skill> haveSkills) {
		this.haveSkills = haveSkills;
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

    public String getEmail() {
    	System.out.println("email found");
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
    
    public String getPassword() {
    	System.out.println("password found");
        return password;
    }

    public void setPassword(String password) {
        this.password= password;
    }
    

/*
    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }*/
}
