package com.colegiox.services;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.colegiox.entities.SchoolClass;
import com.colegiox.entities.SchoolSubject;
import com.colegiox.entities.Teacher;
import com.colegiox.entities.enums.Subjects;
import com.colegiox.exceptions.ObjectNotFoundException;
import com.colegiox.repository.SchoolSubjectRepository;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class SchoolSubjectServiceTest {
	
	@InjectMocks
	private SchoolSubjectService service;
	
	@Mock
	private SchoolSubjectRepository repository;
	
	private SchoolSubject schoolSubject;
	
	private Optional<SchoolSubject>optionalSchoolSubject;
	
	private static final Integer ID=1;
	private static final String EXCECAO = "Objeto não encontrado";
	
	private static final Subjects NAME= Subjects.MATHEMATICS;
	
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
		optionalSchoolSubject=Optional.of(schoolSubject);
	}

	@Test
	void testFindAll() {
		when(repository.findAll()).thenReturn(List.of(schoolSubject));
		
		List<SchoolSubject>response=service.findAll();
		
		assertNotNull(response);
		assertNotNull(response.get(0).getClasses());
		assertNotNull(response.get(0).getTeachers());
		assertEquals(1,response.get(0).getClasses().size());
		assertEquals(1,response.get(0).getTeachers().size());
		assertEquals(SchoolSubject.class,response.get(0).getClass());
		assertEquals(NAME,response.get(0).getName());
		assertEquals(ID,response.get(0).getId());
	}

	@Test
	void testFindById() {
		when(repository.findById(anyInt())).thenReturn(optionalSchoolSubject);
		
		SchoolSubject response= service.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getClasses());
		assertNotNull(response.getTeachers());
		assertEquals(1,response.getClasses().size());
		assertEquals(1,response.getTeachers().size());
		assertEquals(SchoolSubject.class,response.getClass());
		assertEquals(NAME,response.getName());
		assertEquals(ID,response.getId());
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
