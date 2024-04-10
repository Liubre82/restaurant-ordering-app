package com.restaurant.restaurantorderingapp.controllers.userControllers;

import com.restaurant.restaurantorderingapp.dto.userAddressesDto.CreateUserAddressDTO;
import com.restaurant.restaurantorderingapp.dto.userAddressesDto.UpdateUserAddressDTO;
import com.restaurant.restaurantorderingapp.dto.userAddressesDto.UserAddressDTO;
import com.restaurant.restaurantorderingapp.services.userServices.UserAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserAddressController {

    private final String entityName = "User Address";

    private final UserAddressService userAddressService;

    @Autowired
    public UserAddressController(UserAddressService userAddressService) {
        this.userAddressService = userAddressService;
    }

    // curl -i -s http://localhost:8080/api/userAddresses | sed -e 's/{/\n&/g'
    //Method should not be accessible to any users,
    @GetMapping("/admin/userAddresses")
    public ResponseEntity<List<UserAddressDTO>> getUserAddresses() {
        List<UserAddressDTO> userAddresses = userAddressService.getAllUserAddresses();
        return ResponseEntity.ok(userAddresses);
    }

    @GetMapping("/authUsers/userAddresses/{userAddressId}")
    public ResponseEntity<UserAddressDTO> getUserAddress(@PathVariable Long userAddressId) {
        UserAddressDTO userAddressDTO = userAddressService.getUserAddressById(userAddressId);
        return ResponseEntity.ok(userAddressDTO);
    }

    // curl -i -X POST -H "Content-Type: application/json" -d '{"userAddressName": "XXL"}' http://localhost:8080/api/userAddresses
    @PostMapping("/authUsers/userAddresses")
    public ResponseEntity<String> createUserAddress(
            @RequestBody @Valid CreateUserAddressDTO CreateUserAddressDTO) {
        userAddressService.createUserAddress(CreateUserAddressDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entityName + " created successfully.");
    }

    // curl -i -X DELETE http://localhost:8080/api/userAddresses/7
    @DeleteMapping("/authUsers/userAddresses/{userAddressId}")
    public ResponseEntity<String> deleteUserAddress(@PathVariable Long userAddressId) {
        userAddressService.deleteUserAddress(userAddressId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityName + " deleted successfully.");
    }

    @PutMapping("/authUsers/userAddresses/{userAddressId}")
    public ResponseEntity<UserAddressDTO> updateUserAddress(
            @PathVariable Long userAddressId, @RequestBody @Valid UpdateUserAddressDTO updateUserAddressDTO) {
        UserAddressDTO userAddressDTO = userAddressService.updateUserAddress(userAddressId, updateUserAddressDTO);
        return ResponseEntity.ok(userAddressDTO);
    }
}
