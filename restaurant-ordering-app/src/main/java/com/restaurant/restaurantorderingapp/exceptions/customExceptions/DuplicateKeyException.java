package com.restaurant.restaurantorderingapp.exceptions.customExceptions;

public class DuplicateKeyException extends RuntimeException {

    public DuplicateKeyException(String duplicateName) {
        super("entity must be unique, create unsuccessful! duplicate entry: " + duplicateName);
    }
}
