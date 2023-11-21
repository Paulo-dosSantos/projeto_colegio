package com.colegiox.resources;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegiox.entities.Trimester;
import com.colegiox.services.TrimesterService;

@RestController
@RequestMapping(value="/trimesters")
public class TrimesterResource {
	
	@Autowired
	private TrimesterService service;
	
	@GetMapping
	public ResponseEntity<List<Trimester>>findAll(){
		return ResponseEntity.ok().body(service.findAll());
	}
	@GetMapping(value="/{id}")
	public ResponseEntity<Trimester>findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(service.findById(id));
	}

}
