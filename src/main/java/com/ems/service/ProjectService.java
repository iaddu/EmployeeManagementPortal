
package com.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ems.dao.ProjectDao;
import com.ems.model.Project;

@Service
public class ProjectService {
	
	@Autowired
	public ProjectDao projectDao;
	
	@Transactional
	public void createService(String proName) {
		Project project=new Project(proName);
		projectDao.save(project);
	}
	
}
