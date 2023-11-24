package com.colegiox.services;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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
import com.colegiox.entities.SchoolSubject;
import com.colegiox.entities.Teacher;
import com.colegiox.repository.TeacherRepository;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {
	
	private static final String EMAIL = "ironzinho@gmail.com";

	private static final String NAME = "Tony Stark";

	@InjectMocks
	private TeacherService service;
	
	@Mock
	private TeacherRepository repository;
	
	private Teacher teacher;
	
	private Optional<Teacher>optionalTeacher;
	
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
		optionalTeacher=Optional.of(teacher);
	}

	@Test
	void testFindAll() {
		when(repository.findAll()).thenReturn(List.of(teacher));
		
		List<Teacher>response=service.findAll();
		
		assertNotNull(response);
		assertNotNull(response.get(0).getSchoolSubject());
		assertNotNull(response.get(0).getExams());
		assertEquals(Teacher.class,response.get(0).getClass());
		assertEquals(SchoolSubject.class,response.get(0).getSchoolSubject().getClass());
		assertEquals(NAME,response.get(0).getName());
		assertEquals(EMAIL,response.get(0).getEmail());
	}

	@Test
	void testFindById() {
		when(repository.findById(anyInt())).thenReturn(optionalTeacher);
		
		Teacher response=service.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getSchoolSubject());
		assertNotNull(response.getExams());
		assertEquals(Teacher.class,response.getClass());
		assertEquals(SchoolSubject.class,response.getSchoolSubject().getClass());
		assertEquals(NAME,response.getName());
		assertEquals(EMAIL,response.getEmail());
	}

}
