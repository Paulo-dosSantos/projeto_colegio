package com.colegiox.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colegiox.entities.Trimester;
import com.colegiox.exceptions.ObjectNotFoundException;
import com.colegiox.repository.TrimesterRepository;

@Service
public class TrimesterService {
	
	@Autowired
	private TrimesterRepository repository;
	
	
	public List<Trimester>findAll(){
		
		return repository.findAll();
	}
	public Trimester findById(Integer id) {
		return repository.findById(id).orElseThrow(()->new ObjectNotFoundException("Objeto não encontrado"));
	}
	


}
