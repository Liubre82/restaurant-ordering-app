package com.restaurant.restaurantorderingapp.repositories.foodRepositories;

import com.restaurant.restaurantorderingapp.models.food.FoodImage;
import org.springframework.data.repository.CrudRepository;

public interface FoodImageRepository extends CrudRepository<FoodImage, Long> {
}
