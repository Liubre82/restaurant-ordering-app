package com.restaurant.restaurantorderingapp.dto.userRestaurantReviewsDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UserRestaurantReviewDTO(

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
