package com.colegiox.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colegiox.entities.SchoolTrimester;
import com.colegiox.exceptions.ObjectNotFoundException;
import com.colegiox.repository.TrimesterRepository;

@Service
public class TrimesterService {
	
	@Autowired
	private TrimesterRepository repository;
	
	
	public List<SchoolTrimester>findAll(){
		
		return repository.findAll();
	}
	public SchoolTrimester findById(Integer id) {
		return repository.findById(id).orElseThrow(()->new ObjectNotFoundException("Objeto não encontrado"));
	}
	


}
