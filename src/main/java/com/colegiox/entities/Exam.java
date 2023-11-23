package com.colegiox.entities;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false,of= {"id"})
public class Exam implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	
	@NonNull
	private Instant date;
	
	@ManyToOne
	@NonNull
	private SchoolTrimester trimester;
	
	
	
	@ManyToOne
	@JsonIgnore
	@NonNull
	private Teacher teacher;
	
	
	public SchoolSubject getSchoolSubject() {
		return teacher.getSchoolSubject();
	}
}
