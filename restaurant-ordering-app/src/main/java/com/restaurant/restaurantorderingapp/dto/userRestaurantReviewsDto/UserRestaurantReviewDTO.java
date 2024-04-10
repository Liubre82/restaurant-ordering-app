package com.restaurant.restaurantorderingapp.dto.userRestaurantReviewsDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UserRestaurantReviewDTO(


        @NotNull
        @Positive
        Long userRestaurantReviewId,

        @NotNull
        @NotBlank
        String userId,

        @NotNull
        @NotBlank
        String userRestaurantReviewTitle,

        @NotNull
        @NotBlank
        BigDecimal userRestaurantRating,

        @NotNull
        @NotBlank
        String userRestaurantReviewDescription,

        @NotNull
        @NotBlank
        String createdAt
) {}
