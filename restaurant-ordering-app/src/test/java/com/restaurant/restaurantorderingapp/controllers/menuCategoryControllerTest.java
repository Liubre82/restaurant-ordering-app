package com.restaurant.restaurantorderingapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.CreateMenuCategoryDTO;
import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.MenuCategoryDTO;
import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.UpdateMenuCategoryDTO;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.DuplicateKeyException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.EmptyDataTableException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.NotFoundException;
import com.restaurant.restaurantorderingapp.services.MenuCategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(MenuCategoryController.class)
public class menuCategoryControllerTest extends BaseControllerTest{

    private static final String END_POINT_PATH = "/menuCategories";

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MenuCategoryService menuCategoryService;

    private MenuCategoryDTO menuCategoryTestEntity1;

    private MenuCategoryDTO menuCategoryTestEntity2;

    private List<MenuCategoryDTO> menuCategoryTestEntityList = new ArrayList<MenuCategoryDTO>();

    @BeforeEach
    public void setUp() {
        menuCategoryTestEntity1 = new MenuCategoryDTO(1L, "Appetizer");
        menuCategoryTestEntity2 = new MenuCategoryDTO(2L, "Dessert");

        menuCategoryTestEntityList.add(menuCategoryTestEntity1);
        menuCategoryTestEntityList.add(menuCategoryTestEntity2);
    }

    @AfterEach
    public void tearDown() {}

    @Test
    @DisplayName("Invalid Input Error Handling: : Create Menu Category with Invalid input.")
    public void testCreateMenuCategoryValidError() throws Exception {
        String statusCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
        CreateMenuCategoryDTO createMenuCategoryDTO = new CreateMenuCategoryDTO("");
        String requestBody = objectMapper.writeValueAsString(createMenuCategoryDTO);

        mockMvc.perform(post(END_POINT_PATH)
                        .contentType("application/json")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$['status code']", is(statusCode)))
                .andExpect(jsonPath("$['menuCategoryName']", is("must not be blank")));

        /* MethodArgumentNotValidException is thrown at the controller layer, so this test should
        not interact with any code in the controller and any in the service layer. */
        verifyZeroInteractions(menuCategoryService);
        verify(menuCategoryService, never()).createMenuCategories(any());
    }

    @Test
    @DisplayName("Duplicate Menu Category Error Handling: : Create a menu category that already exist.")
    public void testCreateMenuCategoryDuplicateError() throws Exception {
        String statusCode = String.valueOf(HttpStatus.CONFLICT.value());
        String duplicateName = "Appetizer";
        CreateMenuCategoryDTO createMenuCategoryDTO = new CreateMenuCategoryDTO(duplicateName);
        String requestBody = objectMapper.writeValueAsString(createMenuCategoryDTO);

        doThrow(new DuplicateKeyException(duplicateName)).when(menuCategoryService).createMenuCategories(createMenuCategoryDTO);
        mockMvc.perform(post(END_POINT_PATH)
                        .contentType("application/json")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$['status code']", is(statusCode)))
                .andExpect(jsonPath("$['message']", is("entity must be unique, create UNSUCCESSFUL! duplicate entry: " + duplicateName)));;
        verify(menuCategoryService).createMenuCategories(createMenuCategoryDTO);
    }

    @Test
    @DisplayName("Successfully CREATE a new menu category.")
    public void testCreateMenuCategorySuccess() throws Exception {
        CreateMenuCategoryDTO createMenuCategoryDTO = new CreateMenuCategoryDTO("testing");
        String requestBody = objectMapper.writeValueAsString(createMenuCategoryDTO);

        doNothing().when(menuCategoryService).createMenuCategories(createMenuCategoryDTO);
        mockMvc.perform(post(END_POINT_PATH)
                        .contentType("application/json")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isCreated());
        verify(menuCategoryService).createMenuCategories(createMenuCategoryDTO);
    }

    @Test
    @DisplayName("Successfully GET a menu category by Id.")
    public void testGetMenuCategorySuccess() throws Exception{
        Long menuCategoryId = 1L;
        String requestURI = requestURIBuilder(END_POINT_PATH, menuCategoryId);
        String responseBody = objectMapper.writeValueAsString(menuCategoryTestEntity1);

        when(menuCategoryService.getMenuCategoryById(menuCategoryId))
                .thenReturn(menuCategoryTestEntity1);
        mockMvc.perform(get(requestURI))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    @DisplayName("Not Found Error Handler: find menu category with an unknown Id.")
    public void testGetMenuCategoryNotFoundError() throws Exception {
        Long menuCategoryId = 1255624L;
        String entityName = "Menu Category";
        String requestURI = requestURIBuilder(END_POINT_PATH, menuCategoryId);

        /* Our getMenuCategoryById should throw a NotFoundException with a custom msg, so here we
        * create an instance of the exception and pass the parameters it needs to create the custom msg.*/
        when(menuCategoryService.getMenuCategoryById(menuCategoryId)).thenThrow(new NotFoundException(entityName, menuCategoryId));
        notFoundExceptionTest(requestURI, HttpMethod.GET);
        verify(menuCategoryService).getMenuCategoryById(menuCategoryId);
    }

    @Test
    @DisplayName("Successfully GET ALL menu categories.")
    public void testGetMenuCategoriesSuccess() throws Exception {
        String responseBody = objectMapper.writeValueAsString(menuCategoryTestEntityList);

        when(menuCategoryService.getAllMenuCategories()).thenReturn(menuCategoryTestEntityList);
        mockMvc.perform(get(END_POINT_PATH))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    @DisplayName("Empty Data-table Error Handler: GET ALL menu categories when there are no menu categories.")
    public void testGetMenuCategoriesEmptyDataTableError() throws Exception {
        String entityName = "menu categories";
        when(menuCategoryService.getAllMenuCategories()).thenThrow(new EmptyDataTableException(entityName));
        mockMvc.perform(get(END_POINT_PATH))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(menuCategoryService).getAllMenuCategories();
    }

    @Test
    @DisplayName("Not Found Error Handler: UPDATE a menu category that does not exist")
    public void testUpdateMenuCategoryNotFoundError() throws Exception {
        String entityName = "Menu Category";
        Long menuCategoryId = 10432L;
        String requestURI = requestURIBuilder(END_POINT_PATH, menuCategoryId);

        UpdateMenuCategoryDTO updateMenuCategoryDTO = new UpdateMenuCategoryDTO("UpdateTest");
        String requestBody = objectMapper.writeValueAsString(updateMenuCategoryDTO);

        when(menuCategoryService.updateMenuCategory(menuCategoryId, updateMenuCategoryDTO)).thenThrow(
                new NotFoundException(entityName, menuCategoryId)
        );
        notFoundExceptionTest(requestURI, HttpMethod.PUT, requestBody);
        verify(menuCategoryService).updateMenuCategory(menuCategoryId, updateMenuCategoryDTO);
    }


    @Test
    @DisplayName("Successfully UPDATE a menu category ")
    public void testUpdateMenuCategorySuccess() throws Exception {
        Long menuCategoryId = 1L;
        String requestURI = requestURIBuilder(END_POINT_PATH, menuCategoryId);

        UpdateMenuCategoryDTO updateMenuCategoryDTO = new UpdateMenuCategoryDTO("UpdateTest");
        String requestBody = objectMapper.writeValueAsString(updateMenuCategoryDTO);

        MenuCategoryDTO updatedMenuCategoryDTO = new MenuCategoryDTO(menuCategoryId, updateMenuCategoryDTO.menuCategoryName());
        String responseBody = objectMapper.writeValueAsString(updatedMenuCategoryDTO);

        when(menuCategoryService.updateMenuCategory(menuCategoryId, updateMenuCategoryDTO)).thenReturn(updatedMenuCategoryDTO);
        mockMvc.perform(put(requestURI)
                        .contentType("application/json")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
        verify(menuCategoryService).updateMenuCategory(menuCategoryId, updateMenuCategoryDTO);
    }

    @Test
    @DisplayName("SUCCESSFULLY DELETE a menu category.")
    public void testDeleteMenuCategorySuccess() throws Exception {
        Long menuCategoryId = 1L;
        String requestURI = requestURIBuilder(END_POINT_PATH, menuCategoryId);

        doNothing().when(menuCategoryService).deleteMenuCategory(menuCategoryId);
        mockMvc.perform(delete(requestURI))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Menu Category deleted successfully."));
        verify(menuCategoryService).deleteMenuCategory(menuCategoryId);
    }

    @Test
    @DisplayName("NOT FOUND ERROR HANDLER: DELETE a menu category that doesn't exist")
    public void testDeleteMenuCategoryNotFoundError() throws Exception {
        Long menuCategoryId = 102L;
        String requestURI = requestURIBuilder(END_POINT_PATH, menuCategoryId);
        String entityName = "Menu Category";

        doThrow(new NotFoundException(entityName, menuCategoryId))
                .when(menuCategoryService).deleteMenuCategory(menuCategoryId);
        notFoundExceptionTest(requestURI, HttpMethod.DELETE);
        verify(menuCategoryService).deleteMenuCategory(menuCategoryId);

    }



}


