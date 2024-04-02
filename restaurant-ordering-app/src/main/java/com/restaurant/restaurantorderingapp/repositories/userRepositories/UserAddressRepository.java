package com.restaurant.restaurantorderingapp.repositories.userRepositories;

import com.restaurant.restaurantorderingapp.models.user.UserAddress;
import org.springframework.data.repository.CrudRepository;

public interface UserAddressRepository extends CrudRepository<UserAddress, Long> {
}
