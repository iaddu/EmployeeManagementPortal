package com.ems.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ems.model.Project;

@SpringBootTest
public class ProjectDaoTest {

	
    private ProjectDao projectDaoMock;
    
    private Project project;

    @BeforeEach
    public void setUp() {
        projectDaoMock = mock(ProjectDao.class);
        project=new Project();
        project.setProId(1);
        project.setProName("SampleProject");


    }

    @Test
    public void testFindProjectByproId() {
        when(projectDaoMock.findProjectByproId(1)).thenReturn(Optional.of(project));
        Optional<Project> result = projectDaoMock.findProjectByproId(1);
        assertEquals(Optional.of(project), result);
    }

    @Test
    public void testFindProjectByproName() {
        when(projectDaoMock.findProjectByproName("SampleProject")).thenReturn(project);
        Project result = projectDaoMock.findProjectByproName("SampleProject");
        assertEquals(project, result);
    }
}
