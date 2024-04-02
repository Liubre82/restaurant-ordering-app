package com.restaurant.restaurantorderingapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.restaurantorderingapp.dto.foodImagesDto.CreateFoodImageDTO;
import com.restaurant.restaurantorderingapp.dto.foodImagesDto.FoodImageDTO;
import com.restaurant.restaurantorderingapp.dto.foodImagesDto.UpdateFoodImageDTO;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.DuplicateKeyException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.EmptyDataTableException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.NotFoundException;
import com.restaurant.restaurantorderingapp.services.FoodImageService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FoodImageController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FoodImageControllerTest extends BaseControllerTest {

    private static final String END_POINT_PATH = "/foodImages";
    private static final String tableName = "food images";
    private static final String entityName = "Food Image";
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
    @DisplayName("SUCCESSFULLY CREATE a new food image.")
    @Order(1)
    public void testCreateFoodImageSuccess() throws Exception {
        CreateFoodImageDTO createFoodImageDTO = new CreateFoodImageDTO("3333","testing Image");
        String requestBody = objectMapper.writeValueAsString(createFoodImageDTO);

        doNothing().when(foodImageService).createFoodImages(createFoodImageDTO);
        createRequestSuccessTest(END_POINT_PATH, requestBody);
        verify(foodImageService).createFoodImages(createFoodImageDTO);
    }

    @Test
    @DisplayName("SUCCESSFULLY GET a food image by Id.")
    @Order(2)
    public void testGetFoodImageSuccess() throws Exception{
        Long foodImageId = 1L;
        String requestURI = requestURIBuilder(END_POINT_PATH, foodImageId);
        String responseBody = objectMapper.writeValueAsString(foodImageTestEntity1);

        when(foodImageService.getFoodImageById(foodImageId))
                .thenReturn(foodImageTestEntity1);
        getRequestSuccessTest(requestURI, responseBody);
        verify(foodImageService).getFoodImageById(foodImageId);
    }

    @Test
    @DisplayName("SUCCESSFULLY GET ALL food images.")
    @Order(3)
    public void testGetFoodImagesSuccess() throws Exception {
        String responseBody = objectMapper.writeValueAsString(foodImageTestEntityList);

        when(foodImageService.getAllFoodImages()).thenReturn(foodImageTestEntityList);
        getRequestSuccessTest(END_POINT_PATH, responseBody);
        verify(foodImageService).getAllFoodImages();
    }

    @Test
    @DisplayName("SUCCESSFULLY UPDATE a food image ")
    @Order(4)
    public void testUpdateFoodImageSuccess() throws Exception {
        Long foodImageId = 1L;
        String requestURI = requestURIBuilder(END_POINT_PATH, foodImageId);

        UpdateFoodImageDTO updateFoodImageDTO = new UpdateFoodImageDTO("2222", "test image test");
        String requestBody = objectMapper.writeValueAsString(updateFoodImageDTO);

        FoodImageDTO updatedFoodImageDTO = new FoodImageDTO(foodImageId, updateFoodImageDTO.foodItemId(), updateFoodImageDTO.imageUrl());
        String responseBody = objectMapper.writeValueAsString(updatedFoodImageDTO);

        when(foodImageService.updateFoodImage(foodImageId, updateFoodImageDTO)).thenReturn(updatedFoodImageDTO);
        updateRequestSuccessTest(requestURI, requestBody, responseBody);
        verify(foodImageService).updateFoodImage(foodImageId, updateFoodImageDTO);

    }

    @Test
    @DisplayName("SUCCESSFULLY DELETE a food image.")
    @Order(5)
    public void testDeleteFoodImageSuccess() throws Exception {
        Long foodImageId = 1L;
        String requestURI = requestURIBuilder(END_POINT_PATH, foodImageId);

        doNothing().when(foodImageService).deleteFoodImage(foodImageId);
        deleteRequestSuccessTest(requestURI, entityName);
        verify(foodImageService).deleteFoodImage(foodImageId);
    }


    /*  Error Handler tests */

    @Test
    @DisplayName("Not Found Error Handler: GET food image with an unknown Id.")
    @Order(6)
    public void testGetFoodImageNotFoundError() throws Exception {
        Long foodImageId = 1255624L;
        String requestURI = requestURIBuilder(END_POINT_PATH, foodImageId);

        /* Our getFoodImageById should throw a NotFoundException with a custom msg, so here we
         * create an instance of the exception and pass the parameters it needs to create the custom msg.*/
        when(foodImageService.getFoodImageById(foodImageId)).thenThrow(new NotFoundException(entityName, foodImageId));
        notFoundExceptionTest(requestURI, HttpMethod.GET);
        verify(foodImageService).getFoodImageById(foodImageId);
    }

    @Test
    @DisplayName("Not Found Error Handler: UPDATE a food image that does not exist")
    @Order(7)
    public void testUpdateFoodImageNotFoundError() throws Exception {
        Long foodImageId = 10432L;
        String requestURI = requestURIBuilder(END_POINT_PATH, foodImageId);

        UpdateFoodImageDTO updateFoodImageDTO = new UpdateFoodImageDTO("3333","UpdateTest");
        String requestBody = objectMapper.writeValueAsString(updateFoodImageDTO);

        when(foodImageService.updateFoodImage(foodImageId, updateFoodImageDTO)).thenThrow(
                new NotFoundException(entityName, foodImageId)
        );
        notFoundExceptionTest(requestURI, HttpMethod.PUT, requestBody);
        verify(foodImageService).updateFoodImage(foodImageId, updateFoodImageDTO);
    }

    @Test
    @DisplayName("Not Found Error Handler: DELETE a food image that doesn't exist")
    @Order(8)
    public void testDeleteFoodImageNotFoundError() throws Exception {
        Long foodImageId = 102L;
        String requestURI = requestURIBuilder(END_POINT_PATH, foodImageId);

        doThrow(new NotFoundException(entityName, foodImageId))
                .when(foodImageService).deleteFoodImage(foodImageId);
        notFoundExceptionTest(requestURI, HttpMethod.DELETE);
        verify(foodImageService).deleteFoodImage(foodImageId);

    }

    @Test
    @DisplayName("Empty Data-table Error Handler: GET ALL food images when there are no food images.")
    @Order(9)
    public void testGetFoodImagesEmptyDataTableError() throws Exception {
        String statusCode = String.valueOf(HttpStatus.NOT_FOUND.value());
        when(foodImageService.getAllFoodImages()).thenThrow(new EmptyDataTableException(tableName));
        emptyDataTableExceptionTest(END_POINT_PATH, statusCode, tableName);
        verify(foodImageService).getAllFoodImages();
    }

    @Test
    @DisplayName("Duplicate Menu Category Error Handling: CREATE a food image that already exist.")
    @Order(10)
    public void testCreateFoodImageDuplicateError() throws Exception {

        String duplicateName = "new image tester";
        CreateFoodImageDTO createFoodImageDTO = new CreateFoodImageDTO("2222",duplicateName);
        String requestBody = objectMapper.writeValueAsString(createFoodImageDTO);

        doThrow(new DuplicateKeyException(duplicateName)).when(foodImageService).createFoodImages(createFoodImageDTO);
        duplicateKeyExceptionTest(END_POINT_PATH, requestBody, duplicateName);
        verify(foodImageService).createFoodImages(createFoodImageDTO);
    }

    @Test
    @DisplayName("Invalid Input Error Handling: : CREATE food image with Invalid input.")
    @Order(11)
    public void testCreateFoodImageValidError() throws Exception {
        String statusCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
        CreateFoodImageDTO createFoodImageDTO = new CreateFoodImageDTO("5555", "");
        String requestBody = objectMapper.writeValueAsString(createFoodImageDTO);

        mockMvc.perform(post(END_POINT_PATH)
                        .contentType("application/json")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$['status code']", is(statusCode)))
                .andExpect(jsonPath("$['imageUrl']", is("must not be blank")));

        /* MethodArgumentNotValidException is thrown at the controller layer, so this test should
        not interact with any code in the controller and any in the service layer. */
        verifyNoInteractions(foodImageService);
        verify(foodImageService, never()).createFoodImages(any());
    }
}
