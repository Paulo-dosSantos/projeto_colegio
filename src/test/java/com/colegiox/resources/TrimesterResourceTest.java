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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.colegiox.entities.Trimester;
import com.colegiox.entities.enums.NumberTrimester;
import com.colegiox.services.TrimesterService;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class TrimesterResourceTest {
	
	@InjectMocks
	private TrimesterResource resource;
	
	@Mock
	private TrimesterService service;
	
	private Trimester trimester;
	

	
	private static final Integer ID= 1;
	private static final NumberTrimester number= NumberTrimester.FIRST_TRIMESTER;
	private static final Instant BENNING=Instant.parse("2023-02-02T00:00:00Z");
	private static final Instant END= Instant.parse("2002-04-02T00:00:00Z");
	


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	private void startUser() {
		trimester= new Trimester(number, BENNING, END);
		trimester.setId(ID);
		
	}

	@Test
	void testFindAll() {
		when(service.findAll()).thenReturn(List.of(trimester));
		
		ResponseEntity<List<Trimester>>response= resource.findAll();
		
		assertNotNull(response);
		assertEquals(ResponseEntity.class,response.getClass());
		assertEquals(Trimester.class,response.getBody().get(0).getClass());
		assertEquals(1,response.getBody().size());
		assertEquals(ID,response.getBody().get(0).getId());
		assertEquals(NumberTrimester.FIRST_TRIMESTER,number);
		assertEquals(BENNING, response.getBody().get(0).getBenning());
		assertEquals(END,response.getBody().get(0).getEnd());
		assertEquals(HttpStatus.OK,response.getStatusCode());
		
	
	
	}

	@Test
	void testFindById() {
		
		when(service.findById(anyInt())).thenReturn(trimester);
		
		ResponseEntity<Trimester>response=resource.findById(ID);
		
		assertNotNull(response);
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(ResponseEntity.class,response.getClass());
		assertEquals(Trimester.class,response.getBody().getClass());
		assertNotNull(response);
		assertNotNull(response.getBody());
		
		assertEquals(ID,response.getBody().getId());
		assertEquals(BENNING,response.getBody().getBenning());
		assertEquals(END,response.getBody().getEnd());
		
		
	}

}
