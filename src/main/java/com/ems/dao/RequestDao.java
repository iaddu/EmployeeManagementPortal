package com.ems.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.model.Request;

@Repository
public interface RequestDao extends JpaRepository<Request, Integer> {
	Optional<Request> findRequestByreqId(int reqId);

}
