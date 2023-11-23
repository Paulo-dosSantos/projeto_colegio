package com.colegiox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegiox.entities.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher	, Integer> {
	
	
	
}
