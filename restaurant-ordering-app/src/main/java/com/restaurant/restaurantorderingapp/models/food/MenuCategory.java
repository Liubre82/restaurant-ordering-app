package com.restaurant.restaurantorderingapp.models.food;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "menu_categories")
public class MenuCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_category_id")
    private Long menuCategoryId;

    @NotBlank
    @NotNull
    @Column(name = "menu_category_name")
    private String menuCategoryName;

    @OneToMany(mappedBy = "menuCategory", cascade = CascadeType.REMOVE)
    private List<FoodItem> foodItems;

    public Long getMenuCategoryId() {
        return menuCategoryId;
    }

    public String getMenuCategoryName() {
        return menuCategoryName;
    }

    public void setMenuCategoryName(String menuCategoryName) {
        this.menuCategoryName = menuCategoryName;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }
}
