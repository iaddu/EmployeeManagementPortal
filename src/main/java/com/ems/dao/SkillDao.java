package com.ems.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.model.Skill;

@Repository
public interface SkillDao  extends JpaRepository<Skill, Integer>{
	
	public Skill findByskillName(String skillName);
}
