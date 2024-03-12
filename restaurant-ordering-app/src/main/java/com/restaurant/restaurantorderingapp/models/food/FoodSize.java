package com.restaurant.restaurantorderingapp.models.food;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "food_sizes")
public class FoodSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodSizeId;

    private String foodSizeName;

    public Long getFoodSizeId() {
        return foodSizeId;
    }

    public String getFoodSizeName() {
        return foodSizeName;
    }

    public void setFoodSizeName(String foodSizeName) {
        this.foodSizeName = foodSizeName;
    }
}
