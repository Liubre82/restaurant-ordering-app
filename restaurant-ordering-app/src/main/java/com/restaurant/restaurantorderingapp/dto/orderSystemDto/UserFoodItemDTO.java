package com.restaurant.restaurantorderingapp.dto.orderSystemDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;

@JsonPropertyOrder({"userFoodId", "quantityOrdered", "foodItemVariationDTO"})
public record UserFoodItemDTO(
        Long userFoodId,

        @NotNull
        @JsonProperty("foodItemVariation")
        FoodItemVariationDTO foodItemVariationDTO,

        Long quantityOrdered
) {
}
