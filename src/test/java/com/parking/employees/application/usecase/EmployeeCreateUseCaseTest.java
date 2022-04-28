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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeCreateUseCaseTest {

    @InjectMocks
    EmployeeCreateUseCase employeeCreateUseCase;

    @Mock
    IEmployeeOut iemployeeOut;

    @Mock
    RolService rolService;

    @Mock
    BCryptPasswordEncoder encoder;

    @BeforeEach
    void setup() {
        when(rolService.findById(anyInt())).thenReturn(EmployeeMother.dummyRol());
        when(encoder.encode(any())).thenReturn("123");
        doNothing().when(iemployeeOut).create(any());
    }

    @Test
    void createEmployeeOkTest() {
        Employee employee = EmployeeMother.dummyEmployee();
        employeeCreateUseCase.create(EmployeeMother.dummyRquest().toEntity(), 1);
        verify(iemployeeOut).create(employee);
    }

}