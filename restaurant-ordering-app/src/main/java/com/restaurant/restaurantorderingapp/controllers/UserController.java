package com.restaurant.restaurantorderingapp.controllers;

import com.restaurant.restaurantorderingapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/{userId}")
//    public ResponseEntity<UserDTO> getUserDetails(@PathVariable String userId) {
//        Optional<User> userDTO = userService.findUser(userId);
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(userDTO);
//    }
}
