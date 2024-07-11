package com.restaurant.restaurantorderingapp.dto.orderSystemDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.restaurant.restaurantorderingapp.dto.userAddressesDto.UserAddressDTO;

import java.math.BigDecimal;
import java.util.List;

@JsonPropertyOrder({"userOrderId", "userAddressDTO", "subtotal", "totalSalesTax",
        "totalOrderCost", "orderNotes", "orderCreatedAt", "foods"})
public record UserFoodOrderDTO(

        String userOrderId,

        @JsonProperty("userAddress")
        UserAddressDTO userAddressDTO,

        BigDecimal subtotal,

        BigDecimal totalSalesTax,

        BigDecimal totalOrderCost,

        String orderNotes,

        String orderCreatedAt,

        @JsonProperty("userFoodItemsOrdered")
        List<UserFoodItemDTO> foods
) {}
