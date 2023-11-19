package com.colegiox.resources;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	@PutMapping(value="/{id}")
	public ResponseEntity<SchoolClass>update(@PathVariable Integer id, @RequestBody SchoolClass schoolClass){
		return ResponseEntity.ok().body(service.update(id,schoolClass));
	}
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void>delete(@PathVariable Integer id){
		return ResponseEntity.noContent().build();
	}
	@PostMapping
	public ResponseEntity<SchoolClass>insert(@RequestBody SchoolClass schoolClass){
		schoolClass=service.insert(schoolClass);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(schoolClass.getId()).toUri();
		return ResponseEntity.created(uri).build();	}

}
