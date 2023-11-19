package com.colegiox.entities;

import com.colegiox.entities.enums.NumberTrimester;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Trimester {
	
	private Integer id;
	
	@NonNull
	@Column(unique=true)
	private NumberTrimester numberTrimester;
	
	
	

}
