package com.colegiox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.colegiox.entities.SchoolClass;

public interface SchoolClassRepository extends JpaRepository<SchoolClass	, Integer> {

}
