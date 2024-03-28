package com.restaurant.restaurantorderingapp.dto.foodItemsDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateFoodItemDTO(

        @NotNull
        @Positive
        Long menuCategoryId,

        @NotNull
        @NotBlank
        String foodItemName,

        @NotNull
        @NotBlank
        String foodItemDescription
) {
}
