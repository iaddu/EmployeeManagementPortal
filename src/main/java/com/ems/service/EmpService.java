package com.ems.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ems.dao.EmployeeDao;
import com.ems.dao.ProjectDao;
import com.ems.dao.RequestDao;
import com.ems.dao.SkillDao;
import com.ems.model.Employee;
import com.ems.model.Project;
import com.ems.model.Request;
import com.ems.model.Skill;

@Service
public class EmpService {
	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private SkillDao skillDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private RequestDao requestDao;
	
	@Transactional
	public void createEmp(Employee emp) {
		empDao.save(emp);
	    }
	
	public String createPassword() {
		Random rand=new Random();
		String capital_letters="ABCDEFGHIJKLMNOPQRSTUVWXYX";
		String small_letters="abcdefghijklmnopqrstuvwxyz";
		String numbers="0123456789";
		String values=capital_letters+small_letters+numbers;
		char pass[]=new char[6];
		for(int i=0;i<6;i++){
		pass[i]=values.charAt(rand.nextInt(values.length()));
		}
		String password="";
		for(char it:pass) {
			password+=it;
		}
		return password;
	}
	
	@Transactional
	public void assignSkill(Employee emp, String skill) {
		Skill sk=skillDao.findByskillName(skill);
	    Set<Skill> employeeSkills=emp.getHaveSkills();
	    employeeSkills.add(sk);
	    emp.setHaveSkills(employeeSkills);
	    empDao.save(emp);
	}
	
	@Transactional
	public Employee deleteEmp(String email) {
		System.out.println(1);
		Optional<Employee> optionalEmployee = empDao.findEmployeeByEmail(email);
		if(optionalEmployee.isEmpty())return null;
		System.out.println(2);

		Employee emp=optionalEmployee.get();
		empDao.deleteEmpSkillsByEmployeeId(emp.getEmpId());
		System.out.println(3);

		if(emp.getRole().equals("MANAGER")) {
		Set<Project> haveProject=emp.getHaveProject();
		for(Project pro:haveProject) {
			pro.setHaveManager(null);
		}
		System.out.println(4);

		List<Employee> employeeList=empDao.findAll();
		for(Employee employee:employeeList) {
			if(employee.getManager()!=null && employee.getManager().intValue()==emp.getEmpId())
				employee.setManager(null);
				employee.setAssignedProject(null);
		}
		System.out.println(5);

		}
		System.out.println(6);

		empDao.delete(emp);
		System.out.println(7);
		return emp;
	}
	public List<Employee> getUnassignedEmplyee(){
		List<Employee> allEmployee=empDao.findAll();
		for(Employee emp:allEmployee) {
			if(emp.getAssignedProject()!=null)allEmployee.remove(emp);
		}
		return allEmployee;
	}
	
	public List<Employee> getAllEmployee(){
		List<Employee> allEmployee=empDao.findAll();
		return allEmployee;
	}
	
	public List<Employee> getAllEmployeeOnly(){
		List<Employee> allEmployee=empDao.findAll();
		List<Employee> allEmployeeOnly=new ArrayList<>();
		for(Employee employee:allEmployee) {
			if(employee.getRole().toLowerCase().equals("employee"))allEmployeeOnly.add(employee);
		}
		return allEmployeeOnly;
	}
	
	public List<Employee> getAllManagerOnly(){
		List<Employee> allEmployee=empDao.findAll();
		List<Employee> allEmployeeOnly=new ArrayList<>();
		for(Employee employee:allEmployee) {
			if(employee.getRole().toLowerCase().equals("manager"))allEmployeeOnly.add(employee);
		}
		return allEmployeeOnly;
	}
	public Employee getEmployee(String email) {
		Optional<Employee> optionalEmployee = empDao.findEmployeeByEmail(email);
		if(optionalEmployee.isEmpty())return null;
		return optionalEmployee.get();
	}
	
	//assigning a manager to project
	@Transactional
	public void assignManager(String managerId,String projectId) {
		int empId=Integer.parseInt(managerId);
		int proId=Integer.parseInt(projectId);
		Optional<Employee> optionalEmployee = empDao.findEmployeeByempId(empId);
		Optional<Project> optionalProject=projectDao.findProjectByproId(proId);
        if (optionalEmployee.isPresent() && optionalProject.isPresent()) {
            Employee employee = optionalEmployee.get();
            Project project=optionalProject.get();
            project.setHaveManager(employee);
            projectDao.save(project);
        } else {
            throw new RuntimeException("either employee not found or project not found" );
        }
	}
	//handling assigning  request by manager for an employee
	public void assignThisRequest(String requestId,String employeeId,String manId,String projectId) {
		int empId=Integer.parseInt(employeeId);
		int managerId=Integer.parseInt(manId);
		int proId=Integer.parseInt(projectId);
		int reqId=Integer.parseInt(requestId);
		Optional<Employee> optionalEmployee = empDao.findEmployeeByempId(empId);
		Optional<Project> optionalProject=projectDao.findProjectByproId(proId);
		Optional<Employee> optionalManager=empDao.findEmployeeByempId(managerId);
		Optional<Request> optionalRequest=requestDao.findRequestByreqId(reqId);
		if((optionalEmployee.isEmpty() || optionalManager.isEmpty())
				|| (optionalProject.isEmpty()||optionalRequest.isEmpty())) {
			throw new NoSuchElementException("element missing");
		}
		else {
			Employee employee=optionalEmployee.get();
			Project project=optionalProject.get();
			Employee manager=optionalManager.get();
			Request request=optionalRequest.get();
			employee.setManager(managerId);
			employee.setAssignedProject(project);
			request.setReqStatus("Approved");
			requestDao.save(request);
			empDao.save(employee);
				}
	}
	//handling un-assigning  request by manager for an employee
		public void unassignThisRequest(String requestId,String employeeId) {
			int empId=Integer.parseInt(employeeId);
			int reqId=Integer.parseInt(requestId);
			Optional<Employee> optionalEmployee = empDao.findEmployeeByempId(empId);
			Optional<Request> optionalRequest=requestDao.findRequestByreqId(reqId);
			if(optionalEmployee.isEmpty() ||optionalRequest.isEmpty() ) {
				throw new NoSuchElementException("element missing");
			}
			else {
				Employee employee=optionalEmployee.get();
				Request request=optionalRequest.get();
				employee.setManager(null);
				employee.setAssignedProject(null);
				request.setReqStatus("Approved");
				requestDao.save(request);
				empDao.save(employee);
					}
		}
		//handling to reject request
		public void rejectThisRequest(String requestId) {
			int reqId=Integer.parseInt(requestId);
			Optional<Request> optionalRequest=requestDao.findRequestByreqId(reqId);
			if(optionalRequest.isEmpty()) {
				throw new NoSuchElementException("element missing");
			}
			else {
				Request request=optionalRequest.get();
				request.setReqStatus("Rejected");
				requestDao.save(request);
			}
		}
		
		@Transactional
		public void updateEmp(String empId,String firstName,String lastName,String email,String address,String phone,
				String gender,String role,String dob,Set<Skill> st) {
			Optional<Employee> optionalEmployee=empDao.findEmployeeByempId(Integer.parseInt(empId));
			if(optionalEmployee.isEmpty())throw new NullPointerException();
			Employee employee=optionalEmployee.get();
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setEmail(email);
			employee.setAddress(address);
			employee.setGender(gender);
			employee.setHaveSkills(st);
			employee.setDob(dob);
			employee.setPhone(phone);
			employee.setRole(role);
			empDao.save(employee);
		}
		
		@Transactional
		public void updateSkill(String empId,Set<Skill> st) {
			Optional<Employee> optionalEmployee=empDao.findEmployeeByempId(Integer.parseInt(empId));
			if(optionalEmployee.isEmpty())throw new NullPointerException();
			Employee employee=optionalEmployee.get();
			employee.setHaveSkills(st);
			empDao.save(employee);		
		}
		
		@Transactional
		public void changePassword(String email,String upass) {
			Optional<Employee> optionalEmployee=empDao.findEmployeeByEmail(email);
			if(optionalEmployee.isEmpty())throw new NullPointerException();
			Employee employee=optionalEmployee.get();
			System.out.println(upass);
			employee.setPassword(upass);
			System.out.println(upass);
			empDao.save(employee);
		}
		
		public String getManagerName(Integer empId) {
			if(empId==null)return "NA";
			Optional<Employee> optionalEmployee=empDao.findEmployeeByempId(empId);
			Employee employee=optionalEmployee.get();
			String name=employee.getFirstName()+" "+employee.getLastName();
			return name;
		}
}
