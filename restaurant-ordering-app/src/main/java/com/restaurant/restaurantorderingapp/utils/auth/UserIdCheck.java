package com.restaurant.restaurantorderingapp.utils.auth;

public class UserIdCheck {

    private UserIdCheck(){}

    public static boolean checkUserId(String userId, String userIdCheck) {
        return userId.equals(userIdCheck);
    }
}
