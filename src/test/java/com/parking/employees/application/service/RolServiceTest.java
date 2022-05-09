package com.parking.employees.application.service;

import com.parking.employees.EmployeeMother;
import com.parking.employees.adapter.entity.RolEntity;
import com.parking.employees.adapter.jpa.RolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RolServiceTest {

    @InjectMocks
    RolService rolService;

    @Mock
    RolRepository rolRepository;

    @BeforeEach
    void setUp() {
        Optional<RolEntity> optionalRol = Optional.of(EmployeeMother.dummyRolEntity());
        when(rolRepository.findById(anyInt())).thenReturn(optionalRol);
    }

    @Test
    void findById() {
        assertEquals(EmployeeMother.dummyRol(), rolService.findById(0));
    }
}