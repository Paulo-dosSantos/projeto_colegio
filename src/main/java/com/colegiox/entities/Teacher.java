package com.colegiox.entities;
import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Entity
@Data
@EqualsAndHashCode(callSuper = false,of= {"id"})
@NoArgsConstructor
@RequiredArgsConstructor
public class Teacher implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NonNull
	private String name;
	
	@NonNull
	private String email;
	
	@NonNull
	@ManyToOne
	private SchoolSubject schoolSubject;
	
	

}
