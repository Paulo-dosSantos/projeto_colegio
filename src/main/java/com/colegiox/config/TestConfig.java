package com.colegiox.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.colegiox.entities.SchoolClass;
import com.colegiox.entities.Student;
import com.colegiox.entities.Trimester;
import com.colegiox.entities.enums.NumberClass;
import com.colegiox.entities.enums.NumberTrimester;
import com.colegiox.entities.enums.Shifts;
import com.colegiox.repository.SchoolClassRepository;
import com.colegiox.repository.StudentRepository;
import com.colegiox.repository.TrimesterRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private SchoolClassRepository schoolClassRepository;
	
	@Autowired
	private TrimesterRepository trimesterRepository;

	@Override
	public void run(String... args) throws Exception {
		
			
		SchoolClass schoolclass1= new SchoolClass(NumberClass.CLASS_1201, Shifts.MORNING_SHIFT);
		SchoolClass schoolclass2= new SchoolClass(NumberClass.CLASS_1203, Shifts.MORNING_SHIFT);
		SchoolClass schoolclass3= new SchoolClass(NumberClass.CLASS_2106, Shifts.AFTERNOON_SHIFT);
		
		Student student1=new Student( "Erick Lanshher", "eriquito@gmail.com",Instant.parse("2002-02-02T00:00:00Z"),true,schoolclass1);
		Student student2=new Student( "Charles Xavier", "xavinho@gmail.com", Instant.parse("2001-05-13T00:00:00Z"),true,schoolclass2);
		
		
		schoolclass1.getStudents().addAll(Arrays.asList(student1,student2));
		schoolClassRepository.saveAll(Arrays.asList(schoolclass1,schoolclass2, schoolclass3));
		studentRepository.saveAll(Arrays.asList(student1,student2));
		Trimester trimester1= new Trimester(NumberTrimester.FIRST_TRIMESTER, Instant.parse("2023-02-02T00:00:00Z"),
				Instant.parse("2002-04-02T00:00:00Z"));
		
		
		trimesterRepository.save(trimester1);
		
	}

}
