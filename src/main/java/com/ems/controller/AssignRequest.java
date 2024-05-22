package com.ems.controller;

public class AssignRequest {
	private String reqId;
	private String proId;
	private String managerId;
	private String empId;
	
	
	public AssignRequest(String reqId,String proId, String managerId, String empId) {
		super();
		this.proId = proId;
		this.managerId = managerId;
		this.empId = empId;
		this.reqId=reqId;
	}

	public String getProId() {
		return proId;
	}
	
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	
	

}
