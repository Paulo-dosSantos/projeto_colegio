package com.colegiox.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colegiox.entities.SchoolClass;
import com.colegiox.exceptions.ObjectNotFoundException;
import com.colegiox.repository.SchoolClassRepository;

@Service
public class SchoolClassService {
	
	@Autowired
	private SchoolClassRepository repository;
	
	
	public List<SchoolClass>findAll(){
		
		return repository.findAll();
	}
	public SchoolClass findById(Integer id) {
		return repository.findById(id).orElseThrow(()->new ObjectNotFoundException("Objeto não encontrado"));
	}
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	public SchoolClass insert(SchoolClass obj) {
		return repository.save(obj);
	}
	public SchoolClass update(Integer id, SchoolClass obj) {
		findById(id);
		
		return repository.save(obj);
	}


}
