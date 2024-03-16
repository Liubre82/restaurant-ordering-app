package com.restaurant.restaurantorderingapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.CreateMenuCategoryDTO;
import com.restaurant.restaurantorderingapp.services.MenuCategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MenuCategoryController.class)
public class menuCategoryControllerTest {

    private static final String END_POINT_PATH = "/menuCategories";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MenuCategoryService menuCategoryService;


    @Test
    @DisplayName("Creating a new menu category validation test, checks to ensure an error occurs " +
            "and no entity is created when the form data/request body is passes the validity test.")
    public void testCreateMenuCategoryError() throws Exception {
        CreateMenuCategoryDTO createMenuCategoryDTO = new CreateMenuCategoryDTO("");
        String requestBody = objectMapper.writeValueAsString(createMenuCategoryDTO);

        mockMvc.perform(post(END_POINT_PATH)
                        .contentType("application/json")
                        .content(requestBody)) // Invalid request body, should result in a 400 Bad Request
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$['status code']", is("400")))
                .andExpect(jsonPath("$['menuCategoryName']", is("must not be blank")));
        /* MethodArgumentNotValidException is thrown at the controller layer, so this test should
        not interact with any code in the controller and any in the service layer. */
        verifyZeroInteractions(menuCategoryService);
        verify(menuCategoryService, never()).createMenuCategories(any());
    }

    @Test
    @DisplayName("Create a new menu category test, checks to ensure an entity is created successfully.")
    public void testCreateMenuCategorySuccess() throws Exception {
        CreateMenuCategoryDTO createMenuCategoryDTO = new CreateMenuCategoryDTO("testing");
        String requestBody = objectMapper.writeValueAsString(createMenuCategoryDTO);

        doNothing().when(menuCategoryService).createMenuCategories(createMenuCategoryDTO);
        mockMvc.perform(post(END_POINT_PATH)
                        .contentType("application/json")
                        .content(requestBody)) // Invalid request body, should result in a 400 Bad Request
                .andExpect(status().isCreated());
        verify(menuCategoryService).createMenuCategories(createMenuCategoryDTO);

    }


}
