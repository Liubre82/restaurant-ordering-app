package com.restaurant.restaurantorderingapp.repositories.userRepositories;

import com.restaurant.restaurantorderingapp.models.user.UserRestaurantReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRestaurantReviewRepository extends CrudRepository<UserRestaurantReview, Long> {

    /**
     * Checks the existence of a user has written a review.
     *
     * @param userId is the foreign key of a userId associated with a review.
     * @return a boolean value if the userId is found in the context.
     */
    @Query("SELECT COUNT(urr) > 0 FROM UserRestaurantReview urr WHERE urr.user.userId = :userId")
    boolean existsByUser(String userId);

}
