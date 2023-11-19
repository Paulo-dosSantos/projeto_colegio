package com.colegiox.entities;

import java.util.ArrayList;
import java.util.List;

import com.colegiox.entities.enums.NumberClass;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false, of= {"id"})
@NoArgsConstructor
@RequiredArgsConstructor
public class SchoolClass {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private NumberClass number;
	private List<Student>students= new ArrayList<>();
	
	
	
}
