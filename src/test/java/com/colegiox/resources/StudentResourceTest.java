package com.colegiox.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.colegiox.entities.SchoolClass;
import com.colegiox.entities.Student;
import com.colegiox.entities.enums.NumberClass;
import com.colegiox.entities.enums.Shifts;
import com.colegiox.services.StudentService;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class StudentResourceTest {
	
	@Mock
	private StudentService service;
	
	@InjectMocks
	private StudentResource resource;
	
	private Student student;
	
	
	private static final SchoolClass SCHOOL_CLASS = new SchoolClass(NumberClass.CLASS_1201,Shifts.MORNING_SHIFT);

	private static final boolean CONDITION = true;

	private static final Instant BIRTH = Instant.parse("2002-02-01T20:43:00Z");

	private static final String EMAIL = "john@gmail.com";

	private static final String NAME = "John Constantine";

	private static final Integer ID = 1;

	private static final String EXCECAO = "Objeto não encontrado";


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	private void startUser() {
		
		student= new Student(NAME, EMAIL, BIRTH, CONDITION, SCHOOL_CLASS);
		student.setId(ID);
		
		
	}
	
	@Test
	void testFindAll() {
		
		when(service.findAll()).thenReturn(List.of(student));
		
		ResponseEntity<List<Student>> response= resource.findAll();
		
		assertNotNull(response);
		assertEquals(ResponseEntity.class,response.getClass());
		assertEquals(Student.class,response.getBody().get(0).getClass());
		assertEquals(ID,response.getBody().get(0).getId());
		assertEquals(NAME,response.getBody().get(0).getName());
		assertEquals(EMAIL,response.getBody().get(0).getEmail());
		assertEquals(BIRTH,response.getBody().get(0).getBirth());
		assertEquals(CONDITION,response.getBody().get(0).getCondition());
		assertEquals(1,response.getBody().size());
		assertEquals(HttpStatus.OK,response.getStatusCode());
		
	}


	@Test
	void testFindById() {
		
		when(service.findById(anyInt())).thenReturn(student);
		
		ResponseEntity<Student>response=resource.findById(ID);
		assertNotNull(response);
		assertEquals(ResponseEntity.class,response.getClass());
		assertEquals(Student.class,response.getBody().getClass());
		assertEquals(ID,response.getBody().getId());
		assertEquals(NAME,response.getBody().getName());
		assertEquals(EMAIL,response.getBody().getEmail());
		assertEquals(BIRTH,response.getBody().getBirth());
		assertEquals(CONDITION,response.getBody().getCondition());
		assertEquals(HttpStatus.OK,response.getStatusCode());
		
		
		
	}

	@Test
	void testUpdate() {
		
		when(service.update(ID, student)).thenReturn(student);
		
		ResponseEntity<Student>response= resource.update(ID, student);
		assertNotNull(response);
		assertEquals(ResponseEntity.class,response.getClass());
		assertEquals(Student.class,response.getBody().getClass());
		assertEquals(ID,response.getBody().getId());
		assertEquals(NAME,response.getBody().getName());
		assertEquals(EMAIL,response.getBody().getEmail());
		assertEquals(BIRTH,response.getBody().getBirth());
		assertEquals(CONDITION,response.getBody().getCondition());
		assertEquals(HttpStatus.OK,response.getStatusCode());
		
		
	
	}

	@Test
	void testDelete() {
		doNothing().when(service).delete(anyInt());
		ResponseEntity<Void>response=resource.delete(ID);
		
		
		verify(service,times(1)).delete(anyInt());
		assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());

	}

	@Test
	void testInsert() {
		fail("Not yet implemented");
	}
	
	
}
