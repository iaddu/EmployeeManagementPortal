package com.ems.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ems.dao.ProjectDao;
import com.ems.model.Project;

public class TestProjectService {
	
	@InjectMocks
	private ProjectService projectService;
	
	@Mock
	private ProjectDao projectDao;
	
	@Mock
	private Project project;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	 
	@Test
    public void testCreateService() {
        String projectName = "EMP";
        
        projectService.createService(projectName);
        
        ArgumentCaptor<Project> projectCaptor = ArgumentCaptor.forClass(Project.class);
        verify(projectDao, times(1)).save(projectCaptor.capture());
        
        // Verify the properties of the captured Project object
        Project capturedProject = projectCaptor.getValue();
        assertThat(capturedProject.getProName()).isEqualTo(projectName);
        assertThat(capturedProject.getProId()).isZero(); // Assuming default value is zero or it is set somewhere else
    }
	 
	@Test
	public void testGetAllProject() {
		when(project.getProName()).thenReturn("emp");
		List<Project> projectList=new ArrayList<>();
		projectList.add(project);
		when(projectDao.findAll()).thenReturn(projectList);
		
		List<Project> result=projectService.getAllProject();
		
		assertThat(result).isNotNull();
		assertThat(result.get(0).getProName()).isEqualTo("emp");
	}
	 
	
	@Test
	public void testGetProject() {
		when(project.getProId()).thenReturn(0);
		when(project.getProName()).thenReturn("ems");
		List<Project> projectList=new ArrayList<>();
		projectList.add(project);
		when(projectDao.findAll()).thenReturn(projectList);
		
		projectService.getProject(""+project.getProId());
		for(Project pro:projectList) {
			assertThat(pro.getProName()).isEqualTo("ems");
		}
		
		
	}
	
}
