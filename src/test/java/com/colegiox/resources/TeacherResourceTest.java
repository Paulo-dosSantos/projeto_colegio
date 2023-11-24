package com.colegiox.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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

import com.colegiox.entities.Exam;
import com.colegiox.entities.SchoolClass;
import com.colegiox.entities.SchoolSubject;
import com.colegiox.entities.Teacher;
import com.colegiox.services.TeacherService;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class TeacherResourceTest {
	
	private static final String EMAIL = "ironzinho@gmail.com";

	private static final String NAME = "Tony Stark";

	@InjectMocks
	private TeacherResource resource;
	
	@Mock
	private TeacherService service;
	
	private Teacher teacher;
	
	
	
	@Mock
	private SchoolClass schoolClass;
	
	@Mock
	private Exam exam;
	@Mock
	private SchoolSubject schoolSubject;
	
	private static final Integer ID=1;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	private void startUser() {
		teacher=new Teacher(NAME, EMAIL, schoolSubject);
		teacher.getExams().add(exam);
		teacher.setId(ID);
		
	}

	@Test
	void testFindAll() {
		when(service.findAll()).thenReturn(List.of(teacher));
		
		ResponseEntity<List<Teacher>>response=resource.findAll();
		
		assertNotNull(response);
		assertNotNull(response.getBody().get(0).getExams());
		assertEquals(ResponseEntity.class,response.getClass());
		assertEquals(Teacher.class,response.getBody().get(0).getClass());
		assertEquals(1,response.getBody().size());
		assertEquals(ID,response.getBody().get(0).getId());
		assertEquals(NAME,response.getBody().get(0).getName());
		assertEquals(EMAIL,response.getBody().get(0).getEmail());
		assertEquals(HttpStatus.OK,response.getStatusCode());
		
		
	
	}

	@Test
	void testFindById() {
		when(service.findById(anyInt())).thenReturn(teacher);
		
		ResponseEntity<Teacher> response=resource.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getBody().getExams());
		assertEquals(ResponseEntity.class,response.getClass());
		assertEquals(Teacher.class,response.getBody().getClass());
		assertEquals(ID,response.getBody().getId());
		assertEquals(NAME,response.getBody().getName());
		assertEquals(EMAIL,response.getBody().getEmail());
		assertEquals(HttpStatus.OK,response.getStatusCode());
		
	}

}
