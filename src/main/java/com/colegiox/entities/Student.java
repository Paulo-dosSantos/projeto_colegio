package com.colegiox.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false, of= {"id"})
@Entity
public class Student implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private   Integer 	id;
	
	@NonNull
	private String name;
	
	@NonNull
	private String email;
	
	
	
	@NonNull
	private Instant birth;
	
	@NonNull
	private Boolean condition;
	
	@ManyToOne
	@NonNull
	private SchoolClass schoolClass;
	
	@JsonIgnore
	@OneToMany(mappedBy="student")
	private List<ExamTaken>examsTaken= new ArrayList<>();
}
