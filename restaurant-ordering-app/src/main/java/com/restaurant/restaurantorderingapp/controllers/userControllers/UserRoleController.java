package com.restaurant.restaurantorderingapp.controllers.userControllers;

import com.restaurant.restaurantorderingapp.models.user.UserRole;
import com.restaurant.restaurantorderingapp.repositories.userRepositories.UserRoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserRoleController {

    private final UserRoleRepository userRoleRepository;

    public UserRoleController(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


    @GetMapping("/admin/userRoles")
    public ResponseEntity<Optional<UserRole>> getUserRole(@PathVariable String userRoleName) {
        Optional<UserRole> userRole = userRoleRepository.findByUserRoleName(userRoleName);
        return ResponseEntity.ok(userRole);
    }
}
