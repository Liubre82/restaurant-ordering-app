package com.restaurant.restaurantorderingapp.dto.menuCategoriesDto;


import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

public record MenuCategoryDTO(
        @NotNull
        @NotBlank
        Long menuCategoryId,
        @NotNull
        @NotBlank
        String menuCategoryName
) {}
