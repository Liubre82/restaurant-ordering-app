package com.restaurant.restaurantorderingapp.dto.usersDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(

        @NotNull
        @NotBlank
        String userRoleId,

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