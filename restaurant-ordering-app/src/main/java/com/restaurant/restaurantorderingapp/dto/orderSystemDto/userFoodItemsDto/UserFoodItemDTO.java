package com.restaurant.restaurantorderingapp.dto.orderSystemDto.userFoodItemsDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto.FoodItemVariationDTO;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UserFoodItemDTO (
        Long userFoodId,

        String userOrderId,

        @NotNull
        @JsonProperty("foodItemVariation")
        FoodItemVariationDTO foodItemVariationDTO,

        BigDecimal foodItemCost,

        Long quantityOrdered
){
}
