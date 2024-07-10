package com.restaurant.restaurantorderingapp.controllers.orderSystemController;

import com.restaurant.restaurantorderingapp.dto.orderSystemDto.CreateUserFoodOrderDTO;
import com.restaurant.restaurantorderingapp.services.orderSystemServices.OrderSystemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderSystemController {

    public final OrderSystemService orderSystemService;

    @Autowired
    public OrderSystemController(OrderSystemService orderSystemService) {
        this.orderSystemService = orderSystemService;
    }

    @PostMapping("/authUsers/users/{userId}/userOrdersAndItems")
    public ResponseEntity<String> createUserOrderAndFoodItems(@RequestBody @Valid CreateUserFoodOrderDTO createUserFoodOrderDTO,
                                                              @PathVariable String userId) {
        orderSystemService.createUserFoodOrder(createUserFoodOrderDTO, userId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Created User Order and User Food Items");
    }
}
