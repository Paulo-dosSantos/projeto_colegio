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
import org.springframework.http.ResponseEntity;

import com.colegiox.entities.SchoolClass;
import com.colegiox.entities.SchoolSubject;
import com.colegiox.entities.Teacher;
import com.colegiox.entities.enums.Subjects;
import com.colegiox.services.SchoolSubjectService;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class SchoolSubjectResourceTest {
	
	@InjectMocks
	private SchoolSubjectResource resource;
	
	@Mock
	private SchoolSubjectService service;
	
	private static final Integer ID=1;
	
	private static final Subjects NAME= Subjects.MATHEMATICS;
	
	private SchoolSubject schoolSubject;

	@Mock
	private static  SchoolClass schoolClass;
	
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
		schoolSubject= new SchoolSubject(NAME);
		schoolSubject.setId(ID);
		schoolSubject.getClasses().add(schoolClass);
		schoolSubject.getTeachers().add(teacher);
		
		
	}

	@Test
	void testFindAll() {
		when(service.findAll()).thenReturn(List.of(schoolSubject));
		
		ResponseEntity<List<SchoolSubject>>response=resource.findAll();
		
		assertNotNull(response);
		assertNotNull(response.getBody().get(0).getClasses());
		assertNotNull(response.getBody().get(0).getTeachers());
		assertEquals(ResponseEntity.class,response.getClass());
		assertEquals(SchoolSubject.class,response.getBody().get(0).getClass());
		assertEquals(1,response.getBody().get(0).getClasses().size());
		assertEquals(1,response.getBody().get(0).getTeachers().size());
		assertEquals(Teacher.class,response.getBody().get(0).getTeachers().get(0).getClass());
		assertEquals(SchoolClass.class,response.getBody().get(0).getClasses().get(0).getClass());
		
	
	}

	@Test
	void testFindById() {
when(service.findById(anyInt())).thenReturn(schoolSubject);
		
		ResponseEntity<SchoolSubject>response=resource.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getBody().getClasses());
		assertNotNull(response.getBody().getTeachers());
		assertEquals(ResponseEntity.class,response.getClass());
		assertEquals(SchoolSubject.class,response.getBody().getClass());
		assertEquals(1,response.getBody().getClasses().size());
		assertEquals(1,response.getBody().getTeachers().size());
		assertEquals(Teacher.class,response.getBody().getTeachers().get(0).getClass());
		assertEquals(SchoolClass.class,response.getBody().getClasses().get(0).getClass());
		
	}

}
