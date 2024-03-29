package com.restaurant.restaurantorderingapp.utils.mappers;

import com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto.CreateFoodItemVariationDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto.FoodItemVariationDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemsDto.FoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.foodSizesDto.FoodSizeDTO;
import com.restaurant.restaurantorderingapp.models.food.FoodItem;
import com.restaurant.restaurantorderingapp.models.food.FoodItemVariation;
import com.restaurant.restaurantorderingapp.models.food.FoodSize;

public class FoodItemVariationMapper {

    private FoodItemVariationMapper() {}

    public static FoodItemVariation fromDTOToEntity(
            CreateFoodItemVariationDTO createFoodItemVariationDTO,
            FoodItem foodItem,
            FoodSize foodSize )
    {
        FoodItemVariation foodItemVariation = new FoodItemVariation();
        foodItemVariation.setFoodItem(foodItem);
        foodItemVariation.setFoodSize(foodSize);
        foodItemVariation.setFoodPrice(createFoodItemVariationDTO.foodPrice());
        return foodItemVariation;
    }

    public static FoodItemVariationDTO fromEntityToDTO(FoodItemVariation foodItemVariation) {
        FoodItemDTO foodItemDTO = FoodItemMapper.fromEntityToDTO(foodItemVariation.getFoodItem(), foodItemVariation.getFoodItem().getMenuCategory());
        FoodSizeDTO foodSizeDTO = FoodSizeMapper.fromEntityToDTO(foodItemVariation.getFoodSize());
        return new FoodItemVariationDTO(
                foodItemVariation.getFoodItemVariationId(),
                foodItemDTO,
                foodSizeDTO,
                foodItemVariation.getFoodPrice()
        );
    }

}
