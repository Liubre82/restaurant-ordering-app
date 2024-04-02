package com.restaurant.restaurantorderingapp.dto.foodImagesDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateFoodImageDTO (

        @NotNull
        @NotBlank
        @JsonProperty("foodItem")
        String foodItemId,

        @NotNull
        @NotBlank
        String imageUrl
){}
