package com.colegiox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegiox.entities.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam	, Integer> {
	
	List<Exam>findByTrimesterId(Integer id);
	
}
