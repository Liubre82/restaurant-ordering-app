package com.restaurant.restaurantorderingapp.dto.usersDto;

import com.restaurant.restaurantorderingapp.models.user.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(

        @NotNull
        @NotBlank
        String userId,

        @NotNull
        @NotBlank
        UserRole userRole,

        @NotNull
        @NotBlank
        String username,

        @NotNull
        @NotBlank
        String userEmail
) {}
