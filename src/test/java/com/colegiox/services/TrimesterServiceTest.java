package com.colegiox.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.colegiox.entities.SchoolTrimester;
import com.colegiox.entities.enums.NumberTrimester;
import com.colegiox.exceptions.ObjectNotFoundException;
import com.colegiox.repository.TrimesterRepository;


@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class TrimesterServiceTest {
	
	@InjectMocks
	private TrimesterService service;
	
	@Mock
	private TrimesterRepository repository;
	
	private SchoolTrimester trimester;
	
	private Optional<SchoolTrimester>optionalTrimester;
	
	private static final Integer ID= 1;
	private static final NumberTrimester number= NumberTrimester.FIRST_TRIMESTER;
	private static final Instant BENNING=Instant.parse("2023-02-02T00:00:00Z");
	private static final Instant END= Instant.parse("2002-04-02T00:00:00Z");
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
		
		trimester= new SchoolTrimester(number, BENNING, END);
		trimester.setId(ID);
		optionalTrimester= Optional.of(trimester);
		
		
	}

	@Test
	void testFindAll() {
		
		when(repository.findAll()).thenReturn(List.of(trimester));
		
		List<SchoolTrimester>response= service.findAll();
		
		assertNotNull(response);
		assertEquals(SchoolTrimester.class,response.get(0).getClass());
		assertEquals(1,response.size());
		assertEquals(ID,response.get(0).getId());
		assertEquals(NumberTrimester.FIRST_TRIMESTER,number);
		assertEquals(BENNING, response.get(0).getBenning_date());
		assertEquals(END,response.get(0).getEnd_date());
	}

	@Test
	void testFindById() {
when(repository.findById(anyInt())).thenReturn(optionalTrimester);
		
		SchoolTrimester response= service.findById(ID);
		
		assertNotNull(response);
		assertEquals(SchoolTrimester.class,response.getClass());
		
		assertEquals(ID,response.getId());
		assertEquals(NumberTrimester.FIRST_TRIMESTER,number);
		assertEquals(BENNING, response.getBenning_date());
		assertEquals(END,response.getEnd_date());
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
