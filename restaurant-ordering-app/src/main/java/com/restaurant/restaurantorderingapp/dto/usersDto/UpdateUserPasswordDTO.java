package com.restaurant.restaurantorderingapp.dto.usersDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserPasswordDTO(

        @NotNull
        @NotBlank
        String userPassword
) {}
