package com.colegiox.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colegiox.entities.Teacher;
import com.colegiox.exceptions.ObjectNotFoundException;
import com.colegiox.repository.TeacherRepository;

@Service
public class TeacherService {
	
	@Autowired
	private TeacherRepository repository;
	
	
	public List<Teacher>findAll(){
		return repository.findAll();
	
	}
	public Teacher findById(Integer id) {
		return repository.findById(id).orElseThrow(()->new ObjectNotFoundException("Objeto não encontrado"));
	}
	


}
