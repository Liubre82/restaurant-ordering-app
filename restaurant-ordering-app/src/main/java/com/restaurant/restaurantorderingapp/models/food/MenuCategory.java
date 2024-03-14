package com.restaurant.restaurantorderingapp.models.food;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
