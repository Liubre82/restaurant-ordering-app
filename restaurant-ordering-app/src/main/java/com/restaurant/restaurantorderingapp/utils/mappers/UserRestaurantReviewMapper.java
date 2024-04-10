package com.restaurant.restaurantorderingapp.utils.mappers;

import com.restaurant.restaurantorderingapp.dto.userRestaurantReviewsDto.CreateUserRestaurantReviewDTO;
import com.restaurant.restaurantorderingapp.dto.userRestaurantReviewsDto.UserRestaurantReviewDTO;
import com.restaurant.restaurantorderingapp.models.user.User;
import com.restaurant.restaurantorderingapp.models.user.UserRestaurantReview;

import java.time.format.DateTimeFormatter;

public class UserRestaurantReviewMapper {

    private UserRestaurantReviewMapper() {}

    public static UserRestaurantReviewDTO fromEntityToDTO(UserRestaurantReview userRestaurantReview) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm a");
        String formattedDateTime = userRestaurantReview.getCreatedAt().format(formatter);
        return new UserRestaurantReviewDTO(
                userRestaurantReview.getUserRestaurantReviewId(),
                userRestaurantReview.getUser().getUserId(),
                userRestaurantReview.getUserRestaurantReviewTitle(),
                userRestaurantReview.getUserRestaurantRating(),
                userRestaurantReview.getUserRestaurantReviewDescription(),
                formattedDateTime
        );
    }

    public static UserRestaurantReview fromDTOToEntity(CreateUserRestaurantReviewDTO createUserRestaurantReviewDTO, User user) {
        UserRestaurantReview userRestaurantReview = new UserRestaurantReview();
        userRestaurantReview.setUser(user);
        userRestaurantReview.setUserRestaurantReviewTitle(createUserRestaurantReviewDTO.userRestaurantReviewTitle());
        userRestaurantReview.setUserRestaurantRating(createUserRestaurantReviewDTO.userRestaurantRating());
        userRestaurantReview.setUserRestaurantReviewDescription(createUserRestaurantReviewDTO.userRestaurantReviewDescription());
        return userRestaurantReview;
    }

}
