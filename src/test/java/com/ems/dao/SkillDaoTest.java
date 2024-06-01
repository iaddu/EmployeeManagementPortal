package com.ems.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ems.model.Skill;

public class SkillDaoTest {
	//	public Skill findByskillName(String skillName);
	
	private Skill skill;
	
	@Mock
	private SkillDao skillDao;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		skill=new Skill();
		skill.setSkillName("java");
	}
	
	@Test
	public void testFindBySkillName() {
		when(skillDao.findByskillName("java")).thenReturn(skill);
		Skill result=skillDao.findByskillName("java");
		assertNotNull(result);
		assertEquals("java", result.getSkillName());
	}

}
