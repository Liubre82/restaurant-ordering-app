package com.restaurant.restaurantorderingapp.repositories.userRepositories;

import com.restaurant.restaurantorderingapp.models.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
