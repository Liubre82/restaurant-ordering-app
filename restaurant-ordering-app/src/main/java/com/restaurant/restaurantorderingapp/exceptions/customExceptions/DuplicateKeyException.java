package com.restaurant.restaurantorderingapp.exceptions.customExceptions;

public class DuplicateKeyException extends RuntimeException {

    public DuplicateKeyException(String duplicateName) {
        super("entity must be unique, create UNSUCCESSFUL! duplicate entry: " + duplicateName);
    }

    public DuplicateKeyException() {
        super("entity must be unique, create UNSUCCESSFUL! duplicate entry.");
    }
}
