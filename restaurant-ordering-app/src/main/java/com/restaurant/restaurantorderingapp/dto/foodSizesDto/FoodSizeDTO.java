package com.restaurant.restaurantorderingapp.dto.foodSizesDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FoodSizeDTO(
        @NotNull
        @NotBlank
        Long foodSizeId,

        @NotNull
        @NotBlank
        String foodSizeName
) {}
