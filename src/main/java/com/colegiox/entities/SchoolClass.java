package com.colegiox.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.colegiox.entities.enums.NumberClass;
import com.colegiox.entities.enums.Shifts;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class SchoolClass implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(unique=true)
	private NumberClass number;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private Shifts shift;
	
	@OneToMany(mappedBy="schoolClass")
	@JsonIgnore
	private List<Student>students= new ArrayList<>();
	
	
	
}
