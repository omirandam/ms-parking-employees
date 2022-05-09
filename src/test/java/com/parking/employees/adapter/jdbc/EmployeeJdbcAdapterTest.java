package com.parking.employees.adapter.jdbc;

import com.parking.employees.EmployeeMother;
import com.parking.employees.adapter.entity.EmployeeEntity;
import com.parking.employees.adapter.entity.RolEntity;
import com.parking.employees.adapter.jpa.EmployeeRepository;
import com.parking.employees.adapter.jpa.RolRepository;
import com.parking.employees.domain.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeJdbcAdapterTest {

    @InjectMocks
    EmployeeJdbcAdapter employeeJdbcAdapter;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    RolRepository rolRepository;

    List<EmployeeEntity> employeeList = EmployeeMother.dummyEmployeesEntitys();
    Employee employee = EmployeeMother.dummyEmployeeFind();

    @Test
    void findByUsername() {
        when(employeeRepository.findByUsername(any())).thenReturn(employeeList.get(0));

        assertEquals(employee, employeeJdbcAdapter.findByUsername("omirandam"));
    }

    @Test
    void find() {
        Optional<EmployeeEntity> optionalEmployee = Optional.of(employeeList.get(0));
        when(employeeRepository.findById(anyInt())).thenReturn(optionalEmployee);

        assertEquals(employee, employeeJdbcAdapter.find(0));
    }

    @Test
    void findAll() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        assertFalse(employeeJdbcAdapter.findAll().isEmpty());
    }

    @Test
    void delete() {
        Optional<EmployeeEntity> optionalEmployee = Optional.of(employeeList.get(0));
        when(employeeRepository.findById(anyInt())).thenReturn(optionalEmployee);
        doNothing().when(employeeRepository).delete(any());

        employeeJdbcAdapter.delete(1);
        verify(employeeRepository).delete(employeeList.get(0));
    }

    @Test
    void update() {
        Optional<EmployeeEntity> optionalEmployee = Optional.of(employeeList.get(0));
        when(employeeRepository.findById(anyInt())).thenReturn(optionalEmployee);
        Optional<RolEntity> optionalRol = Optional.of(EmployeeMother.dummyRolEntity());
        when(rolRepository.findById(anyInt())).thenReturn(optionalRol);

        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(optionalEmployee.get());

        employeeJdbcAdapter.update(1,employee);
        verify(employeeRepository).save(employeeList.get(0));
    }

    @Test
    void create() {
        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(employeeList.get(0));

        employeeJdbcAdapter.create(employee);
        verify(employeeRepository).save(employeeList.get(0));
    }


}