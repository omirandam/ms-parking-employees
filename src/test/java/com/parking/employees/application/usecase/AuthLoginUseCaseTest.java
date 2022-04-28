package com.parking.employees.application.usecase;

import com.parking.employees.EmployeeMother;
import com.parking.employees.application.port.out.IEmployeeOut;
import com.parking.employees.application.util.JwtUtils;
import org.junit.jupiter.api.Assertions;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthLoginUseCaseTest {

    @InjectMocks
    AuthLoginUseCase authLoginUseCase;

    @Mock
    IEmployeeOut iemployeeOut;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    void setUp() {
        when(iemployeeOut.findByUsername(any())).thenReturn(EmployeeMother.dummyEmployee());
        when(bCryptPasswordEncoder.matches(any(),any())).thenReturn(true);
    }

    @Test
    void execute() {
        authLoginUseCase = new AuthLoginUseCase(iemployeeOut, bCryptPasswordEncoder, "seriedad");
        Assertions.assertNotNull(authLoginUseCase.execute(EmployeeMother.dummyAuthLogin().toDomain()));
    }


}