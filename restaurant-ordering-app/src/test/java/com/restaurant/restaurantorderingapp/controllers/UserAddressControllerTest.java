package com.restaurant.restaurantorderingapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.restaurantorderingapp.dto.userAddressesDto.CreateUserAddressDTO;
import com.restaurant.restaurantorderingapp.dto.userAddressesDto.UpdateUserAddressDTO;
import com.restaurant.restaurantorderingapp.dto.userAddressesDto.UserAddressDTO;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.EmptyDataTableException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.NotFoundException;
import com.restaurant.restaurantorderingapp.services.userServices.UserAddressService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserAddressControllerTest extends BaseControllerTest{

    private static final String END_POINT_PATH = "/userAddresses";
    private static final String tableName = "user addresses";
    private static final String entityName = "User Address";
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserAddressService userAddressService;
    private UserAddressDTO userAddressTestEntity1;
    private UserAddressDTO userAddressTestEntity2;
    private UserAddressDTO userAddressTestEntity3;
    private static final List<UserAddressDTO> userAddressTestEntityList = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        userAddressTestEntity1 = new UserAddressDTO(1L, "1L", "1205 garfield drive", "Los Angeles", "California", "09563");
        userAddressTestEntity2 = new UserAddressDTO(2L, "2L", "201 Mantua grove", "West Deptford", "New Jersey", "65239");
        userAddressTestEntity3 = new UserAddressDTO(3L, "3L", "1 lincoln memorial street", "Philadephia", "Pennsylvannia", "05236");

        userAddressTestEntityList.add(userAddressTestEntity1);
        userAddressTestEntityList.add(userAddressTestEntity2);
        userAddressTestEntityList.add(userAddressTestEntity3);
    }

    @AfterEach
    public void tearDown() {
        userAddressTestEntityList.clear();
    }

    @Test
    @DisplayName("SUCCESSFULLY CREATE a new user address.")
    @Order(1)
    public void testCreateUserAddressSuccess() throws Exception {
        CreateUserAddressDTO createUserAddressDTO = new CreateUserAddressDTO("3L", "321 Spring dale road", "Miami", "Florida", "26321");
        String requestBody = objectMapper.writeValueAsString(createUserAddressDTO);
        String endpoint = "/authUsers" + END_POINT_PATH;

        doNothing().when(userAddressService).createUserAddress(createUserAddressDTO);
        createRequestSuccessTest(endpoint, requestBody);
        verify(userAddressService).createUserAddress(createUserAddressDTO);
    }

    @Test
    @DisplayName("SUCCESSFULLY GET a user address by Id.")
    @Order(2)
    public void testGetUserAddressSuccess() throws Exception{
        Long userAddressId = 1L;
        String requestURI = "/authUsers" + requestURIBuilder(END_POINT_PATH, userAddressId);
        String responseBody = objectMapper.writeValueAsString(userAddressTestEntity1);

        when(userAddressService.getUserAddressById(userAddressId))
                .thenReturn(userAddressTestEntity1);
        getRequestSuccessTest(requestURI, responseBody);
        verify(userAddressService).getUserAddressById(userAddressId);
    }

    @Test
    @DisplayName("SUCCESSFULLY GET ALL user addresses.")
    @Order(3)
    public void testGetUserAddressesSuccess() throws Exception {
        String responseBody = objectMapper.writeValueAsString(userAddressTestEntityList);
        String endpoint = "/admin" + END_POINT_PATH;
        when(userAddressService.getAllUserAddresses()).thenReturn(userAddressTestEntityList);
        getRequestSuccessTest(endpoint, responseBody);
        verify(userAddressService).getAllUserAddresses();
    }

    @Test
    @DisplayName("SUCCESSFULLY UPDATE a user address")
    @Order(5)
    public void testUpdateUserAddressSuccess() throws Exception {
        Long userAddressId = 1L;
        String requestURI = "/authUsers" + requestURIBuilder(END_POINT_PATH, userAddressId);

        UpdateUserAddressDTO updateUserAddressDTO = new UpdateUserAddressDTO("3L", "321 Spring dale road",
                "Miami", "Florida", "26321");
        String requestBody = objectMapper.writeValueAsString(updateUserAddressDTO);

        UserAddressDTO updatedUserAddressDTO = new UserAddressDTO(userAddressId,
                updateUserAddressDTO.userId(), updateUserAddressDTO.addressName(), updateUserAddressDTO.city(),
                updateUserAddressDTO.state(), updateUserAddressDTO.zipCode());
        String responseBody = objectMapper.writeValueAsString(updatedUserAddressDTO);

        when(userAddressService.updateUserAddress(userAddressId, updateUserAddressDTO)).thenReturn(updatedUserAddressDTO);
        updateRequestSuccessTest(requestURI, requestBody, responseBody);
        verify(userAddressService).updateUserAddress(userAddressId, updateUserAddressDTO);

    }

    @Test
    @DisplayName("SUCCESSFULLY DELETE a user address.")
    @Order(6)
    public void testDeleteUserAddressSuccess() throws Exception {
        Long userAddressId = 1L;
        String requestURI = "/authUsers" + requestURIBuilder(END_POINT_PATH, userAddressId);

        doNothing().when(userAddressService).deleteUserAddress(userAddressId);
        deleteRequestSuccessTest(requestURI, entityName);
        verify(userAddressService).deleteUserAddress(userAddressId);
    }

    

    /*  Error Handler tests */

    @Test
    @DisplayName("Not Found Error Handler: GET user address with an unknown Id.")
    @Order(9)
    public void testGetUserAddressNotFoundError() throws Exception {
        Long userAddressId = 1255624L;
        String requestURI = "/authUsers" + requestURIBuilder(END_POINT_PATH, userAddressId);

        /* Our getUserAddressById should throw a NotFoundException with a custom msg, so here we
         * create an instance of the exception and pass the parameters it needs to create the custom msg.*/
        when(userAddressService.getUserAddressById(userAddressId)).thenThrow(new NotFoundException(entityName, userAddressId));
        notFoundExceptionTest(requestURI, HttpMethod.GET);
        verify(userAddressService).getUserAddressById(userAddressId);
    }

    @Test
    @DisplayName("Not Found Error Handler: UPDATE a user address that does not exist")
    @Order(10)
    public void testUpdateUserAddressNotFoundError() throws Exception {
        Long userAddressId = 10432L;
        String requestURI = "/authUsers" + requestURIBuilder(END_POINT_PATH, userAddressId);

        UpdateUserAddressDTO updateUserAddressDTO = new UpdateUserAddressDTO("3L", "321 Spring dale road",
                "Miami", "Florida", "26321");
        String requestBody = objectMapper.writeValueAsString(updateUserAddressDTO);

        when(userAddressService.updateUserAddress(userAddressId, updateUserAddressDTO)).thenThrow(
                new NotFoundException(entityName, userAddressId)
        );
        notFoundExceptionTest(requestURI, HttpMethod.PUT, requestBody);
        verify(userAddressService).updateUserAddress(userAddressId, updateUserAddressDTO);
    }

    @Test
    @DisplayName("Not Found Error Handler: DELETE a user address that doesn't exist")
    @Order(11)
    public void testDeleteUserAddressNotFoundError() throws Exception {
        Long userAddressId = 102L;
        String requestURI = "/authUsers" + requestURIBuilder(END_POINT_PATH, userAddressId);

        doThrow(new NotFoundException(entityName, userAddressId))
                .when(userAddressService).deleteUserAddress(userAddressId);
        notFoundExceptionTest(requestURI, HttpMethod.DELETE);
        verify(userAddressService).deleteUserAddress(userAddressId);

    }

    @Test
    @DisplayName("Empty Data-table Error Handler: GET ALL user addresses when there are no user addresses.")
    @Order(12)
    public void testGetUserAddressEmptyDataTableError() throws Exception {
        String endpoint = "/admin" + END_POINT_PATH;
        String statusCode = String.valueOf(HttpStatus.NOT_FOUND.value());
        when(userAddressService.getAllUserAddresses()).thenThrow(new EmptyDataTableException(tableName));
        emptyDataTableExceptionTest(endpoint, statusCode, tableName);
        verify(userAddressService).getAllUserAddresses();
    }

    @Test
    @DisplayName("Invalid Input Error Handling: : CREATE User Address with Invalid input.")
    @Order(14)
    public void testCreateUserAddressValidError() throws Exception {
        String statusCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
        CreateUserAddressDTO createUserAddressDTO = new CreateUserAddressDTO("1L","",
                "asd", "asd", "asd");
        String requestBody = objectMapper.writeValueAsString(createUserAddressDTO);
        String endpoint = "/authUsers" + END_POINT_PATH;
        mockMvc.perform(post(endpoint)
                        .contentType("application/json")
                        .characterEncoding("utf-8")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$['status code']", is(statusCode)))
                .andExpect(jsonPath("$['addressName']", is("must not be blank")));

        /* MethodArgumentNotValidException is thrown at the controller layer, so this test should
        not interact with any code in the controller and any in the service layer. */
        verifyNoInteractions(userAddressService);
        verify(userAddressService, never()).createUserAddress(any());
    }

}



