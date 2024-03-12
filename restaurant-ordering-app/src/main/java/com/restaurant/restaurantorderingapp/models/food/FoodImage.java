package com.restaurant.restaurantorderingapp.models.food;

import jakarta.persistence.*;

@Entity
@Table(name = "food_images")
public class FoodImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodImageId;

    @ManyToOne(targetEntity = FoodItem.class)
    @JoinColumn(name = "food_item_id")
    private FoodItem foodItem;

    private String imageUrl;

    public Long getFoodImageId() {
        return foodImageId;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
