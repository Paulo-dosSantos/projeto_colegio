package com.colegiox.resources.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StandardError {
	
	private LocalDateTime timestamp;
	private Integer status;
	private String error;
	private String path;

}
