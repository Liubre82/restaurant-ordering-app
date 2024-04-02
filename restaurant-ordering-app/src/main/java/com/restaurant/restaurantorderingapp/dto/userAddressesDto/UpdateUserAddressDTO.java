package com.restaurant.restaurantorderingapp.dto.userAddressesDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserAddressDTO(

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
) {}
