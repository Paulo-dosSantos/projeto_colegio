package com.colegiox.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.colegiox.entities.SchoolClass;
import com.colegiox.entities.Student;
import com.colegiox.entities.enums.NumberClass;
import com.colegiox.repository.SchoolClassRepository;
import com.colegiox.repository.StudentRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private SchoolClassRepository schoolClassRepository;

	@Override
	public void run(String... args) throws Exception {
		
			
		SchoolClass schoolclass1= new SchoolClass(NumberClass.CLASS_1201);
		SchoolClass schoolclass2= new SchoolClass(NumberClass.CLASS_1203);
		SchoolClass schoolclass3= new SchoolClass(NumberClass.CLASS_1205);
		
		Student student1=new Student( "Erick Lanshher", "eriquito@gmail.com",Instant.parse("2002-02-02T00:00:00Z"),true,schoolclass1);
		Student student2=new Student( "Charles Xavier", "xavinho@gmail.com", Instant.parse("2001-05-13T00:00:00Z"),true,schoolclass2);
		
		
		schoolclass1.getStudents().addAll(Arrays.asList(student1,student2));
		schoolClassRepository.saveAll(Arrays.asList(schoolclass1,schoolclass2, schoolclass3));
		studentRepository.saveAll(Arrays.asList(student1,student2));
		
		
	}

}
