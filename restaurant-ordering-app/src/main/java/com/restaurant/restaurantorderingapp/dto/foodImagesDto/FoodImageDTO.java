package com.restaurant.restaurantorderingapp.dto.foodImagesDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FoodImageDTO(

        @NotNull
        @Positive
        Long foodImageId,

        @NotNull
        @NotBlank
        String foodItem,

        @NotNull
        @NotBlank
        String imageUrl
) {}
