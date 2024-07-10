package com.restaurant.restaurantorderingapp.utils.mappers;

import com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto.FoodItemVariationDTO;
import com.restaurant.restaurantorderingapp.dto.orderSystemDto.userFoodItemsDto.UserFoodItemDTO;
import com.restaurant.restaurantorderingapp.models.food.FoodItemVariation;
import com.restaurant.restaurantorderingapp.models.user.UserFoodItem;
import com.restaurant.restaurantorderingapp.models.user.UserOrder;

import java.math.BigDecimal;

public class UserFoodItemMapper {

    private UserFoodItemMapper() {}

    public static UserFoodItemDTO fromEntityToDTO(UserFoodItem userFoodItem,
                                                  FoodItemVariationDTO foodItemVariationDTO) {
        return new UserFoodItemDTO(
                userFoodItem.getUserFoodId(),
                userFoodItem.getUserOrder().getUserOrderId(),
                foodItemVariationDTO,
                userFoodItem.getFoodItemsCost(),
                userFoodItem.getUserFoodItemQuantity()
        );
    }

    public static UserFoodItem fromDTOToEntity(UserOrder userOrder,
                                               FoodItemVariation foodItemVariation,
                                               BigDecimal foodItemsCost,
                                               Long userFoodItemQuantity
                                               ) {
        UserFoodItem userFoodItem = new UserFoodItem();
        userFoodItem.setUserOrder(userOrder);
        userFoodItem.setFoodItemVariation(foodItemVariation);
        userFoodItem.setFoodItemsCost(foodItemsCost);
        userFoodItem.setUserFoodItemQuantity(userFoodItemQuantity);
        return userFoodItem;
    }

}
