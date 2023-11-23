package com.colegiox.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.colegiox.entities.Exam;
import com.colegiox.entities.SchoolClass;
import com.colegiox.entities.SchoolSubject;
import com.colegiox.entities.SchoolTrimester;
import com.colegiox.entities.Student;
import com.colegiox.entities.Teacher;
import com.colegiox.entities.enums.NumberClass;
import com.colegiox.entities.enums.NumberTrimester;
import com.colegiox.entities.enums.Shifts;
import com.colegiox.entities.enums.Subjects;
import com.colegiox.repository.ExamRepository;
import com.colegiox.repository.SchoolClassRepository;
import com.colegiox.repository.SchoolSubjectRepository;
import com.colegiox.repository.StudentRepository;
import com.colegiox.repository.TeacherRepository;
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
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private SchoolSubjectRepository schoolSubjectRepository;
	
	
	
	

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
		
		
		SchoolSubject mathematics= new SchoolSubject(Subjects.MATHEMATICS);
		
		SchoolSubject art= new SchoolSubject(Subjects.ART);
		
		SchoolSubject biology= new SchoolSubject(Subjects.BIOLOGY);
		
		SchoolSubject chemistry= new SchoolSubject(Subjects.CHEMISTRY);
		
		SchoolSubject philosophy= new SchoolSubject(Subjects.PHILOSOPHY);
		
		SchoolSubject sociology= new SchoolSubject(Subjects.SOCIOLOGY);
		
		SchoolSubject portuguese= new SchoolSubject(Subjects.PORTUGUESE);
		
		SchoolSubject history= new SchoolSubject(Subjects.HISTORY);
		
		schoolSubjectRepository.saveAll(Arrays.asList(mathematics,
				art,biology,chemistry,philosophy,sociology,portuguese,
				history));
		
		
		Teacher teacher1= new Teacher("Stephen Strange","caraestranho@gmail.com",art);
		Teacher teacher2= new Teacher("Steve Rogers","america@gmail.com",history);
		Teacher teacher3= new Teacher("Bruce Banner","green@gmail.com",biology);
		Teacher teacher4= new Teacher("Hank McCoy","ferinha@gmail.com",chemistry);
		Teacher teacher5= new Teacher("Robert Downey Jr.","ferrodohome@gmail.com",mathematics);
		Teacher teacher6= new Teacher("Charles Xavier","x@gmail.com",sociology);
		Teacher teacher7= new Teacher("Clark Kent","esperanca@gmail.com",philosophy);
		Teacher teacher8= new Teacher("Beatriz daCosta","fogo@gmail.com",portuguese);
		
		teacherRepository.saveAll(Arrays.asList(teacher1,teacher2,
				teacher3,teacher4,teacher5,teacher6,teacher7,teacher8));
		
		
		
		
		
		
		
		
		
		
		
Exam exam1= new Exam(Instant.parse("2024-04-02T00:00:00Z"),trimester1,teacher5);
		
		Exam exam2= new Exam(Instant.parse("2024-04-04T00:00:00Z"),trimester1,teacher1);
		
		Exam exam3= new Exam(Instant.parse("2024-04-07T00:00:00Z"),trimester1,teacher3);
		
		Exam exam4= new Exam(Instant.parse("2024-04-09T00:00:00Z"),trimester1,teacher4);
		
		Exam exam5= new Exam(Instant.parse("2024-08-01T00:00:00Z"),trimester3,teacher7);
		
		Exam exam6= new Exam(Instant.parse("2024-08-02T00:00:00Z"),trimester3,teacher6);
		
		Exam exam7= new Exam(Instant.parse("2024-08-12T00:00:00Z"),trimester3,teacher8);
		
		Exam exam8= new Exam(Instant.parse("2024-08-13T00:00:00Z"),trimester3,teacher8);
		
		Exam exam9= new Exam(Instant.parse("2024-08-14T00:00:00Z"),trimester3,teacher2);
		
		Exam exam10= new Exam(Instant.parse("2024-02-02T00:00:00Z"),trimester3,teacher5);
		
		
		examRepository.saveAll(Arrays.asList(exam1,exam2,exam3,exam4,exam5,exam6,exam7,exam8,exam9,exam10));
		
		schoolClassRepository.saveAll(Arrays.asList(schoolclass1,schoolclass2, schoolclass3));
		
	}

}
