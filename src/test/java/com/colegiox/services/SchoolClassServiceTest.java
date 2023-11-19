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

import com.colegiox.entities.SchoolClass;
import com.colegiox.entities.Student;
import com.colegiox.entities.enums.NumberClass;
import com.colegiox.entities.enums.Shifts;
import com.colegiox.exceptions.ObjectNotFoundException;
import com.colegiox.repository.SchoolClassRepository;
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class SchoolClassServiceTest {
	
	private static final Integer ID = 1;

	@Mock
	private SchoolClassRepository repository;
	
	@InjectMocks
	private SchoolClassService service;
	
	private SchoolClass schoolClass;
	
	private Student student;
	
	private Optional<SchoolClass>optionalSchoolClass;
	
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
		schoolClass= new SchoolClass(NumberClass.CLASS_1201, Shifts.MORNING_SHIFT);
		schoolClass.setId(ID);
		
		 student=new Student( "Erick Lanshher", "eriquito@gmail.com",Instant.parse("2002-02-02T00:00:00Z"),true,schoolClass);
		 schoolClass.getStudents().add(student);
			optionalSchoolClass= Optional.of(schoolClass);
	}

	@Test
	void testFindAll() {
		when(repository.findAll()).thenReturn(List.of(schoolClass));
		
		List<SchoolClass>response= service.findAll();
		
		assertNotNull(response);
		assertNotNull(response.get(0).getStudents());
		assertEquals(SchoolClass.class,response.get(0).getClass());
		assertEquals(Student.class,response.get(0).getStudents().get(0).getClass());
		assertEquals(ID,response.get(0).getId());
		assertEquals(NumberClass.CLASS_1201,response.get(0).getNumber());
		assertEquals(Shifts.MORNING_SHIFT,response.get(0).getShift());
	}

	@Test
	void testFindById() {
		when(repository.findById(anyInt())).thenReturn(optionalSchoolClass);
		
		SchoolClass response= service.findById(ID);
		assertNotNull(response);
		assertNotNull(response.getStudents());
		assertEquals(SchoolClass.class,response.getClass());
		assertEquals(Student.class,response.getStudents().get(0).getClass());
		assertEquals(ID,response.getId());
		assertEquals(NumberClass.CLASS_1201,response.getNumber());
		assertEquals(Shifts.MORNING_SHIFT,response.getShift());
		
		
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

	}

}
