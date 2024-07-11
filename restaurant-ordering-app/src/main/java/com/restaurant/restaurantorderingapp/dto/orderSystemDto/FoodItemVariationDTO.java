package com.restaurant.restaurantorderingapp.dto.orderSystemDto;

import java.math.BigDecimal;

//Special FoodItemVariation DTO only used for User Order Food items.
public record FoodItemVariationDTO(

        Long foodItemVariationId,

        String foodItemName,
        //Thumbnail image for the food Item.
        String imageUrl,

        String foodSize,
        BigDecimal foodItemCost
) {}
