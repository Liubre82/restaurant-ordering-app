package com.restaurant.restaurantorderingapp.repositories;

import com.restaurant.restaurantorderingapp.models.food.FoodItemVariation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FoodItemVariationRepository extends CrudRepository<FoodItemVariation, Long> {

    /**
     * checks the existence of a foodItemVariation, if it exists, it means a variation with the same
     * foodItemId and foodSizeId cannot be created, because it will be a duplicate entity.
     *
     * @param foodItemId is the primary key and foreign key of FoodItem.
     * @param foodSizeId is the primary key and foreign key of FoodSize.
     * @return a boolean value based on if the variation can be found.
     */
    @Query("SELECT COUNT(fiv) > 0 FROM FoodItemVariation fiv WHERE fiv.foodItem.foodItemId = :foodItemId AND fiv.foodSize.foodSizeId = :foodSizeId")
    boolean checkFoodItemVariationUniqueness(String foodItemId, Long foodSizeId);
}
