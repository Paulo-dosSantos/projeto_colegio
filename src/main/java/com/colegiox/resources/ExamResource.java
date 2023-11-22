package com.colegiox.resources;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegiox.entities.Exam;
import com.colegiox.services.ExamService;

@RestController
@RequestMapping(value="/exams")
public class ExamResource {
	
	@Autowired
	private ExamService service;
	
	@GetMapping(value="/trimester/{id}")
	public ResponseEntity<List<Exam>>findByTrimesterId(@PathVariable Integer id){
		return ResponseEntity.ok().body(service.findByTrimesterId(id));
	}
	@GetMapping(value="/{id}")
	public ResponseEntity<Exam>findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(service.findById(id));
	}

}
