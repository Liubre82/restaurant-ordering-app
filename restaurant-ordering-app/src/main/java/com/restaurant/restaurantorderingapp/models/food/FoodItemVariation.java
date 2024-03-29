package com.restaurant.restaurantorderingapp.models.food;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "food_item_variations")
public class FoodItemVariation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodItemVariationId;

    @ManyToOne
    @JoinColumn(name = "food_item_id")
    private FoodItem foodItem;

    @OneToOne
    @JoinColumn(name = "food_size_id")
    private FoodSize foodSize;

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

    public FoodSize getFoodSize() {
        return foodSize;
    }

    public void setFoodSize(FoodSize foodSize) {
        this.foodSize = foodSize;
    }

    public BigDecimal getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigDecimal foodPrice) {
        this.foodPrice = foodPrice;
    }
}
