package com.restaurant.restaurantorderingapp.dto.orderSystemDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.HashMap;


//Json payload will create entities for both UserOrder & UserFoodItem.
public record CreateUserFoodOrderDTO(

        @NotNull
        @Min(1)
        Long userAddressId,

        BigDecimal subtotal,


        BigDecimal totalSalesTax,


        BigDecimal totalOrderCost,

        String orderNotes,

        //{foodItemVariationId : foodItemQuantity}
        HashMap<Long, Long> productQuantityMap

){
}
