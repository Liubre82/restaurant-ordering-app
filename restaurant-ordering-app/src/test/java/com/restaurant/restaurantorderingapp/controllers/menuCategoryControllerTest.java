package com.restaurant.restaurantorderingapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.CreateMenuCategoryDTO;
import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.MenuCategoryDTO;
import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.UpdateMenuCategoryDTO;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.DuplicateKeyException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.EmptyDataTableException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.NotFoundException;
import com.restaurant.restaurantorderingapp.services.MenuCategoryService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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


@WebMvcTest(MenuCategoryController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class menuCategoryControllerTest extends BaseControllerTest{

    private static final String END_POINT_PATH = "/menuCategories";
    private static final String tableName = "menu categories";
    private static final String entityName = "Menu Category";
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MenuCategoryService menuCategoryService;
    private MenuCategoryDTO menuCategoryTestEntity1;
    private MenuCategoryDTO menuCategoryTestEntity2;
    private MenuCategoryDTO menuCategoryTestEntity3;
    private static final List<MenuCategoryDTO> menuCategoryTestEntityList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        menuCategoryTestEntity1 = new MenuCategoryDTO(1L, "Appetizers");
        menuCategoryTestEntity2 = new MenuCategoryDTO(2L, "Desserts");
        menuCategoryTestEntity3 = new MenuCategoryDTO(3L, "Beverages");

        menuCategoryTestEntityList.add(menuCategoryTestEntity1);
        menuCategoryTestEntityList.add(menuCategoryTestEntity2);
        menuCategoryTestEntityList.add(menuCategoryTestEntity3);
    }

    @AfterEach
    public void tearDown() {
        menuCategoryTestEntityList.clear();
    }

    @Test
    @DisplayName("SUCCESSFULLY CREATE a new menu category.")
    @Order(1)
    public void testCreateMenuCategorySuccess() throws Exception {
        CreateMenuCategoryDTO createMenuCategoryDTO = new CreateMenuCategoryDTO("testing");
        String requestBody = objectMapper.writeValueAsString(createMenuCategoryDTO);

        doNothing().when(menuCategoryService).createMenuCategories(createMenuCategoryDTO);
        createRequestSuccessTest(END_POINT_PATH, requestBody);
        verify(menuCategoryService).createMenuCategories(createMenuCategoryDTO);
    }

    @Test
    @DisplayName("SUCCESSFULLY GET a menu category by Id.")
    @Order(2)
    public void testGetMenuCategorySuccess() throws Exception{
        Long menuCategoryId = 1L;
        String requestURI = requestURIBuilder(END_POINT_PATH, menuCategoryId);
        String responseBody = objectMapper.writeValueAsString(menuCategoryTestEntity1);

        when(menuCategoryService.getMenuCategoryById(menuCategoryId))
                .thenReturn(menuCategoryTestEntity1);
        getRequestSuccessTest(requestURI, responseBody);
        verify(menuCategoryService).getMenuCategoryById(menuCategoryId);
    }

    @Test
    @DisplayName("SUCCESSFULLY GET ALL menu categories.")
    @Order(3)
    public void testGetMenuCategoriesSuccess() throws Exception {
        String responseBody = objectMapper.writeValueAsString(menuCategoryTestEntityList);

        when(menuCategoryService.getAllMenuCategories()).thenReturn(menuCategoryTestEntityList);
        getRequestSuccessTest(END_POINT_PATH, responseBody);
        verify(menuCategoryService).getAllMenuCategories();
    }

    @Test
    @DisplayName("SUCCESSFULLY GET ALL menu categories by name through SEARCH INPUT")
    @Order(4)
    public void testSearchMenuCategoriesSuccess() throws Exception {
        String searchInput = "es";
        String requestURI = END_POINT_PATH + "/search?searchInput=" + searchInput;
        List<MenuCategoryDTO> searchList = new ArrayList<>();
        searchList.add(menuCategoryTestEntity2);
        searchList.add(menuCategoryTestEntity3);
        String responseBody = objectMapper.writeValueAsString(searchList);

        when(menuCategoryService.searchMenuCategories(searchInput)).thenReturn(searchList);
        getRequestSuccessTest(requestURI, responseBody);
        verify(menuCategoryService).searchMenuCategories(searchInput);
    }

    @Test
    @DisplayName("SUCCESSFULLY UPDATE a menu category ")
    @Order(5)
    public void testUpdateMenuCategorySuccess() throws Exception {
        Long menuCategoryId = 1L;
        String requestURI = requestURIBuilder(END_POINT_PATH, menuCategoryId);

        UpdateMenuCategoryDTO updateMenuCategoryDTO = new UpdateMenuCategoryDTO("UpdateTest");
        String requestBody = objectMapper.writeValueAsString(updateMenuCategoryDTO);

        MenuCategoryDTO updatedMenuCategoryDTO = new MenuCategoryDTO(menuCategoryId, updateMenuCategoryDTO.menuCategoryName());
        String responseBody = objectMapper.writeValueAsString(updatedMenuCategoryDTO);

        when(menuCategoryService.updateMenuCategory(menuCategoryId, updateMenuCategoryDTO)).thenReturn(updatedMenuCategoryDTO);
        updateRequestSuccessTest(requestURI, requestBody, responseBody);
        verify(menuCategoryService).updateMenuCategory(menuCategoryId, updateMenuCategoryDTO);

    }

    @Test
    @DisplayName("SUCCESSFULLY DELETE a menu category.")
    @Order(6)
    public void testDeleteMenuCategorySuccess() throws Exception {
        Long menuCategoryId = 1L;
        String requestURI = requestURIBuilder(END_POINT_PATH, menuCategoryId);

        doNothing().when(menuCategoryService).deleteMenuCategory(menuCategoryId);
        deleteRequestSuccessTest(requestURI, entityName);
        verify(menuCategoryService).deleteMenuCategory(menuCategoryId);
    }


    /*  Error Handler tests */

    @Test
    @DisplayName("Not Found Error Handler: GET menu category with an unknown Id.")
    @Order(7)
    public void testGetMenuCategoryNotFoundError() throws Exception {
        Long menuCategoryId = 1255624L;
        String requestURI = requestURIBuilder(END_POINT_PATH, menuCategoryId);

        /* Our getMenuCategoryById should throw a NotFoundException with a custom msg, so here we
        * create an instance of the exception and pass the parameters it needs to create the custom msg.*/
        when(menuCategoryService.getMenuCategoryById(menuCategoryId)).thenThrow(new NotFoundException(entityName, menuCategoryId));
        notFoundExceptionTest(requestURI, HttpMethod.GET);
        verify(menuCategoryService).getMenuCategoryById(menuCategoryId);
    }

    @Test
    @DisplayName("Not Found Error Handler: UPDATE a menu category that does not exist")
    @Order(8)
    public void testUpdateMenuCategoryNotFoundError() throws Exception {
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
    @DisplayName("Not Found Error Handler: DELETE a menu category that doesn't exist")
    @Order(9)
    public void testDeleteMenuCategoryNotFoundError() throws Exception {
        Long menuCategoryId = 102L;
        String requestURI = requestURIBuilder(END_POINT_PATH, menuCategoryId);

        doThrow(new NotFoundException(entityName, menuCategoryId))
                .when(menuCategoryService).deleteMenuCategory(menuCategoryId);
        notFoundExceptionTest(requestURI, HttpMethod.DELETE);
        verify(menuCategoryService).deleteMenuCategory(menuCategoryId);

    }

    @Test
    @DisplayName("Empty Data-table Error Handler: GET ALL menu categories when there are no menu categories.")
    @Order(10)
    public void testGetMenuCategoriesEmptyDataTableError() throws Exception {
        String statusCode = String.valueOf(HttpStatus.NOT_FOUND.value());
        when(menuCategoryService.getAllMenuCategories()).thenThrow(new EmptyDataTableException(tableName));
        emptyDataTableExceptionTest(END_POINT_PATH, statusCode, tableName);
        verify(menuCategoryService).getAllMenuCategories();
    }

    @Test
    @DisplayName("Duplicate Menu Category Error Handling: CREATE a menu category that already exist.")
    @Order(11)
    public void testCreateMenuCategoryDuplicateError() throws Exception {

        String duplicateName = "Appetizer";
        CreateMenuCategoryDTO createMenuCategoryDTO = new CreateMenuCategoryDTO(duplicateName);
        String requestBody = objectMapper.writeValueAsString(createMenuCategoryDTO);

        doThrow(new DuplicateKeyException(duplicateName)).when(menuCategoryService).createMenuCategories(createMenuCategoryDTO);
        duplicateKeyExceptionTest(END_POINT_PATH, requestBody, duplicateName);
        verify(menuCategoryService).createMenuCategories(createMenuCategoryDTO);
    }

    @Test
    @DisplayName("Invalid Input Error Handling: : CREATE Menu Category with Invalid input.")
    @Order(12)
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
        verifyNoInteractions(menuCategoryService);
        verify(menuCategoryService, never()).createMenuCategories(any());
    }

}


