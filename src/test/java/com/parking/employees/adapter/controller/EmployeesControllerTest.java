package com.parking.employees.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parking.employees.EmployeeMother;
import com.parking.employees.application.port.in.*;
import com.parking.employees.application.service.UserService;
import com.parking.employees.application.util.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = EmployeesController.class)
class EmployeesControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    IEmployeeFindAll findAll_in;
    @MockBean
    IEmployeeFind find_in;
    @MockBean
    IEmployeeCreate create_in;
    @MockBean
    IEmployeeUpdate update_in;
    @MockBean
    IEmployeeDelete delete_in;

    JwtUtils jwtUtils;

    static final String URL_GET_EMPLOYEE = "/api/employee";

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;


    @BeforeEach
    void setUp() {
        when(findAll_in.findAll()).thenReturn(EmployeeMother.dummy());
        when(find_in.find(1)).thenReturn(EmployeeMother.dummy().get(0));
    }

    @Test
    void findAll() throws Exception {

        this.mockMvc.perform(
                        get(URL_GET_EMPLOYEE).header("Authorization", "Bearer "+jwtUtils.createToken("omirandam","ADMIN", "seriedad"))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().json(
                                objectMapper.writeValueAsString(EmployeeMother.dummy())
                        )
                );
    }

    @Test
    void find() throws Exception {

        this.mockMvc.perform(
                        get(URL_GET_EMPLOYEE+"/1").header("Authorization", "Bearer "+jwtUtils.createToken("omirandam","ADMIN", "seriedad"))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().json(
                                objectMapper.writeValueAsString(EmployeeMother.dummy().get(0))
                        )
                );

    }

    @Test
    void create() throws Exception {
        this.mockMvc.perform(
                        post(URL_GET_EMPLOYEE)
                                .contentType("application/json")
                                .header("Authorization", "Bearer "+jwtUtils.createToken("omirandam","ADMIN", "seriedad"))
                                .content(
                                        objectMapper.writeValueAsString(EmployeeMother.dummyRquest())
                                )
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        this.mockMvc.perform(
                        put(URL_GET_EMPLOYEE+"/1")
                                .contentType("application/json")
                                .header("Authorization", "Bearer "+jwtUtils.createToken("omirandam","ADMIN", "seriedad"))
                                .content(
                                        objectMapper.writeValueAsString(EmployeeMother.dummyRquest())
                                )
                )
                .andDo(print())
                .andExpect(status().isOk());
         }

    @Test
    void delete() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete(URL_GET_EMPLOYEE+"/1").header("Authorization", "Bearer "+jwtUtils.createToken("omirandam","ADMIN", "seriedad"))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

}