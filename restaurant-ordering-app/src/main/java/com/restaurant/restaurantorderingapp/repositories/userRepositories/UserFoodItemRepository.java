package com.restaurant.restaurantorderingapp.repositories.userRepositories;

import com.restaurant.restaurantorderingapp.models.user.UserFoodItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserFoodItemRepository extends CrudRepository<UserFoodItem, Long> {


    @Query("SELECT ufi FROM UserFoodItem ufi WHERE ufi.userOrder.userOrderId = :userOrderId")
    Iterable<UserFoodItem> getAllUserFoodItemsByUserOrderId(String userOrderId);
}
