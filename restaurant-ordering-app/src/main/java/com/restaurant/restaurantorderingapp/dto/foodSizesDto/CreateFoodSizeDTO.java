package com.restaurant.restaurantorderingapp.dto.foodSizesDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateFoodSizeDTO(
        @NotNull
        @NotBlank
        String foodSizeName
) {}
