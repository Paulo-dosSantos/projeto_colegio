package com.colegiox.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colegiox.entities.Student;
import com.colegiox.exceptions.ObjectNotFoundException;
import com.colegiox.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;
	
	
	public List<Student>findAll(){
		
		return repository.findAll();
	}
	public Student findById(Integer id) {
		return repository.findById(id).orElseThrow(()->new ObjectNotFoundException("Objeto não encontrado"));
	}
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	public Student insert(Student obj) {
		return repository.save(obj);
	}
	public Student update(Integer id, Student obj) {
		findById(id);
		
		return repository.save(obj);
	}

}
