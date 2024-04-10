//package com.restaurant.restaurantorderingapp.controllers;
//
package com.restaurant.restaurantorderingapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.restaurantorderingapp.dto.foodSizesDto.CreateFoodSizeDTO;
import com.restaurant.restaurantorderingapp.dto.foodSizesDto.FoodSizeDTO;
import com.restaurant.restaurantorderingapp.dto.foodSizesDto.UpdateFoodSizeDTO;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.DuplicateKeyException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.EmptyDataTableException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.NotFoundException;
import com.restaurant.restaurantorderingapp.services.foodServices.FoodSizeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FoodSizeControllerTest extends BaseControllerTest{

    private static final String END_POINT_PATH = "/foodSizes";
    private static final String tableName = "food sizes";
    private static final String entityName = "Food Size";
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private FoodSizeService foodSizeService;
    private FoodSizeDTO foodSizeTestEntity1;
    private FoodSizeDTO foodSizeTestEntity2;
    private FoodSizeDTO foodSizeTestEntity3;
    private static final List<FoodSizeDTO> foodSizeTestEntityList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        foodSizeTestEntity1 = new FoodSizeDTO(1L, "Small");
        foodSizeTestEntity2 = new FoodSizeDTO(2L, "Medium");
        foodSizeTestEntity3 = new FoodSizeDTO(3L, "Large");

        foodSizeTestEntityList.add(foodSizeTestEntity1);
        foodSizeTestEntityList.add(foodSizeTestEntity2);
        foodSizeTestEntityList.add(foodSizeTestEntity3);
    }

    @AfterEach
    public void tearDown() {
        foodSizeTestEntityList.clear();
    }

    @Test
    @DisplayName("SUCCESSFULLY CREATE a new food size.")
    @Order(1)
    public void testCreateFoodSizeSuccess() throws Exception {
        CreateFoodSizeDTO createFoodSizeDTO = new CreateFoodSizeDTO("testing");
        String requestBody = objectMapper.writeValueAsString(createFoodSizeDTO);
        String endpoint = "/admin" +END_POINT_PATH;
        doNothing().when(foodSizeService).createFoodSizes(createFoodSizeDTO);
        createRequestSuccessTest(endpoint, requestBody);
        verify(foodSizeService).createFoodSizes(createFoodSizeDTO);
    }

    @Test
    @DisplayName("SUCCESSFULLY GET a food size by Id.")
    @Order(2)
    public void testGetFoodSizeSuccess() throws Exception{
        Long foodSizeId = 1L;
        String requestURI = "/public" + requestURIBuilder(END_POINT_PATH, foodSizeId);
        String responseBody = objectMapper.writeValueAsString(foodSizeTestEntity1);

        when(foodSizeService.getFoodSizeById(foodSizeId))
                .thenReturn(foodSizeTestEntity1);
        getRequestSuccessTest(requestURI, responseBody);
        verify(foodSizeService).getFoodSizeById(foodSizeId);
    }

    @Test
    @DisplayName("SUCCESSFULLY GET ALL food sizes.")
    @Order(3)
    public void testGetFoodSizesSuccess() throws Exception {
        String responseBody = objectMapper.writeValueAsString(foodSizeTestEntityList);
        String endpoint = "/public" +END_POINT_PATH;
        when(foodSizeService.getAllFoodSizes()).thenReturn(foodSizeTestEntityList);
        getRequestSuccessTest(endpoint, responseBody);
        verify(foodSizeService).getAllFoodSizes();
    }

    @Test
    @DisplayName("SUCCESSFULLY GET ALL food sizes by name through SEARCH INPUT")
    @Order(4)
    public void testSearchFoodSizesSuccess() throws Exception {
        String searchInput = "e";
        String requestURI = "/public" + END_POINT_PATH + "/search?searchInput=" + searchInput;
        List<FoodSizeDTO> searchList = new ArrayList<>();
        searchList.add(foodSizeTestEntity2);
        searchList.add(foodSizeTestEntity3);
        String responseBody = objectMapper.writeValueAsString(searchList);

        when(foodSizeService.searchFoodSizes(searchInput)).thenReturn(searchList);
        getRequestSuccessTest(requestURI, responseBody);
        verify(foodSizeService).searchFoodSizes(searchInput);
    }

    @Test
    @DisplayName("SUCCESSFULLY UPDATE a food size")
    @Order(5)
    public void testUpdateFoodSizeSuccess() throws Exception {
        Long foodSizeId = 1L;
        String requestURI = "/admin" + requestURIBuilder(END_POINT_PATH, foodSizeId);

        UpdateFoodSizeDTO updateFoodSizeDTO = new UpdateFoodSizeDTO("UpdateTest");
        String requestBody = objectMapper.writeValueAsString(updateFoodSizeDTO);

        FoodSizeDTO updatedFoodSizeDTO = new FoodSizeDTO(foodSizeId, updateFoodSizeDTO.foodSizeName());
        String responseBody = objectMapper.writeValueAsString(updatedFoodSizeDTO);

        when(foodSizeService.updateFoodSize(foodSizeId, updateFoodSizeDTO)).thenReturn(updatedFoodSizeDTO);
        updateRequestSuccessTest(requestURI, requestBody, responseBody);
        verify(foodSizeService).updateFoodSize(foodSizeId, updateFoodSizeDTO);

    }

    @Test
    @DisplayName("SUCCESSFULLY DELETE a food size.")
    @Order(6)
    public void testDeleteFoodSizeSuccess() throws Exception {
        Long foodSizeId = 1L;
        String requestURI = "/admin" + requestURIBuilder(END_POINT_PATH, foodSizeId);

        doNothing().when(foodSizeService).deleteFoodSize(foodSizeId);
        deleteRequestSuccessTest(requestURI, entityName);
        verify(foodSizeService).deleteFoodSize(foodSizeId);
    }


    /*  Error Handler tests */

    @Test
    @DisplayName("Not Found Error Handler: GET food size with an unknown Id.")
    @Order(7)
    public void testGetFoodSizeNotFoundError() throws Exception {
        Long foodSizeId = 1255624L;
        String requestURI = "/public" + requestURIBuilder(END_POINT_PATH, foodSizeId);

        /* Our getFoodSizeById should throw a NotFoundException with a custom msg, so here we
         * create an instance of the exception and pass the parameters it needs to create the custom msg.*/
        when(foodSizeService.getFoodSizeById(foodSizeId)).thenThrow(new NotFoundException(entityName, foodSizeId));
        notFoundExceptionTest(requestURI, HttpMethod.GET);
        verify(foodSizeService).getFoodSizeById(foodSizeId);
    }

    @Test
    @DisplayName("Not Found Error Handler: UPDATE a food size that does not exist")
    @Order(8)
    public void testUpdateFoodSizeNotFoundError() throws Exception {
        Long foodSizeId = 10432L;
        String requestURI = "/admin" + requestURIBuilder(END_POINT_PATH, foodSizeId);

        UpdateFoodSizeDTO updateFoodSizeDTO = new UpdateFoodSizeDTO("UpdateTest");
        String requestBody = objectMapper.writeValueAsString(updateFoodSizeDTO);

        when(foodSizeService.updateFoodSize(foodSizeId, updateFoodSizeDTO)).thenThrow(
                new NotFoundException(entityName, foodSizeId)
        );
        notFoundExceptionTest(requestURI, HttpMethod.PUT, requestBody);
        verify(foodSizeService).updateFoodSize(foodSizeId, updateFoodSizeDTO);
    }

    @Test
    @DisplayName("Not Found Error Handler: DELETE a food size that doesn't exist")
    @Order(9)
    public void testDeleteFoodSizeNotFoundError() throws Exception {
        Long foodSizeId = 102L;
        String requestURI = "/admin" + requestURIBuilder(END_POINT_PATH, foodSizeId);

        doThrow(new NotFoundException(entityName, foodSizeId))
                .when(foodSizeService).deleteFoodSize(foodSizeId);
        notFoundExceptionTest(requestURI, HttpMethod.DELETE);
        verify(foodSizeService).deleteFoodSize(foodSizeId);

    }

    @Test
    @DisplayName("Empty Data-table Error Handler: GET ALL food sizes when there are no food sizes.")
    @Order(10)
    public void testGetFoodSizesEmptyDataTableError() throws Exception {
        String statusCode = String.valueOf(HttpStatus.NOT_FOUND.value());

        String endpoint = "/public" +END_POINT_PATH;
        when(foodSizeService.getAllFoodSizes()).thenThrow(new EmptyDataTableException(tableName));
        emptyDataTableExceptionTest(endpoint, statusCode, tableName);
        verify(foodSizeService).getAllFoodSizes();
    }

    @Test
    @DisplayName("Duplicate Food Size Error Handling: CREATE a food size that already exist.")
    @Order(11)
    public void testCreateFoodSizeDuplicateError() throws Exception {

        String duplicateName = "Large";
        CreateFoodSizeDTO createFoodSizeDTO = new CreateFoodSizeDTO(duplicateName);
        String requestBody = objectMapper.writeValueAsString(createFoodSizeDTO);

        String endpoint = "/admin" +END_POINT_PATH;
        doThrow(new DuplicateKeyException(duplicateName)).when(foodSizeService).createFoodSizes(createFoodSizeDTO);
        duplicateKeyExceptionTest(endpoint, requestBody, duplicateName);
        verify(foodSizeService).createFoodSizes(createFoodSizeDTO);
    }

    @Test
    @DisplayName("Invalid Input Error Handling: : CREATE Food Size with Invalid input.")
    @Order(12)
    public void testCreateFoodSizeValidError() throws Exception {
        String statusCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
        CreateFoodSizeDTO createFoodSizeDTO = new CreateFoodSizeDTO("");
        String requestBody = objectMapper.writeValueAsString(createFoodSizeDTO);

        String endpoint = "/admin" +END_POINT_PATH;
        mockMvc.perform(post(endpoint)
                        .contentType("application/json")
                        .characterEncoding("utf-8")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$['status code']", is(statusCode)))
                .andExpect(jsonPath("$['foodSizeName']", is("must not be blank")));

        /* MethodArgumentNotValidException is thrown at the controller layer, so this test should
        not interact with any code in the controller and any in the service layer. */
        verifyNoInteractions(foodSizeService);
        verify(foodSizeService, never()).createFoodSizes(any());
    }

}



