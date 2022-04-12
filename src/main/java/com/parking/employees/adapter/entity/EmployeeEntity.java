package com.parking.employees.adapter.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "employee")
public class EmployeeEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, unique = true)
	private String identity_document;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String lastname;

	@Column(nullable = false)
	private LocalDate birthday;
	
	@Column(nullable = false)
	private Boolean state;
	
	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String paswword;

	@ManyToOne(cascade = CascadeType.MERGE)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "id_rol")
	private RolEntity rol;
	
}
