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

import com.colegiox.entities.Student;
import com.colegiox.services.StudentService;

@RestController
@RequestMapping(value="/students")
public class StudentResource {
	
	@Autowired
	private StudentService service;
	
	@GetMapping
	private ResponseEntity<List<Student>>findAll(){
		
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Student>findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	@PutMapping(value="/{id}")
	public ResponseEntity<Student>update(@PathVariable Integer id, @RequestBody Student student){
		return ResponseEntity.ok().body(service.update(id,student));
	}
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void>delete(@PathVariable Integer id){
		return ResponseEntity.noContent().build();
	}
	@PostMapping
	public ResponseEntity<Student>insert(@RequestBody Student student){
		student=service.insert(student);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(student.getId()).toUri();
		return ResponseEntity.created(uri).build();	}



}
