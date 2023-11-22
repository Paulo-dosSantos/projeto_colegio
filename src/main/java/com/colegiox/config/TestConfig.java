package com.colegiox.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.colegiox.entities.Exam;
import com.colegiox.entities.SchoolClass;
import com.colegiox.entities.SchoolTrimester;
import com.colegiox.entities.Student;
import com.colegiox.entities.enums.NumberClass;
import com.colegiox.entities.enums.NumberTrimester;
import com.colegiox.entities.enums.Shifts;
import com.colegiox.entities.enums.Subjects;
import com.colegiox.repository.ExamRepository;
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
	@Autowired
	private ExamRepository examRepository;

	@Override
	public void run(String... args) throws Exception {
		
			
		SchoolClass schoolclass1= new SchoolClass(NumberClass.CLASS_1201, Shifts.MORNING_SHIFT);
		SchoolClass schoolclass2= new SchoolClass(NumberClass.CLASS_1203, Shifts.MORNING_SHIFT);
		SchoolClass schoolclass3= new SchoolClass(NumberClass.CLASS_2106, Shifts.AFTERNOON_SHIFT);
		
		schoolClassRepository.saveAll(Arrays.asList(schoolclass1,schoolclass2, schoolclass3));
		
		
		Student student1=new Student( "Erick Lanshher", "eriquito@gmail.com",Instant.parse("2002-02-02T00:00:00Z"),true,schoolclass1);
		Student student2=new Student( "Charles Xavier", "xavinho@gmail.com", Instant.parse("2001-05-13T00:00:00Z"),true,schoolclass2);
		
		
		schoolclass1.getStudents().addAll(Arrays.asList(student1,student2));
		studentRepository.saveAll(Arrays.asList(student1,student2));
		SchoolTrimester trimester1= new SchoolTrimester(NumberTrimester.FIRST_TRIMESTER, Instant.parse("2024-02-02T00:00:00Z"),
				Instant.parse("2024-04-02T00:00:00Z"));
		SchoolTrimester trimester3= new SchoolTrimester(NumberTrimester.THIRD_TRIMESTER, Instant.parse("2024-07-02T00:00:00Z"),
				Instant.parse("2024-04-02T00:00:00Z"));
		
		
		trimesterRepository.saveAll(Arrays.asList(trimester1,trimester3));
		
		Exam exam1= new Exam(Subjects.MATHEMATICS,Instant.parse("2024-04-02T00:00:00Z"),trimester1);
		
		Exam exam2= new Exam(Subjects.ART,Instant.parse("2024-04-04T00:00:00Z"),trimester1);
		
		Exam exam3= new Exam(Subjects.BIOLOGY,Instant.parse("2024-04-07T00:00:00Z"),trimester1);
		
		Exam exam4= new Exam(Subjects.CHEMISTRY,Instant.parse("2024-04-09T00:00:00Z"),trimester1);
		
		Exam exam5= new Exam(Subjects.MATHEMATICS,Instant.parse("2024-08-01T00:00:00Z"),trimester3);
		
		Exam exam6= new Exam(Subjects.PHILOSOPHY,Instant.parse("2024-08-02T00:00:00Z"),trimester3);
		
		Exam exam7= new Exam(Subjects.SOCIOLOGY,Instant.parse("2024-08-12T00:00:00Z"),trimester3);
		
		Exam exam8= new Exam(Subjects.PORTUGUESE,Instant.parse("2024-08-13T00:00:00Z"),trimester3);
		
		Exam exam9= new Exam(Subjects.HISTORY,Instant.parse("2024-08-14T00:00:00Z"),trimester3);
		
		Exam exam10= new Exam(Subjects.MATHEMATICS,Instant.parse("2024-02-02T00:00:00Z"),trimester3);
		
		
		
		examRepository.saveAll(Arrays.asList(exam1,exam2,exam3,exam4,exam5,exam6,exam7,exam8,exam9,exam10));
		schoolclass1.getExams().addAll(Arrays.asList(exam1,exam2,exam3,exam4));
		
		schoolclass2.getExams().addAll(Arrays.asList(exam7,exam8,exam9,exam10));
		
		schoolClassRepository.saveAll(Arrays.asList(schoolclass1,schoolclass2, schoolclass3));
		
	}

}
