package com.colegiox.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.colegiox.entities.Student;
import com.colegiox.repository.StudentRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Student student1=new Student( "Erick Lanshher", "eriquito@gmail.com",Instant.parse("2002-02-02T00:00:00Z"),true);
		Student student2=new Student( "Charles Xavier", "xavinho@gmail.com", Instant.parse("2001-05-13T00:00:00Z"),true);
		
		studentRepository.saveAll(Arrays.asList(student1,student2));
		
		
		
	}

}
