package com.restaurant.restaurantorderingapp.exceptions.customExceptions;

public class UserNotAuthorizedException extends RuntimeException{

    public UserNotAuthorizedException(String userId, String entityName, Long reviewId) {
        super("User: " + userId + " is not authorized to change " + entityName + " ID: " + reviewId + ".");
    }
}
