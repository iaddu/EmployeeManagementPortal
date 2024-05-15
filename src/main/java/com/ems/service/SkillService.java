package com.ems.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ems.dao.SkillDao;
import com.ems.model.Skill;

@Service
public class SkillService {
	@Autowired
	private SkillDao skillDao;
	
	
	@Transactional
	public void createSkill(Skill skill) {
		skillDao.save(skill);
	}
	
	
	public Set<Skill> getSkillSet(Set<String> skillNames) {
		Set<Skill> st=new HashSet<>();
		for(String sk:skillNames) {
			st.add(skillDao.findByskillName(sk));
		}
		return st;
	}
	
}
