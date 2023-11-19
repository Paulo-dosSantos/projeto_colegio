package com.colegiox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegiox.entities.Trimester;

@Repository
public interface TrimesterRepository extends JpaRepository<Trimester	, Integer> {
	
	
	
}
