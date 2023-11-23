package com.colegiox.resources;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegiox.entities.SchoolSubject;
import com.colegiox.services.SchoolSubjectService;

@RestController
@RequestMapping(value="/subjects")
public class SchoolSubjectResource {
	
	@Autowired
	private SchoolSubjectService service;
	
	@GetMapping
	public ResponseEntity<List<SchoolSubject>>findAll(){
		return ResponseEntity.ok().body(service.findAll());
	}
	@GetMapping(value="/{id}")
	public ResponseEntity<SchoolSubject>findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(service.findById(id));
	}

}
