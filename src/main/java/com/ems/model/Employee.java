package com.ems.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// DTO class to represent the data from the registration form
@Entity
@Table(name="Employee")
public class Employee {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
	@Column(name="id")
	private int Id;
	
	@Column(name="firstName")
    private String firstName;
	@Column(name="lastName")
    private String lastName;
	@Column(name="email")
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
	
	/*@Column(name="skills")
    private List<String> skills; // Capturing multiple skills as a list
*/
    // Default constructor
	
    public Employee() {
    }
    

    public Employee(String firstName, String lastName, String email, String address, String phone,
			String gender, String role,String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.gender = gender;
		this.role = role;
		this.password=password;
	}


	// Getters and Setters for each field
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
