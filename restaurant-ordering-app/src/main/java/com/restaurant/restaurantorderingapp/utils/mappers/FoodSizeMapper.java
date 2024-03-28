package com.restaurant.restaurantorderingapp.utils.mappers;

import com.restaurant.restaurantorderingapp.dto.foodSizesDto.CreateFoodSizeDTO;
import com.restaurant.restaurantorderingapp.dto.foodSizesDto.FoodSizeDTO;
import com.restaurant.restaurantorderingapp.models.food.FoodSize;

public class FoodSizeMapper {

    private FoodSizeMapper() {}

    public static FoodSizeDTO fromEntityToDTO(FoodSize foodSize) {
        return new FoodSizeDTO(
                foodSize.getFoodSizeId(),
                foodSize.getFoodSizeName()
        );
    }

    public static FoodSize fromDTOToEntity(CreateFoodSizeDTO createFoodSizeDTO) {
        FoodSize foodSize = new FoodSize();
        foodSize.setFoodSizeName(createFoodSizeDTO.foodSizeName());
        return foodSize;
    }
}
