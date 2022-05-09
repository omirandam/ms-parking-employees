package com.parking.employees;

import com.parking.employees.adapter.dto.AuthLoginRequest;
import com.parking.employees.adapter.dto.EmployeeDto;
import com.parking.employees.adapter.dto.EmployeeRequest;
import com.parking.employees.adapter.dto.RolDto;
import com.parking.employees.adapter.entity.EmployeeEntity;
import com.parking.employees.adapter.entity.RolEntity;
import com.parking.employees.domain.AuthLogin;
import com.parking.employees.domain.Employee;
import com.parking.employees.domain.Rol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeMother {

    public static List<EmployeeDto> dummy(){
        List<EmployeeDto> listEmployees = new ArrayList<EmployeeDto>();

        RolDto rol = new RolDto(1, "Admin","Admin");
        EmployeeDto employee = new EmployeeDto(1,"1234567892","Marlene","Melian",LocalDate.of(1973,4,14),true,"omirandam","123", rol);

        listEmployees.add(employee);

        return listEmployees;
    }

    public static EmployeeRequest dummyRquest(){
        return new EmployeeRequest("1234567892","Marlene","Melian",LocalDate.of(1973,4,14),true,"omirandam","123", 1);
    }

    public static Rol dummyRol(){
        Rol rol = new Rol(1,"ADMIN","ADMIN");

        return rol;
    }

    public static Employee dummyEmployee(){
        Employee employee = EmployeeMother.dummyRquest().toEntity();
        Rol rol = EmployeeMother.dummyRol();
        employee.setRol(rol);

        return  employee;
    }

    public static Employee dummyEmployeeFind(){
        Employee employee = new Employee(1,"1234567892","Marlene","Melian",LocalDate.of(1973,4,14),true,"omirandam","123", null);
        Rol rol = new Rol(1,"ADMIN", "ADMIN");
        employee.setRol(rol);

        return  employee;
    }

    public static RolEntity dummyRolEntity(){
        return  new RolEntity(1,"ADMIN", "ADMIN");
    }

    public static List<EmployeeEntity> dummyEmployeesEntitys(){
        List<EmployeeEntity> listEmployees = new ArrayList<EmployeeEntity>();

        RolEntity rol = new RolEntity(1, "ADMIN","ADMIN");
        EmployeeEntity employee = new EmployeeEntity(1,"1234567892","Marlene","Melian",LocalDate.of(1973,4,14),true,"omirandam","123", rol);

        listEmployees.add(employee);


        return  listEmployees;
    }

    public static AuthLoginRequest dummyAuthLogin(){
        return new AuthLoginRequest("test", "123");

    }

}
