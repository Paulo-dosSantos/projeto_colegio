package com.colegiox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegiox.entities.ExamTaken;

@Repository
public interface ExamTakenRepository extends JpaRepository<ExamTaken	, Integer> {
	
	List<ExamTaken>findByStudentId(Integer id);
	
}
