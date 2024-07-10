package com.restaurant.restaurantorderingapp.dto.orderSystemDto.userOrdersDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateUserOrderDTO(

        @NotNull
        @NotBlank
        String userId,

        @NotNull
        @NotBlank
        Long userAddressId,

        @NotNull
        @NotBlank
        BigDecimal subtotal,

        @NotNull
        @NotBlank
        BigDecimal totalSalesTax,

        @NotNull
        @NotBlank
        BigDecimal totalOrderCost,

        String orderNotes
) {}
