package com.colegiox.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;

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
import org.springframework.http.ResponseEntity;

import com.colegiox.entities.Exam;
import com.colegiox.entities.SchoolClass;
import com.colegiox.entities.SchoolTrimester;
import com.colegiox.entities.enums.NumberClass;
import com.colegiox.entities.enums.NumberTrimester;
import com.colegiox.entities.enums.Shifts;
import com.colegiox.entities.enums.Subjects;
import com.colegiox.services.ExamService;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class ExamResourceTest {
	
	@Mock
	private ExamService service;
	
	@InjectMocks
	private ExamResource resource;
	
	private Exam exam;
	
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
		
		trimester.setId(ID);
		
	}

	@Test
	void testFindByTrimesterId() {
		when(service.findByTrimesterId(anyInt())).thenReturn(List.of(exam));
		
		ResponseEntity<List<Exam>>response= resource.findByTrimesterId(ID);
		
		assertNotNull(response);
		assertNotNull(response.getBody().get(0).getClasses());
		assertNotNull(response.getBody().get(0).getTrimester());
		assertEquals(ResponseEntity.class,response.getClass());
		assertEquals(Exam.class,response.getBody().get(0).getClass());
		assertEquals(SchoolTrimester.class,response.getBody().get(0).getTrimester().getClass());
		assertEquals(SchoolClass.class,response.getBody().get(0).getClasses().get(0).getClass());
		assertEquals(1,response.getBody().get(0).getClasses().size());
		assertEquals(ID,response.getBody().get(0).getId());
		assertEquals(Subjects.class,response.getBody().get(0).getSubject().getClass());
		
	}

	@Test
	void testFindById() {
when(service.findById(anyInt())).thenReturn(exam);
		
		ResponseEntity<Exam> response= resource.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getBody().getClasses());
		assertNotNull(response.getBody().getTrimester());
		assertEquals(ResponseEntity.class,response.getClass());
		assertEquals(Exam.class,response.getBody().getClass());
		assertEquals(SchoolTrimester.class,response.getBody().getTrimester().getClass());
		assertEquals(SchoolClass.class,response.getBody().getClasses().get(0).getClass());
		assertEquals(1,response.getBody().getClasses().size());
		assertEquals(ID,response.getBody().getId());
		assertEquals(Subjects.class,response.getBody().getSubject().getClass());
		

	}

}
