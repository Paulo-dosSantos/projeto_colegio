package com.colegiox.resources.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.colegiox.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError>objectNotFoundException(
			ObjectNotFoundException ex, HttpServletRequest request){
			
		StandardError error= new StandardError
				(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(),request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

	}
}
