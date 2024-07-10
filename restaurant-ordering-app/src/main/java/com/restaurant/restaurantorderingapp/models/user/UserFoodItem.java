package com.restaurant.restaurantorderingapp.models.user;

import com.restaurant.restaurantorderingapp.models.food.FoodItemVariation;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "user_food_items")
public class UserFoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userFoodId;

    @ManyToOne(targetEntity = UserOrder.class)
    @JoinColumn(name = "user_order_id")
    private UserOrder userOrder;

    @OneToOne(targetEntity = FoodItemVariation.class)
    @JoinColumn(name = "food_item_variation_id")
    private FoodItemVariation foodItemVariation;

    //cost of the specific food ordered, foodPrice x userFoodItemQuantity. *NOT TOTAL ORDER COST*
    private BigDecimal foodItemsCost;

    private Long userFoodItemQuantity;


    public Long getUserFoodId() {
        return userFoodId;
    }

    public void setUserFoodId(Long userFoodId) {
        this.userFoodId = userFoodId;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    public FoodItemVariation getFoodItemVariation() {
        return foodItemVariation;
    }

    public void setFoodItemVariation(FoodItemVariation foodItemVariation) {
        this.foodItemVariation = foodItemVariation;
    }

    public BigDecimal getFoodItemsCost() {
        return foodItemsCost;
    }

    public void setFoodItemsCost(BigDecimal foodItemsCost) {
        this.foodItemsCost = foodItemsCost;
    }

    public Long getUserFoodItemQuantity() {
        return userFoodItemQuantity;
    }

    public void setUserFoodItemQuantity(Long userFoodItemQuantity) {
        this.userFoodItemQuantity = userFoodItemQuantity;
    }
}
