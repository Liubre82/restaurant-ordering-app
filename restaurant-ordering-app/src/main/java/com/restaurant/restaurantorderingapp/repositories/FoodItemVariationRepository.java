package com.restaurant.restaurantorderingapp.repositories;

import com.restaurant.restaurantorderingapp.models.food.FoodItemVariation;
import org.springframework.data.repository.CrudRepository;

public interface FoodItemVariationRepository extends CrudRepository<FoodItemVariation, Long> {

}
