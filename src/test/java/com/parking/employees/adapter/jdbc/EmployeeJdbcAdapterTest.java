package com.parking.employees.adapter.jdbc;

import com.parking.employees.EmployeeMother;
import com.parking.employees.adapter.entity.EmployeeEntity;
import com.parking.employees.adapter.entity.RolEntity;
import com.parking.employees.adapter.jpa.EmployeeRepository;
import com.parking.employees.adapter.jpa.RolRepository;
import com.parking.employees.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EmployeeJdbcAdapterTest {

    @InjectMocks
    EmployeeJdbcAdapter employeeJdbcAdapter;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    RolRepository rolRepository;

    List<EmployeeEntity> employeeList = EmployeeMother.dummyEmployeesEntitys();
    Employee employee = EmployeeMother.dummyEmployeeFind();

    @BeforeEach
    void setUp() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        Optional<EmployeeEntity> optionalEmployee = Optional.of(employeeList.get(0));
        when(employeeRepository.findById(anyInt())).thenReturn(optionalEmployee);
        when(employeeRepository.findByUsername(any())).thenReturn(employeeList.get(0));

        doNothing().when(employeeRepository).delete(any());

        Optional<RolEntity> optionalRol = Optional.of(EmployeeMother.dummyRolEntity());
        when(rolRepository.findById(anyInt())).thenReturn(optionalRol);

       // doNothing().when(employeeRepository).save(any());
    }

    @Test
    void findByUsername() {
        assertEquals(employee, employeeJdbcAdapter.findByUsername("omirandam"));
    }

    @Test
    void find() {
        assertEquals(employee, employeeJdbcAdapter.find(0));
    }

    @Test
    void findAll() {
        assertFalse(employeeJdbcAdapter.findAll().isEmpty());
    }

    @Test
    void delete() {
        employeeJdbcAdapter.delete(1);
        verify(employeeRepository).delete(employeeList.get(0));
    }
/*
    @Test
    void update() {
        employeeJdbcAdapter.update(1,employee);
        verify(employeeRepository).save(employeeList.get(0));
    }

    @Test
    void create() {
       employeeJdbcAdapter.create(employee);
       verify(employeeRepository).save(employeeList.get(0));
    }
*/

}