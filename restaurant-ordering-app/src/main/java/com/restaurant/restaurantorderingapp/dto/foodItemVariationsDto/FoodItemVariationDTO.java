package com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto;

import com.restaurant.restaurantorderingapp.dto.foodItemsDto.FoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.foodSizesDto.FoodSizeDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record FoodItemVariationDTO(

        @NotNull
        @NotBlank
        Long foodItemVariationId,

        @NotNull
        @NotBlank
        FoodItemDTO foodItem,

        @NotNull
        @NotBlank
        FoodSizeDTO foodSize,

        @NotNull
        @NotBlank
        BigDecimal foodPrice
) {}
