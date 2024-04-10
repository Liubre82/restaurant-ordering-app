package com.restaurant.restaurantorderingapp.controllers.userControllers;

import com.restaurant.restaurantorderingapp.dto.userAddressesDto.UserAddressDTO;
import com.restaurant.restaurantorderingapp.dto.userRestaurantReviewsDto.UserRestaurantReviewDTO;
import com.restaurant.restaurantorderingapp.dto.usersDto.FullUserDTO;
import com.restaurant.restaurantorderingapp.dto.usersDto.UpdateUserDTO;
import com.restaurant.restaurantorderingapp.dto.usersDto.UpdateUserPasswordDTO;
import com.restaurant.restaurantorderingapp.dto.usersDto.UserDTO;
import com.restaurant.restaurantorderingapp.services.userServices.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
* The CREATE user route is in the AuthController rather than the user controller
* as the signup route.
*/
@RestController
public class UserController {
    
    private final String entityName = "User";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // curl -i -s http://localhost:8080/api/users | sed -e 's/{/\n&/g'
    //Route only for development to see all user fields including password.
    @GetMapping("/admin/users/all")
    public ResponseEntity<List<FullUserDTO>> getUsersFull() {
        List<FullUserDTO> users = userService.getAllUsersInfo();
        return ResponseEntity.ok(users);
    }

    // curl -i -s http://localhost:8080/api/users | sed -e 's/{/\n&/g'
    @GetMapping("/admin/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/authUsers/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String userId) {
        UserDTO userDTO = userService.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/authUsers/users/{userId}/userAddresses")
    public ResponseEntity<List<UserAddressDTO>> getUserAddressesByUserId(@PathVariable String userId) {
        List<UserAddressDTO> userAddressDTOList = userService.getAllAddressesByUserId(userId);
        return ResponseEntity.ok(userAddressDTOList);
    }

    @GetMapping("/authUsers/users/{userId}/userRestaurantReviews")
    public ResponseEntity<UserRestaurantReviewDTO> getUserReviewById(@PathVariable String userId) {
        UserRestaurantReviewDTO userRestaurantReviewDTO = userService.getReviewByUserId(userId);
        return ResponseEntity.ok(userRestaurantReviewDTO);
    }

    // curl -i -X DELETE http://localhost:8080/api/users/7
    @DeleteMapping("/authUsers/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityName + " deleted successfully.");
    }

    @PutMapping("/authUsers/users/{userId}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable String userId, @RequestBody @Valid UpdateUserDTO updateUserDTO) {
        UserDTO userDTO = userService.updateUser(userId, updateUserDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/autUsers/users/{userId}/password")
    public ResponseEntity<UserDTO> updateUserPassword(
            @PathVariable String userId, @RequestBody @Valid UpdateUserPasswordDTO updateUserPasswordDTO) {
        UserDTO userDTO = userService.updateUserPassword(userId, updateUserPasswordDTO);
        return ResponseEntity.ok(userDTO);
    }
}
