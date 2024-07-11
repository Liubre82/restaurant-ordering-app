package com.restaurant.restaurantorderingapp.dto.userAddressesDto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@JsonPropertyOrder({"userAddressId", "personName", "addressName", "city",
        "state", "zipCode"})
public record UserAddressDTO (
        @NotNull
        @Positive
        Long userAddressId,

        @NotNull
        @NotBlank
        String personName,

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
