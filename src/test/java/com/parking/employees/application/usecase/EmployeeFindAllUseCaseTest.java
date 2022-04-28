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

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeFindAllUseCaseTest {

    @InjectMocks
    EmployeeFindAllUseCase employeeFindAllUseCase;

    @Mock
    IEmployeeOut iemployeeOut;

    @BeforeEach
    void setUp() {
        List<Employee> employeeList = EmployeeMother.dummy().stream().map(e -> e.toEntity()).collect(Collectors.toList());
        when(iemployeeOut.findAll()).thenReturn(employeeList);
    }

    @Test
    void findAll() {
        assertFalse(employeeFindAllUseCase.findAll().isEmpty());
    }

}