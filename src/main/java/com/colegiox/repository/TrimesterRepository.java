package com.colegiox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegiox.entities.SchoolTrimester;

@Repository
public interface TrimesterRepository extends JpaRepository<SchoolTrimester	, Integer> {
	
	
	
}
