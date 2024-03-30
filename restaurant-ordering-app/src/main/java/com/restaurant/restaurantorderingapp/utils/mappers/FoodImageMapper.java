package com.restaurant.restaurantorderingapp.utils.mappers;

import com.restaurant.restaurantorderingapp.dto.foodImagesDto.CreateFoodImageDTO;
import com.restaurant.restaurantorderingapp.dto.foodImagesDto.FoodImageDTO;
import com.restaurant.restaurantorderingapp.models.food.FoodImage;
import com.restaurant.restaurantorderingapp.models.food.FoodItem;

public class FoodImageMapper {

    private FoodImageMapper() {}

    public static FoodImage fromDTOToEntity(CreateFoodImageDTO createFoodImageDTO, FoodItem foodItem) {
        FoodImage foodImage = new FoodImage();
        foodImage.setFoodItem(foodItem);
        foodImage.setImageUrl(createFoodImageDTO.imageUrl());
        return foodImage;
    }

    public static FoodImageDTO fromEntityToDTO(FoodImage foodImage) {
        return new FoodImageDTO(
                foodImage.getFoodImageId(),
                foodImage.getFoodItem().getFoodItemId(),
                foodImage.getImageUrl()
        );
    }
}
