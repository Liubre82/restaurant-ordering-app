package com.restaurant.restaurantorderingapp.dto.usersDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO (

        @NotNull
        @NotBlank
        String username,

        @NotNull
        @NotBlank
        String userEmail
){}
