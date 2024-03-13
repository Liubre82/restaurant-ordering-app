package com.restaurant.restaurantorderingapp.models.food;

import jakarta.persistence.*;

@Entity
@Table(name = "menu_categories")
public class MenuCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuCategoryId;

    private String menuCategoryName;

    public Long getMenuCategoryId() {
        return menuCategoryId;
    }

    public String getMenuCategoryName() {
        return menuCategoryName;
    }

    public void setCategoryName(String menuCategoryName) {
        this.menuCategoryName = menuCategoryName;
    }
}
