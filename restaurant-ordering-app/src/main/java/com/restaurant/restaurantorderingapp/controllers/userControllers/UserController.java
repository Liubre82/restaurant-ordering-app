package com.restaurant.restaurantorderingapp.controllers.userControllers;

import com.restaurant.restaurantorderingapp.services.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
