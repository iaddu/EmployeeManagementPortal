
package com.ems.service;

import java.util.List;

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
	
	public List<Project> getAllProject(){
		return projectDao.findAll();
	}
	
	public Project getProject(String proId) {
		List<Project> projects=projectDao.findAll();
		for(Project project:projects) {
			if(project.getProId()==Integer.parseInt(proId))return project;
		}
		return null;
	}
}
