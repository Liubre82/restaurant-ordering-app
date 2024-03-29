package com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto;

import com.restaurant.restaurantorderingapp.dto.foodItemsDto.FoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.foodSizesDto.FoodSizeDTO;

import java.math.BigDecimal;

public record FoodItemVariationDTO(

        Long foodItemVariationId,

        FoodItemDTO foodItem,

        FoodSizeDTO foodSize,

        BigDecimal foodPrice
) {}
