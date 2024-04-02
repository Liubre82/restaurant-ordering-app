package com.restaurant.restaurantorderingapp.dto.userAddressesDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UserAddressDTO (
        @NotNull
        @Positive
        Long userAddressId,

        @NotNull
        @NotBlank
        String userId,

        @NotNull
        @NotBlank
        String addressName,

        @NotNull
        @NotBlank
        String city,

        @NotNull
        @NotBlank
        String state,

        @NotNull
        @NotBlank
        String zipCode
){}
