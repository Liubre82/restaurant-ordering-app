package com.restaurant.restaurantorderingapp.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.restaurant.restaurantorderingapp.models.user.User;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthReqRes {

    private int statusCode;
    private String error;
    private String message;
    private String accessToken;
    private String refreshToken;
    private String expirationTime;
    private String username;
    private String email;
    private String userRoleName;
    private String password;
    private User user;
    private String userId;
}