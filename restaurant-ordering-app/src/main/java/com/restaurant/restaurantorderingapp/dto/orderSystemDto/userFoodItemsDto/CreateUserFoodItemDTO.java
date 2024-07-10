package com.restaurant.restaurantorderingapp.dto.orderSystemDto.userFoodItemsDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateUserFoodItemDTO(

        @NotNull
        @NotBlank
        String userOrderId,

        @NotNull
        @NotBlank
        String foodItemVariationId,

        @NotNull
        @NotBlank
        BigDecimal foodItemsCost,

        @NotNull
        @NotBlank
        Long userFoodItemQuantity
) {}
