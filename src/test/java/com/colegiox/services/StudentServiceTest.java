package com.colegiox.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.colegiox.entities.SchoolClass;
import com.colegiox.entities.Student;
import com.colegiox.entities.enums.NumberClass;
import com.colegiox.entities.enums.Shifts;
import com.colegiox.exceptions.ObjectNotFoundException;
import com.colegiox.repository.StudentRepository;


@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
	
	
	private static final SchoolClass SCHOOL_CLASS = new SchoolClass(NumberClass.CLASS_1201,Shifts.MORNING_SHIFT);

	private static final boolean CONDITION = true;

	private static final Instant BIRTH = Instant.parse("2002-02-01T20:43:00Z");

	private static final String EMAIL = "john@gmail.com";

	private static final String NAME = "John Constantine";

	private static final Integer ID = 1;

	private static final String EXCECAO = "Objeto não encontrado";

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
		when(repository.findById(anyInt())).thenReturn(optionalStudent);
		
		Student response= service.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getSchoolClass());
		assertEquals(Student.class,response.getClass());
		assertEquals(SchoolClass.class,response.getSchoolClass().getClass());
		assertEquals(ID,response.getId());
		assertEquals(NAME,response.getName());
		assertEquals(EMAIL,response.getEmail());
		assertEquals(BIRTH,response.getBirth());
		assertEquals(CONDITION,response.getCondition());
	}

	@Test
	void testDelete() {
		doNothing().when(repository).deleteById(ID);
		service.delete(ID);
		verify(repository,times(1)).deleteById(anyInt());
	}

	@Test
	void testInsert() {
		when(repository.save(any(Student.class))).thenReturn(student);
		
		Student response= service.insert(student);
		assertNotNull(response);
		assertNotNull(response.getSchoolClass());
		assertEquals(Student.class,response.getClass());
		assertEquals(SchoolClass.class,response.getSchoolClass().getClass());
		assertEquals(ID,response.getId());
		assertEquals(NAME,response.getName());
		assertEquals(EMAIL,response.getEmail());
		assertEquals(BIRTH,response.getBirth());
		assertEquals(CONDITION,response.getCondition());
	}

	@Test
	void testUpdate() {
		when(repository.findById(anyInt())).thenReturn(optionalStudent);
		when(repository.save(any(Student.class))).thenReturn(student);
		Student response= service.update(ID,student);
		assertNotNull(response);
		assertNotNull(response.getSchoolClass());
		assertEquals(Student.class,response.getClass());
		assertEquals(SchoolClass.class,response.getSchoolClass().getClass());
		assertEquals(ID,response.getId());
		assertEquals(NAME,response.getName());
		assertEquals(EMAIL,response.getEmail());
		assertEquals(BIRTH,response.getBirth());
		assertEquals(CONDITION,response.getCondition());
		
	}
	@Test
	void testFindByIdReturnObjectNotFoundException() {
		when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(EXCECAO));
		
		try {
			service.findById(ID);
		}
		catch(Exception ex) {
			assertEquals(ObjectNotFoundException.class, ex.getClass());
			assertEquals(EXCECAO,ex.getMessage());
		}

	}}
