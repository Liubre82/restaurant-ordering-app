package com.restaurant.restaurantorderingapp.services.orderSystemServices;

import com.restaurant.restaurantorderingapp.dto.orderSystemDto.CreateUserFoodOrderDTO;
import com.restaurant.restaurantorderingapp.dto.orderSystemDto.UserFoodOrderDTO;
import com.restaurant.restaurantorderingapp.models.food.FoodItemVariation;
import com.restaurant.restaurantorderingapp.models.user.User;
import com.restaurant.restaurantorderingapp.models.user.UserAddress;
import com.restaurant.restaurantorderingapp.models.user.UserFoodItem;
import com.restaurant.restaurantorderingapp.models.user.UserOrder;
import com.restaurant.restaurantorderingapp.repositories.userRepositories.UserFoodItemRepository;
import com.restaurant.restaurantorderingapp.repositories.userRepositories.UserOrderRepository;
import com.restaurant.restaurantorderingapp.services.foodServices.FoodItemVariationService;
import com.restaurant.restaurantorderingapp.services.userServices.UserAddressService;
import com.restaurant.restaurantorderingapp.services.userServices.UserService;
import com.restaurant.restaurantorderingapp.utils.mappers.FoodItemVariationMapper;
import com.restaurant.restaurantorderingapp.utils.mappers.OrderSystemMapper;
import com.restaurant.restaurantorderingapp.utils.mappers.UserFoodItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderSystemService {

    private final BigDecimal salesTax = BigDecimal.valueOf(0.06625);

    private final UserFoodItemRepository userFoodItemRepository;

    private final UserOrderRepository userOrderRepository;

    private final FoodItemVariationService foodItemVariationService;

    private final UserService userService;

    private final UserAddressService userAddressService;

    @Autowired
    public OrderSystemService(UserFoodItemRepository userFoodItemRepository,
                              UserOrderRepository userOrderRepository,
                              FoodItemVariationService foodItemVariationService,
                              UserService userService,
                              UserAddressService userAddressService
                              ) {
        this.userFoodItemRepository = userFoodItemRepository;
        this.userOrderRepository = userOrderRepository;
        this.foodItemVariationService = foodItemVariationService;
        this.userService = userService;
        this.userAddressService = userAddressService;
    }

//    public UserFoodItem findUserFoodItemById(Long userFoodItemId) {
//        return userFoodItemRepository.findById(userFoodItemId)
//                .orElseThrow(() -> new NotFoundException("Food Item", userFoodItemId));
//    }
//
//
//    public UserFoodItemDTO getUserFoodItemById(Long userFoodItemId) {
//        UserFoodItem userFoodItem = findUserFoodItemById(userFoodItemId);
//        FoodItemVariation foodItemVariation =
//                foodItemVariationService.findFoodItemVariationById(
//                        userFoodItem.getFoodItemVariation().getFoodItemVariationId()
//                );
//        return UserFoodItemMapper.fromEntityToDTO(
//                userFoodItem, FoodItemVariationMapper.fromEntityToDTO(foodItemVariation)
//        );
//    }

    public UserFoodOrderDTO getAllUserFoodOrder(String userId) {
        Iterable<UserOrder> userOrder = userOrderRepository.findAllUserOrdersByUserId(userId);

        while(userOrder.iterator().hasNext()) {

        }


    }


    /**
     * This method creates entities in two tables: UserOrder and UserFoodItem.
     * It is responsible for saving the user's order history every time a user places an order.
     *
     * @param createUserFoodOrderDTO the form data containing details to create a UserOrder and associated UserFoodItem entities.
     */
    public void createUserFoodOrder(CreateUserFoodOrderDTO createUserFoodOrderDTO, String userId) {

        /*
        First create/save UserOrder entity to the db, we need the UserOrder Id in order to create
        the UserFoodItem entities.
        */
        // Retrieve User and UserAddress entities using provided IDs
        User user = userService.findUserById(userId);
        UserAddress userAddress = userAddressService.findUserAddressById(createUserFoodOrderDTO.userAddressId());
        // Create and save UserOrder entity to the database to get its ID
        UserOrder createUserOrder = OrderSystemMapper.fromDTOToEntity(createUserFoodOrderDTO, user, userAddress);
        UserOrder userOrder = userOrderRepository.save(createUserOrder);
        AtomicReference<BigDecimal> subtotal = new AtomicReference<>(BigDecimal.ZERO);
        List<UserFoodItem> userFoodItems = new ArrayList<>();
        /*
        Now we create the UserFoodItem entities. the amount we add will be the size of the HashMap.
        */
        // Key is the foodItemVariationId, value is the quantity of the foodItemVariation the user ordered.
        // Iterate through the productQuantityMap to create UserFoodItem entities
        createUserFoodOrderDTO.productQuantityMap().forEach((key, value) -> {

            FoodItemVariation foodItemVariation = foodItemVariationService.findFoodItemVariationById(key);
            System.out.println(foodItemVariation.getFoodItem().getFoodItemName());
            BigDecimal foodItemVariationPrice = foodItemVariation.getFoodPrice();
            BigDecimal foodItemVariationQuantityOrdered = BigDecimal.valueOf(value);
            BigDecimal cost = foodItemVariationPrice.multiply(foodItemVariationQuantityOrdered);
            BigDecimal roundedCost = cost.setScale(2, RoundingMode.HALF_UP); // 2decimal places &round up when 5.
            // Update total cost accumulator using AtomicReference
            subtotal.updateAndGet(current -> current.add(roundedCost));

            // Create UserFoodItem entity
            UserFoodItem userFoodItem = UserFoodItemMapper.fromDTOToEntity(
                    userOrder,
                    foodItemVariation,
                    roundedCost,
                    value
            );

            // Add the created/persisted UserFoodItem to the List.
            userFoodItems.add(userFoodItemRepository.save(userFoodItem));
        }); // end forEach loop.
        userOrder.setUserFoodItems(userFoodItems);
        //calculate and persist the cost of food, tax, and order cost.
        userOrder.setSubtotal(subtotal.get());
        BigDecimal totalSalesTaxCost = (subtotal.get()).multiply(salesTax);
        userOrder.setTotalSalesTaxCost(totalSalesTaxCost);
        BigDecimal totalOrderCost = (subtotal.get()).add(totalSalesTaxCost);
        userOrder.setTotalOrderCost(totalOrderCost);

        // Save the updated UserOrder entity to the database and adds all the userFoodItem entities.
        userOrderRepository.save(userOrder);
    }


}
