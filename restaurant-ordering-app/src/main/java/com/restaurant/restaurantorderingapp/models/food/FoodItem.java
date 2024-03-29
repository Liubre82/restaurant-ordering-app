package com.restaurant.restaurantorderingapp.models.food;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "food_items")
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "food_item_id")
    private String foodItemId;

    @ManyToOne
    @JoinColumn(name = "menu_category_id")
    private MenuCategory menuCategory;

    @Column(name = "food_item_name", unique = true)
    private String foodItemName;

    @Column(name = "food_item_description")
    private String foodItemDescription;

    @OneToMany(mappedBy = "foodItem", cascade = CascadeType.REMOVE)
    private List<FoodItemVariation> foodItemVariations;

    public String getFoodItemId() {
        return foodItemId;
    }

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(MenuCategory menuCategory) {
        this.menuCategory = menuCategory;
    }

    public String getFoodItemName() {
        return foodItemName;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public String getFoodItemDescription() {
        return foodItemDescription;
    }

    public void setFoodItemDescription(String foodItemDescription) {
        this.foodItemDescription = foodItemDescription;
    }

    public List<FoodItemVariation> getFoodItemVariations() {
        return foodItemVariations;
    }

    public void setFoodItemVariations(List<FoodItemVariation> foodItemVariations) {
        this.foodItemVariations = foodItemVariations;
    }
}
