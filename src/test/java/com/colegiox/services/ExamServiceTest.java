package com.colegiox.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.colegiox.entities.Exam;
import com.colegiox.entities.SchoolTrimester;
import com.colegiox.entities.Teacher;
import com.colegiox.entities.enums.NumberTrimester;
import com.colegiox.repository.ExamRepository;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class ExamServiceTest {
	
	@Mock
	private ExamRepository repository;
	
	@InjectMocks
	private ExamService service;
	
	private Exam exam;
	
	private Optional<Exam>optionalExam;
	
	private static final Integer ID= 1;
	
	
	
	private static final Instant DATE= Instant.parse("2024-04-02T00:00:00Z");
	
	private static  SchoolTrimester trimester;
	
	@Mock
	private static Teacher teacher;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startUser();
		
	
	}

	private void startUser() {
		trimester= new SchoolTrimester(NumberTrimester.FIRST_TRIMESTER, Instant.parse("2024-02-02T00:00:00Z"),
				Instant.parse("2024-04-02T00:00:00Z"));
		
		
		
		exam= new Exam( DATE, trimester,teacher);
		exam.setId(ID);
		
		optionalExam= Optional.of(exam);
		trimester.setId(ID);
		
	}

	@Test
	void testFindByTrimesterId() {
		when(repository.findByTrimesterId(ID)).thenReturn(List.of(exam));
		
		List<Exam>response=service.findByTrimesterId(ID);
		
		assertNotNull(response);
		assertNotNull(response.get(0).getTrimester());
		assertNotNull(response.get(0).getTeacher());
		assertEquals(Exam.class,response.get(0).getClass());
		assertEquals(SchoolTrimester.class,response.get(0).getTrimester().getClass());
		assertEquals(Teacher.class,response.get(0).getTeacher().getClass());
		assertEquals(ID,response.get(0).getId());
		
		
	
	
	}

	@Test
	void testFindById() {
		when(repository.findById(anyInt())).thenReturn(optionalExam);
		
		Exam response=service.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getTrimester());
		assertEquals(Exam.class,response.getClass());
		assertEquals(SchoolTrimester.class,response.getTrimester().getClass());
		assertEquals(Teacher.class,response.getTeacher().getClass());
		assertEquals(ID,response.getId());
	
		
	}

}
