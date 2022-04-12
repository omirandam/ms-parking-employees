package com.parking.employees.adapter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Rol")
public class RolEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

	@Column(nullable = false, unique = true)
	public String name;

	@Column(nullable = false, unique = true)
	public String description;
	
}
