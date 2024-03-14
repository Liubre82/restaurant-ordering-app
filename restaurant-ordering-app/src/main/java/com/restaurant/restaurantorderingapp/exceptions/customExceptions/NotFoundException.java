package com.restaurant.restaurantorderingapp.exceptions.customExceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String entityName, Long id) {
        super(entityName + " not found with ID: " + id);
    }
}