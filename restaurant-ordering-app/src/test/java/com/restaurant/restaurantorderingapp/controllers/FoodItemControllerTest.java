//package com.restaurant.restaurantorderingapp.controllers;
//
package com.restaurant.restaurantorderingapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.restaurantorderingapp.dto.foodImagesDto.FoodImageDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto.FoodItemVariationDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemsDto.CreateFoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemsDto.FoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemsDto.UpdateFoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.foodSizesDto.FoodSizeDTO;
import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.MenuCategoryDTO;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.DuplicateKeyException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.EmptyDataTableException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.NotFoundException;
import com.restaurant.restaurantorderingapp.services.foodServices.FoodItemService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FoodItemControllerTest extends BaseControllerTest{

    private static final String END_POINT_PATH = "/foodItems";
    private static final String tableName = "food items";
    private static final String entityName = "Food Item";
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private FoodItemService foodItemService;
    private FoodItemDTO foodItemTestEntity1;
    private FoodItemDTO foodItemTestEntity2;
    private FoodItemDTO foodItemTestEntity3;
    private MenuCategoryDTO menuCategoryTestEntity1;
    private FoodItemVariationDTO foodItemVariationTestEntity1;
    private FoodItemVariationDTO foodItemVariationTestEntity2;
    private FoodSizeDTO foodSizeTestEntity1;
    private FoodImageDTO foodImageTestEntity1;
    private FoodImageDTO foodImageTestEntity2;
    private static final List<FoodItemDTO> foodItemTestEntityList = new ArrayList<>();
    private static final List<FoodItemVariationDTO> foodItemVariationTestEntityList = new ArrayList<>();
    private static final List<FoodImageDTO> foodImageTestEntityList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        menuCategoryTestEntity1 = new MenuCategoryDTO(1L, "Appetizers");
        foodSizeTestEntity1 = new FoodSizeDTO(1L, "Small");

        foodItemTestEntity1 = new FoodItemDTO("1L", menuCategoryTestEntity1, "Steak", "steak description");
        foodItemTestEntity2 = new FoodItemDTO("2L", menuCategoryTestEntity1,"Miso Soup", "miso soup description");
        foodItemTestEntity3 = new FoodItemDTO("3L", menuCategoryTestEntity1,"Cookie", "description for cookie");

        foodItemVariationTestEntity1 = new FoodItemVariationDTO(1L, foodItemTestEntity1, foodSizeTestEntity1, BigDecimal.valueOf(1.99));
        foodItemVariationTestEntity2 = new FoodItemVariationDTO(2L, foodItemTestEntity1, foodSizeTestEntity1, BigDecimal.valueOf(2.99));

        foodImageTestEntity1 = new FoodImageDTO(1L, foodItemTestEntity1.foodItemId(), "Image 1");
        foodImageTestEntity2 = new FoodImageDTO(2L, foodItemTestEntity1.foodItemId(), "Image 2");

        foodItemTestEntityList.add(foodItemTestEntity1);
        foodItemTestEntityList.add(foodItemTestEntity2);
        foodItemTestEntityList.add(foodItemTestEntity3);

        foodImageTestEntityList.add(foodImageTestEntity1);
        foodImageTestEntityList.add(foodImageTestEntity2);

        foodItemVariationTestEntityList.add(foodItemVariationTestEntity1);
        foodItemVariationTestEntityList.add(foodItemVariationTestEntity2);
    }

    @AfterEach
    public void tearDown() {
        foodItemTestEntityList.clear();
        foodItemVariationTestEntityList.clear();
        foodImageTestEntityList.clear();
    }

    @Test
    @DisplayName("SUCCESSFULLY CREATE a new food item.")
    @Order(1)
    public void testCreateFoodItemSuccess() throws Exception {
        CreateFoodItemDTO createFoodItemDTO = new CreateFoodItemDTO(menuCategoryTestEntity1.menuCategoryId(), "fried calamari", "tasty batter");
        String requestBody = objectMapper.writeValueAsString(createFoodItemDTO);
        String endpoint = "/admin" + END_POINT_PATH;

        doNothing().when(foodItemService).createFoodItems(createFoodItemDTO);
        createRequestSuccessTest(endpoint, requestBody);
        verify(foodItemService).createFoodItems(createFoodItemDTO);
    }

    @Test
    @DisplayName("SUCCESSFULLY GET a food item by Id.")
    @Order(2)
    public void testGetFoodItemSuccess() throws Exception{
        String foodItemId = "1L";
        String requestURI = "/public" + requestURIBuilder(END_POINT_PATH, foodItemId);
        String responseBody = objectMapper.writeValueAsString(foodItemTestEntity1);

        when(foodItemService.getFoodItemById(foodItemId))
                .thenReturn(foodItemTestEntity1);
        getRequestSuccessTest(requestURI, responseBody);
        verify(foodItemService).getFoodItemById(foodItemId);
    }

    @Test
    @DisplayName("SUCCESSFULLY GET ALL food items.")
    @Order(3)
    public void testGetFoodItemsSuccess() throws Exception {
        String responseBody = objectMapper.writeValueAsString(foodItemTestEntityList);
        String endpoint = "/public" + END_POINT_PATH;
        when(foodItemService.getAllFoodItems()).thenReturn(foodItemTestEntityList);
        getRequestSuccessTest(endpoint, responseBody);
        verify(foodItemService).getAllFoodItems();
    }

    @Test
    @DisplayName("SUCCESSFULLY GET ALL food items by name through SEARCH INPUT")
    @Order(4)
    public void testSearchFoodItemsSuccess() throws Exception {
        String searchInput = "soup";
        String requestURI = "/public" + END_POINT_PATH + "/search?searchInput=" + searchInput;
        List<FoodItemDTO> searchList = new ArrayList<>();
        searchList.add(foodItemTestEntity2);
        String responseBody = objectMapper.writeValueAsString(searchList);

        when(foodItemService.searchFoodItems(searchInput)).thenReturn(searchList);
        getRequestSuccessTest(requestURI, responseBody);
        verify(foodItemService).searchFoodItems(searchInput);
    }

    @Test
    @DisplayName("SUCCESSFULLY UPDATE a food item")
    @Order(5)
    public void testUpdateFoodItemSuccess() throws Exception {
        String foodItemId = "1L";
        String requestURI = "/admin" + requestURIBuilder(END_POINT_PATH, foodItemId);

        UpdateFoodItemDTO updateFoodItemDTO = new UpdateFoodItemDTO(1L, "update","UpdateTest");
        String requestBody = objectMapper.writeValueAsString(updateFoodItemDTO);

        FoodItemDTO updatedFoodItemDTO = new FoodItemDTO(foodItemId, menuCategoryTestEntity1, "Steak", "steak description");
        String responseBody = objectMapper.writeValueAsString(updatedFoodItemDTO);

        when(foodItemService.updateFoodItem(foodItemId, updateFoodItemDTO)).thenReturn(updatedFoodItemDTO);
        updateRequestSuccessTest(requestURI, requestBody, responseBody);
        verify(foodItemService).updateFoodItem(foodItemId, updateFoodItemDTO);

    }

    @Test
    @DisplayName("SUCCESSFULLY DELETE a food item.")
    @Order(6)
    public void testDeleteFoodItemSuccess() throws Exception {
        String foodItemId = "1L";
        String requestURI = "/admin" + requestURIBuilder(END_POINT_PATH, foodItemId);

        doNothing().when(foodItemService).deleteFoodItem(foodItemId);
        deleteRequestSuccessTest(requestURI, entityName);
        verify(foodItemService).deleteFoodItem(foodItemId);
    }

    @Test
    @DisplayName("SUCCESSFULLY GET ALL food item variations of a food item.")
    @Order(7)
    public void testGetAllFoodItemVariationsByFoodItemIdSuccess() throws Exception {
        String foodItemId = "1L";
        String requestURI = "/public" + requestURIBuilder(END_POINT_PATH, foodItemId) + "/foodItemVariations";
        String responseBody = objectMapper.writeValueAsString(foodItemVariationTestEntityList);

        when(foodItemService.getAllFoodItemVariations(foodItemId)).thenReturn(foodItemVariationTestEntityList);
        getRequestSuccessTest(requestURI, responseBody);
        verify(foodItemService).getAllFoodItemVariations(foodItemId);
    }

    @Test
    @DisplayName("SUCCESSFULLY GET ALL food images of a food item.")
    @Order(8)
    public void testGetAllFoodImagesByFoodItemIdSuccess() throws Exception {
        String foodItemId = "1L";
        String requestURI = "/public" + requestURIBuilder(END_POINT_PATH, foodItemId) + "/foodImages";
        String responseBody = objectMapper.writeValueAsString(foodImageTestEntityList);

        when(foodItemService.getAllFoodImages(foodItemId)).thenReturn(foodImageTestEntityList);
        getRequestSuccessTest(requestURI, responseBody);
        verify(foodItemService).getAllFoodImages(foodItemId);
    }


    /*  Error Handler tests */

    @Test
    @DisplayName("Not Found Error Handler: GET food item with an unknown Id.")
    @Order(9)
    public void testGetFoodItemNotFoundError() throws Exception {
        String foodItemId = "1255624L";
        String requestURI = "/public" + requestURIBuilder(END_POINT_PATH, foodItemId);

        /* Our getFoodItemById should throw a NotFoundException with a custom msg, so here we
         * create an instance of the exception and pass the parameters it needs to create the custom msg.*/
        when(foodItemService.getFoodItemById(foodItemId)).thenThrow(new NotFoundException(entityName, foodItemId));
        notFoundExceptionTest(requestURI, HttpMethod.GET);
        verify(foodItemService).getFoodItemById(foodItemId);
    }

    @Test
    @DisplayName("Not Found Error Handler: UPDATE a food item that does not exist")
    @Order(10)
    public void testUpdateFoodItemNotFoundError() throws Exception {
        String foodItemId = "10432L";
        String requestURI = "/admin" + requestURIBuilder(END_POINT_PATH, foodItemId);

        UpdateFoodItemDTO updateFoodItemDTO = new UpdateFoodItemDTO(1L, "notFound", "test");
        String requestBody = objectMapper.writeValueAsString(updateFoodItemDTO);

        when(foodItemService.updateFoodItem(foodItemId, updateFoodItemDTO)).thenThrow(
                new NotFoundException(entityName, foodItemId)
        );
        notFoundExceptionTest(requestURI, HttpMethod.PUT, requestBody);
        verify(foodItemService).updateFoodItem(foodItemId, updateFoodItemDTO);
    }

    @Test
    @DisplayName("Not Found Error Handler: DELETE a food item that doesn't exist")
    @Order(11)
    public void testDeleteFoodItemNotFoundError() throws Exception {
        String foodItemId = "102L";
        String requestURI = "/admin" + requestURIBuilder(END_POINT_PATH, foodItemId);

        doThrow(new NotFoundException(entityName, foodItemId))
                .when(foodItemService).deleteFoodItem(foodItemId);
        notFoundExceptionTest(requestURI, HttpMethod.DELETE);
        verify(foodItemService).deleteFoodItem(foodItemId);

    }

    @Test
    @DisplayName("Empty Data-table Error Handler: GET ALL food items when there are no food items.")
    @Order(12)
    public void testGetFoodItemsEmptyDataTableError() throws Exception {
        String endpoint = "/public" + END_POINT_PATH;
        String statusCode = String.valueOf(HttpStatus.NOT_FOUND.value());
        when(foodItemService.getAllFoodItems()).thenThrow(new EmptyDataTableException(tableName));
        emptyDataTableExceptionTest(endpoint, statusCode, tableName);
        verify(foodItemService).getAllFoodItems();
    }

    @Test
    @DisplayName("Duplicate Food Size Error Handling: CREATE a food item that already exist.")
    @Order(13)
    public void testCreateFoodItemDuplicateError() throws Exception {
        String endpoint = "/admin" + END_POINT_PATH;
        String duplicateName = "Steak";
        CreateFoodItemDTO createFoodItemDTO = new CreateFoodItemDTO(1L, duplicateName, "steak description");
        String requestBody = objectMapper.writeValueAsString(createFoodItemDTO);

        doThrow(new DuplicateKeyException(duplicateName)).when(foodItemService).createFoodItems(createFoodItemDTO);
        duplicateKeyExceptionTest(endpoint, requestBody, duplicateName);
        verify(foodItemService).createFoodItems(createFoodItemDTO);
    }

    @Test
    @DisplayName("Invalid Input Error Handling: : CREATE Food Item with Invalid input.")
    @Order(14)
    public void testCreateFoodItemValidError() throws Exception {
        String statusCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
        CreateFoodItemDTO createFoodItemDTO = new CreateFoodItemDTO(1L,"", "test");
        String requestBody = objectMapper.writeValueAsString(createFoodItemDTO);
        String endpoint = "/admin" + END_POINT_PATH;
        mockMvc.perform(post(endpoint)
                        .contentType("application/json")
                        .characterEncoding("utf-8")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$['status code']", is(statusCode)))
                .andExpect(jsonPath("$['foodItemName']", is("must not be blank")));

        /* MethodArgumentNotValidException is thrown at the controller layer, so this test should
        not interact with any code in the controller and any in the service layer. */
        verifyNoInteractions(foodItemService);
        verify(foodItemService, never()).createFoodItems(any());
    }

}



