package com.parking.employees.application.usecase;

import com.parking.employees.EmployeeMother;
import com.parking.employees.adapter.dto.EmployeeDto;
import com.parking.employees.application.port.out.IEmployeeOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeFindUseCaseTest {

    @InjectMocks
    EmployeeFindUseCase employeeFindUseCase;

    @Mock
    IEmployeeOut iemployeeOut;

    EmployeeDto employee = EmployeeMother.dummy().get(0);

    @BeforeEach
    void setUp() {
        when(iemployeeOut.find(anyInt())).thenReturn(employee.toEntity());
    }

    @Test
    void find() {
        assertEquals(employee, employeeFindUseCase.find(anyInt()));
    }

}