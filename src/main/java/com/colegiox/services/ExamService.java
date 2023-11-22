package com.colegiox.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colegiox.entities.Exam;
import com.colegiox.exceptions.ObjectNotFoundException;
import com.colegiox.repository.ExamRepository;

@Service
public class ExamService {
	
	@Autowired
	private ExamRepository repository;
	
	
	public List<Exam>findByTrimesterId(Integer id){
		
		return repository.findByTrimesterId(id);
	}
	public Exam findById(Integer id) {
		return repository.findById(id).orElseThrow(()->new ObjectNotFoundException("Objeto não encontrado"));
	}
	


}
