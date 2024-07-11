package com.restaurant.restaurantorderingapp.utils.mappers;

import com.restaurant.restaurantorderingapp.dto.orderSystemDto.CreateUserFoodOrderDTO;
import com.restaurant.restaurantorderingapp.dto.orderSystemDto.FoodItemVariationDTO;
import com.restaurant.restaurantorderingapp.dto.orderSystemDto.UserFoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.orderSystemDto.UserFoodOrderDTO;
import com.restaurant.restaurantorderingapp.dto.orderSystemDto.userOrdersDto.UserOrderDTO;
import com.restaurant.restaurantorderingapp.dto.userAddressesDto.UserAddressDTO;
import com.restaurant.restaurantorderingapp.models.food.FoodItemVariation;
import com.restaurant.restaurantorderingapp.models.user.User;
import com.restaurant.restaurantorderingapp.models.user.UserAddress;
import com.restaurant.restaurantorderingapp.models.user.UserFoodItem;
import com.restaurant.restaurantorderingapp.models.user.UserOrder;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderSystemMapper {

    private OrderSystemMapper() {};

    public static UserFoodOrderDTO fromEntityToDTO(UserOrder userOrder,
                                                   UserAddressDTO userAddressDTO,
                                                   List<UserFoodItemDTO> foodOrders) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String formattedDateTime = userOrder.getOrderCreatedAt().format(formatter);
        return new UserFoodOrderDTO(
                userOrder.getUserOrderId(),
                userAddressDTO,
                userOrder.getSubtotal(),
                userOrder.getTotalSalesTaxCost(),
                userOrder.getTotalOrderCost(),
                userOrder.getOrderNotes(),
                formattedDateTime,
                foodOrders
        );
    }

    public static com.restaurant.restaurantorderingapp.dto.orderSystemDto.UserFoodItemDTO
    fromEntityToDTO(UserFoodItem userFoodItem, FoodItemVariationDTO foodItemVariationDTO) {
        return new com.restaurant.restaurantorderingapp.dto.orderSystemDto.UserFoodItemDTO(
                userFoodItem.getUserFoodId(),
                foodItemVariationDTO,
                userFoodItem.getUserFoodItemQuantity()
        );
    }

    public static FoodItemVariationDTO fromEntityToDTO(FoodItemVariation foodItemVariation) {
        return new FoodItemVariationDTO(
                foodItemVariation.getFoodItemVariationId(),
                foodItemVariation.getFoodItem().getFoodItemName(),
                foodItemVariation.getFoodItem().getFoodImages().get(0).getImageUrl(),
                foodItemVariation.getFoodSize().getFoodSizeName(),
                foodItemVariation.getFoodPrice()
        );
    }

    public static UserOrderDTO fromEntityToDTO(UserOrder userOrder, UserAddressDTO userAddressDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy"); //"MM-dd-yyyy HH:mm a"
        String formattedDateTime = userOrder.getOrderCreatedAt().format(formatter);
        return new UserOrderDTO(
                userOrder.getUserOrderId(),
                userAddressDTO,
                formattedDateTime,
                userOrder.getSubtotal(),
                userOrder.getTotalSalesTaxCost(),
                userOrder.getTotalOrderCost(),
                userOrder.getOrderNotes()
        );
    }

    public static UserOrder fromDTOToEntity(CreateUserFoodOrderDTO createUserFoodOrderDTO,
                                            User user,
                                            UserAddress userAddress) {
        UserOrder userOrder = new UserOrder();
        userOrder.setUser(user);
        userOrder.setUserAddress(userAddress);
        userOrder.setSubtotal(createUserFoodOrderDTO.subtotal());
        userOrder.setTotalSalesTaxCost(createUserFoodOrderDTO.totalSalesTax());
        userOrder.setTotalOrderCost(createUserFoodOrderDTO.totalOrderCost());
        userOrder.setOrderNotes(createUserFoodOrderDTO.orderNotes());
        return userOrder;
    }
}
