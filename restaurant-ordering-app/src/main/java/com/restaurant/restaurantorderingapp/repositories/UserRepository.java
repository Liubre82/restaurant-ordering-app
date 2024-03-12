package com.restaurant.restaurantorderingapp.repositories;

import com.restaurant.restaurantorderingapp.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
