package com.restaurant.restaurantorderingapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/testing")
    public ResponseEntity<String> testing() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Testing", "this-works")
                .body("This is a test!!");
    }
}
