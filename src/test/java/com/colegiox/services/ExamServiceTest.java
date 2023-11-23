package com.colegiox.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
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
import com.colegiox.entities.SchoolClass;
import com.colegiox.entities.SchoolTrimester;
import com.colegiox.entities.enums.NumberClass;
import com.colegiox.entities.enums.NumberTrimester;
import com.colegiox.entities.enums.Shifts;
import com.colegiox.entities.enums.Subjects;
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
	
	private static final Subjects SUBJECT= Subjects.BIOLOGY;
	
	private static final Instant DATE= Instant.parse("2024-04-02T00:00:00Z");
	
	private static  SchoolTrimester trimester;
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
		
		SchoolClass schoolClass= new SchoolClass(NumberClass.CLASS_1201, Shifts.MORNING_SHIFT);
		
		exam= new Exam(SUBJECT, DATE, trimester);
		exam.setId(ID);
		exam.getClasses().add(schoolClass);
		optionalExam= Optional.of(exam);
		trimester.setId(ID);
		
	}

	@Test
	void testFindByTrimesterId() {
		when(repository.findByTrimesterId(ID)).thenReturn(List.of(exam));
		
		List<Exam>response=service.findByTrimesterId(ID);
		
		assertNotNull(response);
		assertNotNull(response.get(0).getTrimester());
		assertNotNull(response.get(0).getClasses());
		assertEquals(Exam.class,response.get(0).getClass());
		assertEquals(SchoolTrimester.class,response.get(0).getTrimester().getClass());
		assertEquals(1,response.get(0).getClasses().size());
		assertEquals(ID,response.get(0).getId());
		assertEquals(Subjects.class,response.get(0).getSubject().getClass());
		
	
	
	}

	@Test
	void testFindById() {
		when(repository.findById(anyInt())).thenReturn(optionalExam);
		
		Exam response=service.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getTrimester());
		assertNotNull(response.getClasses());
		assertEquals(Exam.class,response.getClass());
		assertEquals(SchoolTrimester.class,response.getTrimester().getClass());
		assertEquals(1,response.getClasses().size());
		assertEquals(ID,response.getId());
		assertEquals(Subjects.class,response.getSubject().getClass());
		
		
	}

}
