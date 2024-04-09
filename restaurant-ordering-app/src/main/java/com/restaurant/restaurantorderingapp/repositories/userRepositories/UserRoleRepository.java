package com.restaurant.restaurantorderingapp.repositories.userRepositories;

import com.restaurant.restaurantorderingapp.models.user.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRoleRepository extends CrudRepository<UserRole, String> {

    @Query("SELECT ur FROM UserRole ur WHERE ur.userRoleName = :userRoleName")
    Optional<UserRole> findByUserRoleName(String userRoleName);
}
