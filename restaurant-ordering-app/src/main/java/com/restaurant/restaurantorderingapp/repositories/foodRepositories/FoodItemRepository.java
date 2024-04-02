package com.restaurant.restaurantorderingapp.repositories.foodRepositories;

import com.restaurant.restaurantorderingapp.models.food.FoodItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FoodItemRepository extends CrudRepository<FoodItem, String> {

    boolean existsByFoodItemName(String foodItemName);

    @Query("SELECT fi FROM FoodItem fi WHERE LOWER(fi.foodItemName) LIKE LOWER(:searchInput)")
    Iterable<FoodItem> findByFoodItemNameLike(String searchInput);
}
