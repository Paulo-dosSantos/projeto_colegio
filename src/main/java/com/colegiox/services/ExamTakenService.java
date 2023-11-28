package com.colegiox.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colegiox.entities.ExamTaken;
import com.colegiox.exceptions.ObjectNotFoundException;
import com.colegiox.repository.ExamTakenRepository;

@Service
public class ExamTakenService {
	
	@Autowired
	private ExamTakenRepository repository;
	
	
	public List<ExamTaken>findByStudentId(Integer id){
		
		List<ExamTaken>exams=  repository.findByStudentId(id);
		
		if(exams.isEmpty()) {
			throw new ObjectNotFoundException("não encontrado");
		}
		return exams;
	
	
	}
	


}
