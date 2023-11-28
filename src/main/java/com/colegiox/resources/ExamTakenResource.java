package com.colegiox.resources;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegiox.entities.Exam;
import com.colegiox.entities.ExamTaken;
import com.colegiox.services.ExamTakenService;

@RestController
@RequestMapping(value="/exams")
public class ExamTakenResource {
	
	@Autowired
	private ExamTakenService service;
	
	@GetMapping(value="/student/{id}")
	public ResponseEntity<List<ExamTaken>>findByTrimesterId(@PathVariable Integer id){
		return ResponseEntity.ok().body(service.findByStudentId(id));
	}
	

}
