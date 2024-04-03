package com.restaurant.restaurantorderingapp.dto.usersDto;

import com.restaurant.restaurantorderingapp.models.user.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FullUserDTO(
        @NotNull
        @NotBlank
        String userId,

        @NotNull
        @NotBlank
        UserRole userRole,

        @NotNull
        @NotBlank
        String userName,

        @NotNull
        @NotBlank
        String userEmail,

        @NotNull
        @NotBlank
        String userPassword
) {}
