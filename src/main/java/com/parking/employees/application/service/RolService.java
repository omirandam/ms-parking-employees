package com.parking.employees.application.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.employees.adapter.entity.RolEntity;
import com.parking.employees.adapter.jpa.RolRepository;
import com.parking.employees.domain.Rol;

@Service
public class RolService {


	@Autowired
	private RolRepository rolRepository;
	
	public Rol findById(Integer id) {	
		Optional<RolEntity>optional = rolRepository.findById(id);
		Rol rol = new Rol();	
		BeanUtils.copyProperties(optional.get(), rol);
		
		return rol;
	}
}
