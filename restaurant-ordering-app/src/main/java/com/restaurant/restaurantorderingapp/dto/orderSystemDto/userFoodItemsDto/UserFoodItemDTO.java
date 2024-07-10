package com.restaurant.restaurantorderingapp.dto.orderSystemDto.userFoodItemsDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.restaurant.restaurantorderingapp.dto.orderSystemDto.FoodItemVariationDTO;
import jakarta.validation.constraints.NotNull;

public record UserFoodItemDTO (
        Long userFoodId,

        @NotNull
        @JsonProperty("foodItemVariation")
        FoodItemVariationDTO foodItemVariationDTO,

        Long quantityOrdered
){
}
