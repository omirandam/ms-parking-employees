package com.parking.employees.adapter.dto;

import org.springframework.beans.BeanUtils;

import com.parking.employees.adapter.entity.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RolDto {

	public Integer id;

	public String name;

	public String description;
	
	public Rol toEntity() {
		Rol rol = new Rol();
		BeanUtils.copyProperties(this, rol);
		
		return rol;
	}
	
	public static RolDto of(Rol rol) {
		RolDto dto = new RolDto();
		BeanUtils.copyProperties(rol, dto);
		
		return dto;
	}
}
