package com.restaurant.restaurantorderingapp.dto.foodImagesDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateFoodImageDTO (

        @NotNull
        @NotBlank
        String foodItemId,

        @NotNull
        @NotBlank
        String imageUrl
){}
