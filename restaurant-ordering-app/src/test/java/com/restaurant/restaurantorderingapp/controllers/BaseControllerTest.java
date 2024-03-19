package com.restaurant.restaurantorderingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public abstract class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    public String requestURIBuilder(String endPoint, Long id) {
        return endPoint + "/" + id;
    }

    protected void notFoundExceptionTest(String endpoint, HttpMethod httpMethod) throws Exception {
        mockMvc.perform(request(httpMethod, endpoint))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    protected void notFoundExceptionTest(String endpoint, HttpMethod httpMethod, String requestBody) throws Exception {
        mockMvc.perform(request(httpMethod, endpoint)
                        .contentType("application/json")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
