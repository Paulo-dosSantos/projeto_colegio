package com.colegiox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegiox.entities.SchoolClass;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass	, Integer> {
	
	
	
}
