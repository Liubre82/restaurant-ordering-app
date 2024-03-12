package com.restaurant.restaurantorderingapp.repositories;

import com.restaurant.restaurantorderingapp.models.food.MenuCategory;
import org.springframework.data.repository.CrudRepository;

public interface MenuCategoryRepository extends CrudRepository<MenuCategory, Long> {
}
