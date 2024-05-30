package com.ems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="request")
public class Request {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="reqId")
	private int reqId;
	@Column(name="managerId")
	private int managerId;
	@Column(name="empId")
	private int empId;
	@Column(name="proId")
	private int proId;
	//pending approved rejected
	@Column(name="reqStatus")
	private String reqStatus;
	//assign unassign
	@Column(name="toStatus")
	private String toStatus;
	
	
	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Request(int managerId, int empId, int proId, String reqStatus, String toStatus) {
		super();
		this.managerId = managerId;
		this.empId = empId;
		this.proId = proId;
		this.reqStatus = reqStatus;
		this.toStatus = toStatus;
	}
	
	
	public int getReqId() {
		return reqId;
	}
	public void setReqId(int reqId) {
		this.reqId = reqId;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getToStatus() {
		return toStatus;
	}
	public void setToStatus(String toStatus) {
		this.toStatus = toStatus;
	}


	@Override
	public String toString() {
		return "Request [reqId=" + reqId + ", managerId=" + managerId + ", empId=" + empId + ", proId=" + proId
				+ ", reqStatus=" + reqStatus + ", toStatus=" + toStatus + "]";
	}
	
	
}
