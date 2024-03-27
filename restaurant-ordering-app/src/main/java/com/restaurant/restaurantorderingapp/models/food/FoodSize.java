package com.restaurant.restaurantorderingapp.models.food;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "food_sizes")
public class FoodSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_size_id")
    private Long foodSizeId;

    @NotBlank
    @NotNull
    @Column(name = "food_size_name")
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
