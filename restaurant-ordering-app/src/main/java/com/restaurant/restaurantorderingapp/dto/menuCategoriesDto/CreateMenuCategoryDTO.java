package com.restaurant.restaurantorderingapp.dto.menuCategoriesDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CreateMenuCategoryDTO(@NotBlank @NotNull String menuCategoryName) {

}
