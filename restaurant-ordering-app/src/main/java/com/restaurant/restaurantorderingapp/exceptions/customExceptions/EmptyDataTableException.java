package com.restaurant.restaurantorderingapp.exceptions.customExceptions;

public class EmptyDataTableException extends RuntimeException{

    public EmptyDataTableException(String name) {
        super("Empty data-table, there are no " + name);
    }
}
