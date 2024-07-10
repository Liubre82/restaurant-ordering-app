package com.restaurant.restaurantorderingapp.repositories.userRepositories;

import com.restaurant.restaurantorderingapp.models.user.UserOrder;
import org.springframework.data.repository.CrudRepository;

public interface UserOrderRepository extends CrudRepository<UserOrder, String> {

//    @Query("SELECT uo FROM UserOrder uo WHERE uo.userId = :userId")
//    Iterable<UserOrder> findAllUserOrdersByUserId(String userId);

}
