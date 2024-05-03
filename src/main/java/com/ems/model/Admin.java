package com.ems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "adminlogin")
public class Admin {
	@Id    
	@GeneratedValue(strategy = GenerationType.AUTO)  // Auto-generate the ID
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="password")
	private String password;
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "AdminLogin [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	
	
	

}
