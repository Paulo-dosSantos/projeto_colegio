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
	
	@NonNull
	private final Double maximumScore=5.0;
	
	
	@ManyToOne
	@JsonIgnore
	@NonNull
	private Teacher teacher;
	
	@JsonIgnore
	@OneToMany(mappedBy="exam")
	private List<ExamTaken>examstaken=new ArrayList<>();
	
	
	public SchoolSubject getSchoolSubject() {
		return teacher.getSchoolSubject();
	}
}
