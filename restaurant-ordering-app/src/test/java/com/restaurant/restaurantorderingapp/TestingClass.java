package com.restaurant.restaurantorderingapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.restaurantorderingapp.dto.foodImagesDto.FoodImageDTO;
import com.restaurant.restaurantorderingapp.services.foodServices.FoodImageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class TestingClass {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private FoodImageService foodImageService;
    private FoodImageDTO foodImageTestEntity1;
    private FoodImageDTO foodImageTestEntity2;
    private FoodImageDTO foodImageTestEntity3;
    private FoodImageDTO foodImageTestEntity4;
    private FoodImageDTO foodImageTestEntity5;
    private FoodImageDTO foodImageTestEntity6;
    private static final List<FoodImageDTO> foodImageTestEntityList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        foodImageTestEntity1 = new FoodImageDTO(1L, "1111", "Image 1");
        foodImageTestEntity2 = new FoodImageDTO(2L, "2222", "Image 2");
        foodImageTestEntity3 = new FoodImageDTO(3L, "3333", "Image 3");
        foodImageTestEntity4 = new FoodImageDTO(4L, "1111", "Image 4");
        foodImageTestEntity5 = new FoodImageDTO(5L, "1111", "Image 5");
        foodImageTestEntity6 = new FoodImageDTO(6L, "2222", "Image 6");

        foodImageTestEntityList.add(foodImageTestEntity1);
        foodImageTestEntityList.add(foodImageTestEntity2);
        foodImageTestEntityList.add(foodImageTestEntity3);
        foodImageTestEntityList.add(foodImageTestEntity4);
        foodImageTestEntityList.add(foodImageTestEntity5);
        foodImageTestEntityList.add(foodImageTestEntity6);
    }

    @AfterEach
    public void tearDown() {
        foodImageTestEntityList.clear();
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    public void getRequestSuccessTest() throws Exception {
        String responseBody = objectMapper.writeValueAsString(foodImageTestEntityList);
        when(foodImageService.getAllFoodImages()).thenReturn(foodImageTestEntityList);
        mockMvc.perform(get("/admin/foodImages"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
        verify(foodImageService).getAllFoodImages();
    }
}
