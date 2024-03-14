package com.restaurant.restaurantorderingapp.exceptions.customExceptions;

public class EmptyResultException extends RuntimeException{

    public EmptyResultException(String name) {
        super("Empty result, there are no " + name);
    }
}
