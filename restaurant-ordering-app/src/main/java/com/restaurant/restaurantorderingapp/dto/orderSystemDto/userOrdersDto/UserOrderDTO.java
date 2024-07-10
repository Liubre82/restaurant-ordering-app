package com.restaurant.restaurantorderingapp.dto.orderSystemDto.userOrdersDto;

import com.restaurant.restaurantorderingapp.dto.userAddressesDto.UserAddressDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UserOrderDTO(

        @NotNull
        @NotBlank
        String userOrderId,

        //userId negated as it is not needed currently.

        @NotNull
        @NotBlank
        UserAddressDTO userAddressDTO,

        @NotNull
        @NotBlank
        String orderCreatedAt,

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
) {
}
