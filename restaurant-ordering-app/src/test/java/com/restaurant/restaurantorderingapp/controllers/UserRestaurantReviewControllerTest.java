package com.restaurant.restaurantorderingapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.restaurantorderingapp.dto.userRestaurantReviewsDto.CreateUserRestaurantReviewDTO;
import com.restaurant.restaurantorderingapp.dto.userRestaurantReviewsDto.UpdateUserRestaurantReviewDTO;
import com.restaurant.restaurantorderingapp.dto.userRestaurantReviewsDto.UserRestaurantReviewDTO;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.EmptyDataTableException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.NotFoundException;
import com.restaurant.restaurantorderingapp.services.userServices.UserRestaurantReviewService;
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
public class UserRestaurantReviewControllerTest extends BaseControllerTest{


    private static final String END_POINT_PATH = "/userRestaurantReviews";
    private static final String tableName = "user restaurant reviews";
    private static final String entityName = "User Restaurant Review";
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserRestaurantReviewService userRestaurantReviewService;
    private UserRestaurantReviewDTO userRestaurantReviewTestEntity1;
    private UserRestaurantReviewDTO userRestaurantReviewTestEntity2;
    private UserRestaurantReviewDTO userRestaurantReviewTestEntity3;
    private static final List<UserRestaurantReviewDTO> userRestaurantReviewTestEntityList = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        userRestaurantReviewTestEntity1 = new UserRestaurantReviewDTO(1L,"1L", "Tastes like shit",
                BigDecimal.valueOf(1), "Don't ever go to this trash ass place, food taste like doodoo", "04-04-2024 01:35 AM");
        userRestaurantReviewTestEntity2 = new UserRestaurantReviewDTO(2L,"2L", "Amazing tasting restaurant",
                BigDecimal.valueOf(5), "Love the food here, would come again", "04-04-2024 03:35 PM");
        userRestaurantReviewTestEntity3 = new UserRestaurantReviewDTO(3L,"1L", "solid food, could be better",
                BigDecimal.valueOf(3.5), "I loved the soup, but the dessert was kinda stale", "04-03-2024 11:35 AM");

        userRestaurantReviewTestEntityList.add(userRestaurantReviewTestEntity1);
        userRestaurantReviewTestEntityList.add(userRestaurantReviewTestEntity2);
        userRestaurantReviewTestEntityList.add(userRestaurantReviewTestEntity3);
    }

    @AfterEach
    public void tearDown() {
        userRestaurantReviewTestEntityList.clear();
    }

    @Test
    @DisplayName("SUCCESSFULLY CREATE a new user restaurant review.")
    @Order(1)
    public void testCreateUserRestaurantReviewSuccess() throws Exception {
        CreateUserRestaurantReviewDTO createUserRestaurantReviewDTO = new CreateUserRestaurantReviewDTO("3L",
                "good food, good environment", BigDecimal.valueOf(2),
                "Good food, could be better");
        String requestBody = objectMapper.writeValueAsString(createUserRestaurantReviewDTO);
        String endpoint = "/authUsers" + END_POINT_PATH;

        doNothing().when(userRestaurantReviewService).createUserRestaurantReview(createUserRestaurantReviewDTO);
        createRequestSuccessTest(endpoint, requestBody);
        verify(userRestaurantReviewService).createUserRestaurantReview(createUserRestaurantReviewDTO);
    }

    @Test
    @DisplayName("SUCCESSFULLY GET a user restaurant review by Id.")
    @Order(2)
    public void testGetUserRestaurantReviewSuccess() throws Exception{
        Long userRestaurantReviewId = 1L;
        String requestURI = "/public" + requestURIBuilder(END_POINT_PATH, userRestaurantReviewId);
        String responseBody = objectMapper.writeValueAsString(userRestaurantReviewTestEntity1);

        when(userRestaurantReviewService.getUserRestaurantReviewById(userRestaurantReviewId))
                .thenReturn(userRestaurantReviewTestEntity1);
        getRequestSuccessTest(requestURI, responseBody);
        verify(userRestaurantReviewService).getUserRestaurantReviewById(userRestaurantReviewId);
    }

    @Test
    @DisplayName("SUCCESSFULLY GET ALL user restaurant reviews.")
    @Order(3)
    public void testGetUserRestaurantReviewsSuccess() throws Exception {
        String responseBody = objectMapper.writeValueAsString(userRestaurantReviewTestEntityList);
        String endpoint = "/public" + END_POINT_PATH;
        when(userRestaurantReviewService.getAllUserRestaurantReviews()).thenReturn(userRestaurantReviewTestEntityList);
        getRequestSuccessTest(endpoint, responseBody);
        verify(userRestaurantReviewService).getAllUserRestaurantReviews();
    }

    @Test
    @DisplayName("SUCCESSFULLY UPDATE a user restaurant review")
    @Order(5)
    public void testUpdateUserRestaurantReviewSuccess() throws Exception {
        Long userRestaurantReviewId = 1L;
        String requestURI = "/authUsers" + requestURIBuilder(END_POINT_PATH, userRestaurantReviewId);

        UpdateUserRestaurantReviewDTO updateUserRestaurantReviewDTO = new UpdateUserRestaurantReviewDTO("1L",
                "Tastes like shit", BigDecimal.valueOf(1), "Don't ever go to this trash ass place, food taste like doodoo");
        String requestBody = objectMapper.writeValueAsString(updateUserRestaurantReviewDTO);

        UserRestaurantReviewDTO updatedUserRestaurantReviewDTO = new UserRestaurantReviewDTO(userRestaurantReviewId,
                updateUserRestaurantReviewDTO.userId(), updateUserRestaurantReviewDTO.userRestaurantReviewTitle(),
                updateUserRestaurantReviewDTO.userRestaurantRating(),
                updateUserRestaurantReviewDTO.userRestaurantReviewDescription(), "04-04-2024 03:35 PM");
        String responseBody = objectMapper.writeValueAsString(updatedUserRestaurantReviewDTO);

        when(userRestaurantReviewService.updateUserRestaurantReview(userRestaurantReviewId, updateUserRestaurantReviewDTO)).thenReturn(updatedUserRestaurantReviewDTO);
        updateRequestSuccessTest(requestURI, requestBody, responseBody);
        verify(userRestaurantReviewService).updateUserRestaurantReview(userRestaurantReviewId, updateUserRestaurantReviewDTO);

    }

    @Test
    @DisplayName("SUCCESSFULLY DELETE a user restaurant review.")
    @Order(6)
    public void testDeleteUserRestaurantReviewSuccess() throws Exception {
        Long userRestaurantReviewId = 1L;
        String requestURI = "/authUsers" + requestURIBuilder(END_POINT_PATH, userRestaurantReviewId);

        doNothing().when(userRestaurantReviewService).deleteUserRestaurantReview(userRestaurantReviewId);
        deleteRequestSuccessTest(requestURI, entityName);
        verify(userRestaurantReviewService).deleteUserRestaurantReview(userRestaurantReviewId);
    }



    /*  Error Handler tests */

    @Test
    @DisplayName("Not Found Error Handler: GET user restaurant review with an unknown Id.")
    @Order(9)
    public void testGetUserRestaurantReviewNotFoundError() throws Exception {
        Long userRestaurantReviewId = 1255624L;
        String requestURI = "/public" + requestURIBuilder(END_POINT_PATH, userRestaurantReviewId);

        /* Our getUserRestaurantReviewById should throw a NotFoundException with a custom msg, so here we
         * create an instance of the exception and pass the parameters it needs to create the custom msg.*/
        when(userRestaurantReviewService.getUserRestaurantReviewById(userRestaurantReviewId)).thenThrow(new NotFoundException(entityName, userRestaurantReviewId));
        notFoundExceptionTest(requestURI, HttpMethod.GET);
        verify(userRestaurantReviewService).getUserRestaurantReviewById(userRestaurantReviewId);
    }

    @Test
    @DisplayName("Not Found Error Handler: UPDATE a user restaurant review that does not exist")
    @Order(10)
    public void testUpdateUserRestaurantReviewNotFoundError() throws Exception {
        Long userRestaurantReviewId = 10432L;
        String requestURI = "/authUsers" + requestURIBuilder(END_POINT_PATH, userRestaurantReviewId);

        UpdateUserRestaurantReviewDTO updateUserRestaurantReviewDTO = new UpdateUserRestaurantReviewDTO("1L",
                "Tastes like shit", BigDecimal.valueOf(1), "Don't ever go to this trash ass place, food taste like doodoo");

        String requestBody = objectMapper.writeValueAsString(updateUserRestaurantReviewDTO);

        when(userRestaurantReviewService.updateUserRestaurantReview(userRestaurantReviewId, updateUserRestaurantReviewDTO)).thenThrow(
                new NotFoundException(entityName, userRestaurantReviewId)
        );
        notFoundExceptionTest(requestURI, HttpMethod.PUT, requestBody);
        verify(userRestaurantReviewService).updateUserRestaurantReview(userRestaurantReviewId, updateUserRestaurantReviewDTO);
    }

    @Test
    @DisplayName("Not Found Error Handler: DELETE a user restaurant review that doesn't exist")
    @Order(11)
    public void testDeleteUserRestaurantReviewNotFoundError() throws Exception {
        Long userRestaurantReviewId = 102L;
        String requestURI = "/authUsers" + requestURIBuilder(END_POINT_PATH, userRestaurantReviewId);

        doThrow(new NotFoundException(entityName, userRestaurantReviewId))
                .when(userRestaurantReviewService).deleteUserRestaurantReview(userRestaurantReviewId);
        notFoundExceptionTest(requestURI, HttpMethod.DELETE);
        verify(userRestaurantReviewService).deleteUserRestaurantReview(userRestaurantReviewId);

    }

    @Test
    @DisplayName("Empty Data-table Error Handler: GET ALL user restaurant reviews when there are no user restaurant reviews.")
    @Order(12)
    public void testGetUserRestaurantReviewEmptyDataTableError() throws Exception {
        String endpoint = "/public" + END_POINT_PATH;
        String statusCode = String.valueOf(HttpStatus.NOT_FOUND.value());
        when(userRestaurantReviewService.getAllUserRestaurantReviews()).thenThrow(new EmptyDataTableException(tableName));
        emptyDataTableExceptionTest(endpoint, statusCode, tableName);
        verify(userRestaurantReviewService).getAllUserRestaurantReviews();
    }

    @Test
    @DisplayName("Invalid Input Error Handling: : CREATE User Restaurant Review with Invalid input.")
    @Order(14)
    public void testCreateUserRestaurantReviewValidError() throws Exception {
        String statusCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
        CreateUserRestaurantReviewDTO createUserRestaurantReviewDTO = new CreateUserRestaurantReviewDTO("1L",
                "Tastes like shit", BigDecimal.valueOf(1), "");
        String requestBody = objectMapper.writeValueAsString(createUserRestaurantReviewDTO);
        String endpoint = "/authUsers" + END_POINT_PATH;
        mockMvc.perform(post(endpoint)
                        .contentType("application/json")
                        .characterEncoding("utf-8")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$['status code']", is(statusCode)))
                .andExpect(jsonPath("$['userRestaurantReviewDescription']", is("must not be blank")));

        /* MethodArgumentNotValidException is thrown at the controller layer, so this test should
        not interact with any code in the controller and any in the service layer. */
        verifyNoInteractions(userRestaurantReviewService);
        verify(userRestaurantReviewService, never()).createUserRestaurantReview(any());
    }

}

