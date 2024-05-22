package com.ems.service;

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
	public void deleteEmp(String email) {
		
		Employee emp=empDao.getByEmail(email);
		empDao.deleteEmpSkillsByEmployeeId(emp.getEmpId());
		empDao.delete(emp);
		System.out.println("deleted success");
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
	
	public Employee getEmployee(String email) {
		return empDao.getByEmail(email);
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
		if((optionalEmployee.isEmpty() || optionalManager.isEmpty())|| (optionalProject.isEmpty()||optionalRequest.isEmpty())) {
			throw new NoSuchElementException("element missing");
		}
		else {
			Employee employee=optionalEmployee.get();
			Project project=optionalProject.get();
			Employee manager=optionalManager.get();
			Request request=optionalRequest.get();
			employee.setManager(managerId);
			employee.setAssignedProject(project);
			request.setReqStatus("approved");
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
				request.setReqStatus("approved");
				requestDao.save(request);
				empDao.save(employee);
					}
		}
}
