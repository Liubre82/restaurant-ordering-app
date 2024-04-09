package com.restaurant.restaurantorderingapp.dto.usersDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(

        @NotNull
        @NotBlank
        String userRoleName,

        @NotNull
        @NotBlank
        String username,

        @NotNull
        @NotBlank
        String userEmail,

        @NotNull
        @NotBlank
        String password
) {}