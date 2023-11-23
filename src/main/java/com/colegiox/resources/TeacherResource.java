package com.colegiox.resources;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegiox.entities.Teacher;
import com.colegiox.services.TeacherService;

@RestController
@RequestMapping(value="/teachers")
public class TeacherResource {
	
	@Autowired
	private TeacherService service;
	
	@GetMapping
	public ResponseEntity<List<Teacher>>findAll(){
		return ResponseEntity.ok().body(service.findAll());
	}
	@GetMapping(value="/{id}")
	public ResponseEntity<Teacher>findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(service.findById(id));
	}

}
