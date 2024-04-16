package com.restaurant.restaurantorderingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters  = false)
public abstract class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    protected String jwt;
//
//    protected UserRoleTestEntity userRole;
//
//    protected static UserTestEntity user;
//    private static SecretKey SECRET_KEY;
//    private static final long EXPIRATION_TIME_MS = 86400000; // 1 day in milliseconds
//
//    public static String generateToken(UserTestEntity user) {
//        String secreteString = "843567893696976453275974432697R634976R738467TR678T34865R6834R8763T478378637664538745673865783678548735687R3";
//        byte[] keyBytes = Base64.getDecoder().decode(secreteString.getBytes(StandardCharsets.UTF_8));
//        SECRET_KEY = new SecretKeySpec(keyBytes, "HmacSHA256");
//        return Jwts.builder()
//                .subject(user.userId())
//                .claim("username", user.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//    }
//
//    @BeforeEach
//    public void setUpUser() {
//        userRole = new UserRoleTestEntity("1", "ADMIN");
//        user = new UserTestEntity("1eb61db0-a929-444a-ab89-f5e07d4eba55", userRole, "admin", "admin@test.com");
//        jwt = generateToken(user);
//        System.out.println(jwt);
//    }
//
//    @AfterEach
//    public void tearDown() {
//
//    }

    public String requestURIBuilder(String endpoint, Long id) {
        return endpoint + "/" + id;
    }

    public String requestURIBuilder(String endpoint, String id) {
        return endpoint + "/" + id;
    }

    protected void getRequestSuccessTest(String endpoint, String responseBody) throws Exception {
        mockMvc.perform(get(endpoint)
                        .header("Authorization", "Bearer " + jwt))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    protected void updateRequestSuccessTest(String endpoint, String requestBody, String responseBody) throws Exception {
        mockMvc.perform(put(endpoint)
                        .header("Authorization", "Bearer " + jwt)
                        .contentType("application/json")
                        .characterEncoding("utf-8")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    protected void deleteRequestSuccessTest(String endpoint, String entityName) throws Exception {
        mockMvc.perform(delete(endpoint)
                        .header("Authorization", "Bearer " + jwt))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(entityName + " deleted successfully."));
    }

    protected void createRequestSuccessTest(String endpoint, String requestBody) throws Exception {
        mockMvc.perform(post(endpoint)
                        .header("Authorization", "Bearer " + jwt)
                        .contentType("application/json")
                        .characterEncoding("utf-8")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    protected void notFoundExceptionTest(String endpoint, HttpMethod httpMethod) throws Exception {
        mockMvc.perform(request(httpMethod, endpoint)
                        .header("Authorization", "Bearer " + jwt))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    protected void notFoundExceptionTest(String endpoint, HttpMethod httpMethod, String requestBody) throws Exception {
        mockMvc.perform(request(httpMethod, endpoint)
                        .header("Authorization", "Bearer " + jwt)
                        .contentType("application/json")
                        .characterEncoding("utf-8")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    protected void duplicateKeyExceptionTest(String endpoint, String requestBody, String duplicateName) throws Exception {
        String statusCode = String.valueOf(HttpStatus.CONFLICT.value());
        mockMvc.perform(post(endpoint)
                        .header("Authorization", "Bearer " + jwt)
                        .contentType("application/json")
                        .characterEncoding("utf-8")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$['status code']", is(statusCode)))
                .andExpect(jsonPath("$['message']", is("entity must be unique, create UNSUCCESSFUL! duplicate entry: " + duplicateName)));;
    }

    protected void emptyDataTableExceptionTest(String endpoint, String statusCode, String tableName) throws Exception {
        mockMvc.perform(get(endpoint)
                        .header("Authorization", "Bearer " + jwt))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$['status code']", is(statusCode)))
                .andExpect(jsonPath("$['message']", is("Empty data-table, there are no " + tableName)));
    }



}
