package com.restaurant.restaurantorderingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public abstract class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    public String requestURIBuilder(String endpoint, Long id) {
        return endpoint + "/" + id;
    }

    public String requestURIBuilder(String endpoint, String id) {
        return endpoint + "/" + id;
    }

    protected void getRequestSuccessTest(String endpoint, String responseBody) throws Exception {
        mockMvc.perform(get(endpoint))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    protected void updateRequestSuccessTest(String endpoint, String requestBody, String responseBody) throws Exception {
        mockMvc.perform(put(endpoint)
                        .contentType("application/json")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    protected void deleteRequestSuccessTest(String endpoint, String entityName) throws Exception {
        mockMvc.perform(delete(endpoint))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(entityName + " deleted successfully."));
    }

    protected void createRequestSuccessTest(String endpoint, String requestBody) throws Exception {
        mockMvc.perform(post(endpoint)
                        .contentType("application/json")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isCreated());

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

    protected void duplicateKeyExceptionTest(String endpoint, String requestBody, String duplicateName) throws Exception {
        String statusCode = String.valueOf(HttpStatus.CONFLICT.value());
        mockMvc.perform(post(endpoint)
                        .contentType("application/json")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$['status code']", is(statusCode)))
                .andExpect(jsonPath("$['message']", is("entity must be unique, create UNSUCCESSFUL! duplicate entry: " + duplicateName)));;
    }

    protected void emptyDataTableExceptionTest(String endpoint, String statusCode, String tableName) throws Exception {
        mockMvc.perform(get(endpoint))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$['status code']", is(statusCode)))
                .andExpect(jsonPath("$['message']", is("Empty data-table, there are no " + tableName)));
    }



}
