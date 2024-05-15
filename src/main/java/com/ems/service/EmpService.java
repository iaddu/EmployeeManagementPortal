package com.ems.service;

import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ems.dao.EmployeeDao;
import com.ems.dao.SkillDao;
import com.ems.model.Employee;
import com.ems.model.Skill;

@Service
public class EmpService {
	@Autowired
	EmployeeDao empDao;
	
	@Autowired
	SkillDao skillDao;
	
	
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
}
