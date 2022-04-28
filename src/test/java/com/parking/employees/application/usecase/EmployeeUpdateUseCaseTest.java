package com.parking.employees.application.usecase;

import com.parking.employees.EmployeeMother;
import com.parking.employees.application.port.out.IEmployeeOut;
import com.parking.employees.application.service.RolService;
import com.parking.employees.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeUpdateUseCaseTest {

    @InjectMocks
    EmployeeUpdateUseCase employeeUpdateUseCase;

    @Mock
    IEmployeeOut iemployeeOut;

    @Mock
    RolService rolService;

    @Mock
    BCryptPasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        when(rolService.findById(anyInt())).thenReturn(EmployeeMother.dummyRol());
        when(encoder.encode(any())).thenReturn("123");
        doNothing().when(iemployeeOut).update(anyInt(),any());
    }

    @Test
    void update() {
        Employee employee = EmployeeMother.dummyEmployee();
        employeeUpdateUseCase.update(1, EmployeeMother.dummyRquest().toEntity(), 1);
        verify(iemployeeOut).update(1,employee);
    }
}