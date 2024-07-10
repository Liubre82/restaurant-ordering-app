package com.restaurant.restaurantorderingapp.dto.orderSystemDto;

import com.restaurant.restaurantorderingapp.dto.orderSystemDto.userFoodItemsDto.UserFoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.userAddressesDto.UserAddressDTO;

import java.math.BigDecimal;
import java.util.List;

public record UserFoodOrderDTO(

        String userOrderId,

        UserAddressDTO userAddressDTO,

        List<UserFoodItemDTO>  foodOrders,

        String orderCreatedAt,

        BigDecimal subtotal,

        BigDecimal totalSalesTax,

        BigDecimal totalOrderCost,

        String orderNotes

) {}
