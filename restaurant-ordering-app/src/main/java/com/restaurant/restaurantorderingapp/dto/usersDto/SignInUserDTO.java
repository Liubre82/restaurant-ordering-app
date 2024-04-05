package com.restaurant.restaurantorderingapp.dto.usersDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignInUserDTO (

        @NotNull
        @NotBlank
        String username,

        @NotNull
        @NotBlank
        String password,

        @NotNull
        @NotBlank
        String userRoleName
){}
