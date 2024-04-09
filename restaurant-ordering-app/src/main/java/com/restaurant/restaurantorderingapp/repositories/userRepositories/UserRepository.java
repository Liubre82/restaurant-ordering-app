package com.restaurant.restaurantorderingapp.repositories.userRepositories;

import com.restaurant.restaurantorderingapp.models.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    /**
     * Checks the existence of a userEmail.
     *
     * @param userEmail is the email to check if another user has it registered.
     * @return a boolean value if the userEmail is found in the context.
     */
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.userEmail = :userEmail")
    boolean existsByUserEmail(String userEmail);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.username = :username")
    boolean existsByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(String username);
}
