package com.colegiox.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colegiox.entities.SchoolSubject;
import com.colegiox.exceptions.ObjectNotFoundException;
import com.colegiox.repository.SchoolSubjectRepository;

@Service
public class SchoolSubjectService {
	
	@Autowired
	private SchoolSubjectRepository repository;
	
	
	public List<SchoolSubject>findAll(){
		return repository.findAll();
	
	}
	public SchoolSubject findById(Integer id) {
		return repository.findById(id).orElseThrow(()->new ObjectNotFoundException("Objeto não encontrado"));
	}
	


}
