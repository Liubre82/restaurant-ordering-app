package com.restaurant.restaurantorderingapp.dto.orderSystemDto;

import java.math.BigDecimal;

public record FoodItemVariationDTO(

        Long foodItemVariationId,

        String foodItemName,
        //Thumbnail image for the food Item.
        String imageUrl,

        String foodSize,
        BigDecimal foodItemCost
) {}
