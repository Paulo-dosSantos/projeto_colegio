package com.colegiox.entities;

import java.util.ArrayList;
import java.util.List;

import com.colegiox.entities.enums.NumberTrimester;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Trimester {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NonNull
	@Column(unique=true)
	private NumberTrimester numberTrimester;
	
	@ManyToMany
	@JoinTable(name="schoolClass_trimester",  joinColumns= @JoinColumn(name="schoolClass_id"),
			inverseJoinColumns=@JoinColumn(name="trimester_id"))
	private List<SchoolClass>schoolClasses= new ArrayList<>();
	
	
	

}
