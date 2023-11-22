package com.colegiox.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.colegiox.entities.enums.NumberTrimester;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="tb_trimester")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class SchoolTrimester implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NonNull
	@Column(unique=true)
	@Enumerated(EnumType.STRING)
	private NumberTrimester numberTrimester;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="GMT")
	@NonNull
	private Instant benning_date;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="GMT")
	@NonNull
	private Instant end_date;
	
	@JsonIgnore
	@OneToMany(mappedBy="trimester")
	private List<Exam>exams= new ArrayList<>();
	
	
	
	

}
