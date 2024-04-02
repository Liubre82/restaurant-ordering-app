package com.restaurant.restaurantorderingapp.repositories.foodRepositories;

import com.restaurant.restaurantorderingapp.models.food.FoodSize;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FoodSizeRepository extends CrudRepository<FoodSize, Long> {

    boolean existsByFoodSizeName(String foodSizeName);

    @Query("SELECT fs FROM FoodSize fs WHERE LOWER(fs.foodSizeName) LIKE LOWER(:searchInput)")
    Iterable<FoodSize> findByFoodSizeNameLike(String searchInput);
}
