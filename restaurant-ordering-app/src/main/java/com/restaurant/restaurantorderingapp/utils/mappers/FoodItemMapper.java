package com.restaurant.restaurantorderingapp.utils.mappers;

import com.restaurant.restaurantorderingapp.dto.foodItemsDto.CreateFoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemsDto.FoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.MenuCategoryDTO;
import com.restaurant.restaurantorderingapp.models.food.FoodItem;
import com.restaurant.restaurantorderingapp.models.food.MenuCategory;

public class FoodItemMapper {

    private FoodItemMapper() {}

    public static FoodItem fromDTOToEntity(CreateFoodItemDTO createFoodItemDTO, MenuCategory menuCategory) {
        FoodItem foodItem = new FoodItem();
        foodItem.setMenuCategory(menuCategory);
        foodItem.setFoodItemName(createFoodItemDTO.foodItemName());
        foodItem.setFoodItemDescription(createFoodItemDTO.foodItemDescription());
        return foodItem;
    }

    public static FoodItemDTO fromEntityToDTO(FoodItem foodItem, MenuCategory menuCategory) {
        MenuCategoryDTO menuCategoryDTO = MenuCategoryMapper.fromEntityToDTO(menuCategory);
        return new FoodItemDTO(
                foodItem.getFoodItemId(),
                menuCategoryDTO,
                foodItem.getFoodItemName(),
                foodItem.getFoodItemDescription()
        );
    }
}
