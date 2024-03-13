package com.restaurant.restaurantorderingapp.services;

import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.MenuCategoryDTO;
import com.restaurant.restaurantorderingapp.models.food.MenuCategory;
import com.restaurant.restaurantorderingapp.repositories.MenuCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuCategoryService {

    private final MenuCategoryRepository menuCategoryRepository;

    @Autowired
    public MenuCategoryService(MenuCategoryRepository menuCategoryRepository) {
        this.menuCategoryRepository = menuCategoryRepository;
    }

    //convert MenuCategory entity obj to DTO obj
    public MenuCategoryDTO fromEntityToDTO(MenuCategory menuCategory) {
        return new MenuCategoryDTO(
                menuCategory.getMenuCategoryId(),
                menuCategory.getMenuCategoryName()
        );
    }

    /* Retrieve all entities from menu_categories table then pass it into a
    * stream to be converted into DTOs and return it in a List*/
    public List<MenuCategoryDTO> getAllMenuCategories() {
        List<MenuCategory> menuCategories = (List<MenuCategory>) menuCategoryRepository.findAll();
        return menuCategories.stream()
                .map(this::fromEntityToDTO) //entity -> fromEntityToDTO(entity)
                .collect(Collectors.toList());
    }

//    public MenuCategory createMenuCategories(MenuCategory categoryName) {
//
//
//    }
}
