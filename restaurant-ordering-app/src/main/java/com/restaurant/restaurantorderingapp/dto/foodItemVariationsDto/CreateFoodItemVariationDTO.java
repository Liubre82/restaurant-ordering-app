package com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

public record CreateFoodItemVariationDTO(

        @NotNull
        @NotBlank
        String foodItemId,

        @NotNull
        @Positive
        Long foodSizeId,

        @NotNull
        @Positive
        BigDecimal foodPrice
) {}
