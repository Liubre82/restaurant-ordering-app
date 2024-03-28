package com.restaurant.restaurantorderingapp.dto.foodItemsDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateFoodItemDTO(

        @NotNull
        @NotBlank
        Long menuCategoryId,

        @NotNull
        @NotBlank
        String foodItemName,

        @NotNull
        @NotBlank
        String foodItemDescription
) {}
