package com.restaurant.restaurantorderingapp.dto.userRestaurantReviewsDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateUserRestaurantReviewDTO(

        @NotNull
        @NotBlank
        String userId,

        @NotNull
        @NotBlank
        String userRestaurantReviewTitle,

        @NotNull
        @Positive
        BigDecimal userRestaurantRating,

        @NotNull
        @NotBlank
        String userRestaurantReviewDescription

) {}
