package com.restaurant.restaurantorderingapp.models.user;

import jakarta.persistence.*;

@Entity
@Table(name = "user_food_items")
public class UserFoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userFoodId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    private String foodItemId;

    private Long userFoodItemQuantity;

    public Long getUserFoodId() {
        return userFoodId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(String foodItemId) {
        this.foodItemId = foodItemId;
    }

    public Long getUserFoodItemQuantity() {
        return userFoodItemQuantity;
    }

    public void setUserFoodItemQuantity(Long userFoodItemQuantity) {
        this.userFoodItemQuantity = userFoodItemQuantity;
    }
}
