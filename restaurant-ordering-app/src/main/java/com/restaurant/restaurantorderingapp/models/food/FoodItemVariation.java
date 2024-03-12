package com.restaurant.restaurantorderingapp.models.food;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "food_item_variations")
public class FoodItemVariation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodItemVariationId;

    @ManyToOne
    @JoinColumn(name = "food_item_id")
    private FoodItem foodItem;

    private Long foodSizeId;

    private BigDecimal foodPrice;

    public Long getFoodItemVariationId() {
        return foodItemVariationId;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public Long getFoodSizeId() {
        return foodSizeId;
    }

    public void setFoodSizeId(Long foodSizeId) {
        this.foodSizeId = foodSizeId;
    }

    public BigDecimal getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigDecimal foodPrice) {
        this.foodPrice = foodPrice;
    }
}
