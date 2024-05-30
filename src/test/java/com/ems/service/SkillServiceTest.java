package com.ems.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ems.dao.SkillDao;
import com.ems.model.Skill;

public class SkillServiceTest {
	 
	@InjectMocks
	private SkillService skillService;
	
	@Mock
	private SkillDao skillDao;
	
	
	@Mock
	private Skill skill;
	
	@BeforeEach
	public void setUp() {
        MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testCreateSkill () {
		skillService.createSkill(skill);
		verify(skillDao,times(1)).save(skill);
	}
	  
	@Test
	public void testGetSkillSet() {
		Set<Skill> skillSet=new HashSet<>();
		Set<String> skillNames=new HashSet<>();
		skillNames.add("java");
		skillSet.add(skill);
		when(skill.getSkillName()).thenReturn("java");
		when(skillDao.findByskillName(skill.getSkillName())).thenReturn(skill);
		
		Set<Skill> result=skillService.getSkillSet(skillNames);
		assertThat(result).isNotNull();
		assertThat(result.iterator().next().getSkillName()).isEqualTo("java");
	}
	
	
}
