package com.colegiox.services;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.Instant;
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
import org.springframework.boot.test.context.SpringBootTest;

import com.colegiox.entities.SchoolClass;
import com.colegiox.entities.Student;
import com.colegiox.entities.enums.NumberClass;
import com.colegiox.entities.enums.Shifts;
import com.colegiox.repository.StudentRepository;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
	
	
	private static final SchoolClass SCHOOL_CLASS = new SchoolClass(NumberClass.CLASS_1201,Shifts.MORNING_SHIFT);

	private static final boolean CONDITION = true;

	private static final Instant BIRTH = Instant.parse("2002-02-01T20:43:00Z");

	private static final String EMAIL = "john@gmail.com";

	private static final String NAME = "John Constantine";

	private static final Integer ID = 1;

	@Mock
	private StudentRepository repository;
	
	@InjectMocks
	private StudentService service;
	
	private Student student;
	
	private Optional<Student>optionalStudent;
	
	

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
		optionalStudent= Optional.of(student);
		
	}

	@Test
	void testFindAll() {
		when(repository.findAll()).thenReturn(List.of(student));
		
		List<Student>response= service.findAll();
		
		assertNotNull(response);
		assertNotNull(response.get(0).getSchoolClass());
		assertEquals(Student.class,response.get(0).getClass());
		assertEquals(SchoolClass.class,response.get(0).getSchoolClass().getClass());
		assertEquals(ID,response.get(0).getId());
		assertEquals(NAME,response.get(0).getName());
		assertEquals(EMAIL,response.get(0).getEmail());
		assertEquals(BIRTH,response.get(0).getBirth());
		assertEquals(CONDITION,response.get(0).getCondition());
		assertEquals(1,response.size());
	}

	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

}
