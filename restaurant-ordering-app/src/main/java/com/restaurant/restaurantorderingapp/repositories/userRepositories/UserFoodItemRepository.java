package com.restaurant.restaurantorderingapp.repositories.userRepositories;

import com.restaurant.restaurantorderingapp.models.user.UserFoodItem;
import org.springframework.data.repository.CrudRepository;

public interface UserFoodItemRepository extends CrudRepository<UserFoodItem, Long> {


}
