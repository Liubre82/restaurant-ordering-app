package com.restaurant.restaurantorderingapp.utils.mappers;

import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.CreateMenuCategoryDTO;
import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.MenuCategoryDTO;
import com.restaurant.restaurantorderingapp.models.food.MenuCategory;

public class MenuCategoryMapper {

    private MenuCategoryMapper() {}

    //convert MenuCategory entity obj to DTO obj
    public static MenuCategoryDTO fromEntityToDTO(MenuCategory menuCategory) {
        return new MenuCategoryDTO(
                menuCategory.getMenuCategoryId(),
                menuCategory.getMenuCategoryName()
        );
    }

    //convert CreateMenuCategory DTO obj to entity obj
    public static MenuCategory fromDTOToEntity(CreateMenuCategoryDTO CreateMenuCategoryDTO) {
        MenuCategory menuCategory = new MenuCategory();
        menuCategory.setMenuCategoryName(CreateMenuCategoryDTO.menuCategoryName());
        return menuCategory;
    }
}
