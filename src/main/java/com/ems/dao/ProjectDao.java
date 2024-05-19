
package com.ems.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.model.Project;

@Repository
public interface ProjectDao extends JpaRepository<Project, Integer>{
	Optional<Project> findProjectByproId(int proId);
}
