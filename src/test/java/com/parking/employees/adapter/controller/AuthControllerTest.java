package com.parking.employees.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parking.employees.EmployeeMother;
import com.parking.employees.application.port.in.IAuthLoginQuery;
import com.parking.employees.application.port.in.IEmployeeFindAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class)
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    IAuthLoginQuery authLogin;

    static final String URL_GET_AUTH = "/auth/login";

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void create() throws Exception {

        this.mockMvc.perform(
                        post(URL_GET_AUTH)
                                .contentType("application/json")
                                .content(
                                        objectMapper.writeValueAsString(EmployeeMother.dummyAuthLogin())
                                )
                )
                .andDo(print())
                .andExpect(status().isOk());

    }
}