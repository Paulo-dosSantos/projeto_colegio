package com.colegiox.resources;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegiox.entities.SchoolClass;
import com.colegiox.services.SchoolClassService;

@RestController
@RequestMapping(value="/classes")
public class SchoolClassResource {
	
	@Autowired
	private SchoolClassService service;
	
	@GetMapping
	public ResponseEntity<List<SchoolClass>>findAll(){
		return ResponseEntity.ok().body(service.findAll());
	}
	@GetMapping(value="/{id}")
	public ResponseEntity<SchoolClass>findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(service.findById(id));
	}

}
