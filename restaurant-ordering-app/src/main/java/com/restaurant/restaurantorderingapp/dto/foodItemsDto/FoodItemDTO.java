package com.restaurant.restaurantorderingapp.dto.foodItemsDto;

import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.MenuCategoryDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FoodItemDTO (

        @NotNull
        @NotBlank
        String foodItemId,

        @NotNull
        @NotBlank
        MenuCategoryDTO menuCategory,

        @NotNull
        @NotBlank
        String foodItemName,

        @NotNull
        @NotBlank
        String foodItemDescription

) {}
