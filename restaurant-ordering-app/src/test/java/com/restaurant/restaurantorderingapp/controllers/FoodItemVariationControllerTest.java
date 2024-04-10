package com.restaurant.restaurantorderingapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto.CreateFoodItemVariationDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto.FoodItemVariationDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto.UpdateFoodItemVariationDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemsDto.FoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.foodSizesDto.FoodSizeDTO;
import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.MenuCategoryDTO;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.DuplicateKeyException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.EmptyDataTableException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.NotFoundException;
import com.restaurant.restaurantorderingapp.services.foodServices.FoodItemVariationService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FoodItemVariationControllerTest extends BaseControllerTest {

    private static final String END_POINT_PATH = "/foodItemVariations";
    private static final String tableName = "food item variations";
    private static final String entityName = "Food Item Variation";
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private FoodItemVariationService foodItemVariationService;
    private FoodItemVariationDTO foodItemVariationTestEntity1;
    private FoodItemVariationDTO foodItemVariationTestEntity2;
    private FoodItemVariationDTO foodItemVariationTestEntity3;
    private FoodItemVariationDTO foodItemVariationTestEntity4;
    private FoodItemVariationDTO foodItemVariationTestEntity5;
    private FoodItemVariationDTO foodItemVariationTestEntity6;
    private FoodItemDTO foodItemTestEntity1;
    private FoodSizeDTO foodSizeTestEntity1;
    private MenuCategoryDTO menuCategoryTestEntity1;
    private static final List<FoodItemVariationDTO> foodItemVariationTestEntityList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        menuCategoryTestEntity1 = new MenuCategoryDTO(1L, "Appetizers");
        foodItemTestEntity1 = new FoodItemDTO("1L", menuCategoryTestEntity1, "Steak", "steak description");
        foodSizeTestEntity1 = new FoodSizeDTO(1L, "Small");

        foodItemVariationTestEntity1 = new FoodItemVariationDTO(1L, foodItemTestEntity1, foodSizeTestEntity1, BigDecimal.valueOf(1.99));
        foodItemVariationTestEntity2 = new FoodItemVariationDTO(2L, foodItemTestEntity1, foodSizeTestEntity1, BigDecimal.valueOf(2.99));
        foodItemVariationTestEntity3 = new FoodItemVariationDTO(3L, foodItemTestEntity1, foodSizeTestEntity1, BigDecimal.valueOf(33.99));
        foodItemVariationTestEntity4 = new FoodItemVariationDTO(4L, foodItemTestEntity1, foodSizeTestEntity1, BigDecimal.valueOf(5.99));
        foodItemVariationTestEntity5 = new FoodItemVariationDTO(5L, foodItemTestEntity1, foodSizeTestEntity1, BigDecimal.valueOf(12.99));
        foodItemVariationTestEntity6 = new FoodItemVariationDTO(6L, foodItemTestEntity1, foodSizeTestEntity1, BigDecimal.valueOf(8.99));

        foodItemVariationTestEntityList.add(foodItemVariationTestEntity1);
        foodItemVariationTestEntityList.add(foodItemVariationTestEntity2);
        foodItemVariationTestEntityList.add(foodItemVariationTestEntity3);
        foodItemVariationTestEntityList.add(foodItemVariationTestEntity4);
        foodItemVariationTestEntityList.add(foodItemVariationTestEntity5);
        foodItemVariationTestEntityList.add(foodItemVariationTestEntity6);
    }

    @AfterEach
    public void tearDown() {
        foodItemVariationTestEntityList.clear();
    }

    @Test
    @DisplayName("SUCCESSFULLY CREATE a new food item variation.")
    @Order(1)
    public void testCreateFoodItemVariationSuccess() throws Exception {
        CreateFoodItemVariationDTO createFoodItemVariationDTO = new CreateFoodItemVariationDTO("3333",foodSizeTestEntity1.foodSizeId(), BigDecimal.valueOf(22.99));
        String requestBody = objectMapper.writeValueAsString(createFoodItemVariationDTO);

        String endpoint = "/admin" +END_POINT_PATH;
        doNothing().when(foodItemVariationService).createFoodItemVariation(createFoodItemVariationDTO);
        createRequestSuccessTest(endpoint, requestBody);
        verify(foodItemVariationService).createFoodItemVariation(createFoodItemVariationDTO);
    }

    @Test
    @DisplayName("SUCCESSFULLY GET a food item variation by Id.")
    @Order(2)
    public void testGetFoodItemVariationSuccess() throws Exception{
        Long foodItemVariationId = 1L;
        String requestURI = "/public" + requestURIBuilder(END_POINT_PATH, foodItemVariationId);
        String responseBody = objectMapper.writeValueAsString(foodItemVariationTestEntity1);

        when(foodItemVariationService.getFoodItemVariationById(foodItemVariationId))
                .thenReturn(foodItemVariationTestEntity1);
        getRequestSuccessTest(requestURI, responseBody);
        verify(foodItemVariationService).getFoodItemVariationById(foodItemVariationId);
    }

    @Test
    @DisplayName("SUCCESSFULLY GET ALL food item variations.")
    @Order(3)
    public void testGetFoodItemVariationsSuccess() throws Exception {
        String responseBody = objectMapper.writeValueAsString(foodItemVariationTestEntityList);

        String endpoint = "/public" +END_POINT_PATH;
        when(foodItemVariationService.getAllFoodItemVariations()).thenReturn(foodItemVariationTestEntityList);
        getRequestSuccessTest(endpoint, responseBody);
        verify(foodItemVariationService).getAllFoodItemVariations();
    }

    @Test
    @DisplayName("SUCCESSFULLY UPDATE a food item variation ")
    @Order(4)
    public void testUpdateFoodItemVariationSuccess() throws Exception {
        Long foodItemVariationId = 1L;
        String requestURI = "/admin" + requestURIBuilder(END_POINT_PATH, foodItemVariationId);

        UpdateFoodItemVariationDTO updateFoodItemVariationDTO = new UpdateFoodItemVariationDTO("3333",foodSizeTestEntity1.foodSizeId(), BigDecimal.valueOf(22.99));
        String requestBody = objectMapper.writeValueAsString(updateFoodItemVariationDTO);

        FoodItemVariationDTO updatedFoodItemVariationDTO = new FoodItemVariationDTO(foodItemVariationId, foodItemTestEntity1, foodSizeTestEntity1, updateFoodItemVariationDTO.foodPrice());
        String responseBody = objectMapper.writeValueAsString(updatedFoodItemVariationDTO);

        when(foodItemVariationService.updateFoodItemVariation(foodItemVariationId, updateFoodItemVariationDTO)).thenReturn(updatedFoodItemVariationDTO);
        updateRequestSuccessTest(requestURI, requestBody, responseBody);
        verify(foodItemVariationService).updateFoodItemVariation(foodItemVariationId, updateFoodItemVariationDTO);

    }

    @Test
    @DisplayName("SUCCESSFULLY DELETE a food item variation.")
    @Order(5)
    public void testDeleteFoodItemVariationSuccess() throws Exception {
        Long foodItemVariationId = 1L;
        String requestURI = "/admin" + requestURIBuilder(END_POINT_PATH, foodItemVariationId);

        doNothing().when(foodItemVariationService).deleteFoodItemVariation(foodItemVariationId);
        deleteRequestSuccessTest(requestURI, entityName);
        verify(foodItemVariationService).deleteFoodItemVariation(foodItemVariationId);
    }


    /*  Error Handler tests */

    @Test
    @DisplayName("Not Found Error Handler: GET food item variation with an unknown Id.")
    @Order(6)
    public void testGetFoodItemVariationNotFoundError() throws Exception {
        Long foodItemVariationId = 1255624L;
        String requestURI = "/public" + requestURIBuilder(END_POINT_PATH, foodItemVariationId);

        /* Our getFoodItemVariationById should throw a NotFoundException with a custom msg, so here we
         * create an instance of the exception and pass the parameters it needs to create the custom msg.*/
        when(foodItemVariationService.getFoodItemVariationById(foodItemVariationId)).thenThrow(new NotFoundException(entityName, foodItemVariationId));
        notFoundExceptionTest(requestURI, HttpMethod.GET);
        verify(foodItemVariationService).getFoodItemVariationById(foodItemVariationId);
    }

    @Test
    @DisplayName("Not Found Error Handler: UPDATE a food item variation that does not exist")
    @Order(7)
    public void testUpdateFoodItemVariationNotFoundError() throws Exception {
        Long foodItemVariationId = 10432L;
        String requestURI = "/admin" + requestURIBuilder(END_POINT_PATH, foodItemVariationId);

        UpdateFoodItemVariationDTO updateFoodItemVariationDTO = new UpdateFoodItemVariationDTO(foodItemTestEntity1.foodItemId(),foodSizeTestEntity1.foodSizeId(), BigDecimal.valueOf(123.12));
        String requestBody = objectMapper.writeValueAsString(updateFoodItemVariationDTO);

        when(foodItemVariationService.updateFoodItemVariation(foodItemVariationId, updateFoodItemVariationDTO)).thenThrow(
                new NotFoundException(entityName, foodItemVariationId)
        );
        notFoundExceptionTest(requestURI, HttpMethod.PUT, requestBody);
        verify(foodItemVariationService).updateFoodItemVariation(foodItemVariationId, updateFoodItemVariationDTO);
    }

    @Test
    @DisplayName("Not Found Error Handler: DELETE a food item variation that doesn't exist")
    @Order(8)
    public void testDeleteFoodItemVariationNotFoundError() throws Exception {
        Long foodItemVariationId = 102L;
        String requestURI = "/admin" + requestURIBuilder(END_POINT_PATH, foodItemVariationId);

        doThrow(new NotFoundException(entityName, foodItemVariationId))
                .when(foodItemVariationService).deleteFoodItemVariation(foodItemVariationId);
        notFoundExceptionTest(requestURI, HttpMethod.DELETE);
        verify(foodItemVariationService).deleteFoodItemVariation(foodItemVariationId);

    }

    @Test
    @DisplayName("Empty Data-table Error Handler: GET ALL food item variations when there are no food item variations.")
    @Order(9)
    public void testGetFoodItemVariationsEmptyDataTableError() throws Exception {
        String statusCode = String.valueOf(HttpStatus.NOT_FOUND.value());

        String endpoint = "/public" +END_POINT_PATH;
        when(foodItemVariationService.getAllFoodItemVariations()).thenThrow(new EmptyDataTableException(tableName));
        emptyDataTableExceptionTest(endpoint, statusCode, tableName);
        verify(foodItemVariationService).getAllFoodItemVariations();
    }

    @Test
    @DisplayName("Duplicate Food Item Variation Error Handling: CREATE a food item variation that already exist.")
    @Order(10)
    public void testCreateFoodItemVariationDuplicateError() throws Exception {
        String duplicateName = "new image tester";
        CreateFoodItemVariationDTO createFoodItemVariationDTO = new CreateFoodItemVariationDTO(foodItemTestEntity1.foodItemId(),foodSizeTestEntity1.foodSizeId(), BigDecimal.valueOf(123.12));
        String requestBody = objectMapper.writeValueAsString(createFoodItemVariationDTO);

        String endpoint = "/admin" +END_POINT_PATH;
        doThrow(new DuplicateKeyException(duplicateName)).when(foodItemVariationService).createFoodItemVariation(createFoodItemVariationDTO);
        duplicateKeyExceptionTest(endpoint, requestBody, duplicateName);
        verify(foodItemVariationService).createFoodItemVariation(createFoodItemVariationDTO);
    }

    @Test
    @DisplayName("Invalid Input Error Handling: : CREATE food item variation with Invalid input.")
    @Order(11)
    public void testCreateFoodItemVariationValidError() throws Exception {
        String statusCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
        CreateFoodItemVariationDTO createFoodItemVariationDTO = new CreateFoodItemVariationDTO("5555", foodSizeTestEntity1.foodSizeId(), BigDecimal.valueOf(-11));
        String requestBody = objectMapper.writeValueAsString(createFoodItemVariationDTO);

        String endpoint = "/admin" +END_POINT_PATH;
        mockMvc.perform(post(endpoint)
                        .contentType("application/json")
                        .characterEncoding("utf-8")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$['status code']", is(statusCode)))
                .andExpect(jsonPath("$['foodPrice']", is("must be greater than 0")));

        /* MethodArgumentNotValidException is thrown at the controller layer, so this test should
        not interact with any code in the controller and any in the service layer. */
        verifyNoInteractions(foodItemVariationService);
        verify(foodItemVariationService, never()).createFoodItemVariation(any());
    }
}
