package com.restaurant.restaurantorderingapp.controllers.userControllers;

import com.restaurant.restaurantorderingapp.dto.usersDto.CreateUserDTO;
import com.restaurant.restaurantorderingapp.dto.usersDto.UpdateUserDTO;
import com.restaurant.restaurantorderingapp.dto.usersDto.UserDTO;
import com.restaurant.restaurantorderingapp.services.userServices.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final String entityName = "User";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // curl -i -s http://localhost:8080/api/users | sed -e 's/{/\n&/g'
    //Method should not be accessible to any users,
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String userId) {
        UserDTO userDTO = userService.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }

    // curl -i -X POST -H "Content-Type: application/json" -d '{"userName": "XXL"}' http://localhost:8080/api/users
    @PostMapping
    public ResponseEntity<String> createUser(
            @RequestBody @Valid CreateUserDTO CreateUserDTO) {
        userService.createUser(CreateUserDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entityName + " created successfully.");
    }

    // curl -i -X DELETE http://localhost:8080/api/users/7
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityName + " deleted successfully.");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable String userId, @RequestBody @Valid UpdateUserDTO updateUserDTO) {
        UserDTO userDTO = userService.updateUser(userId, updateUserDTO);
        return ResponseEntity.ok(userDTO);
    }
}
