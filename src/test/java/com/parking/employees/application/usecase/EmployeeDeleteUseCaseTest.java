package com.parking.employees.application.usecase;

import com.parking.employees.EmployeeMother;
import com.parking.employees.application.port.out.IEmployeeOut;
import com.parking.employees.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeDeleteUseCaseTest {

    @InjectMocks
    EmployeeDeleteUseCase employeeDeleteUseCase;

    @Mock
    IEmployeeOut iemployeeOut;

    @BeforeEach
    void setUp() {
        doNothing().when(iemployeeOut).delete(anyInt());
    }

    @Test
    void delete() {
        employeeDeleteUseCase.delete(1);
        verify(iemployeeOut).delete(1);
    }
}