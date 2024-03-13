package com.restaurant.restaurantorderingapp.controllers;

import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.MenuCategoryDTO;
import com.restaurant.restaurantorderingapp.services.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menuCategories")
public class MenuCategoryController {

    private final MenuCategoryService menuCategoryService;


    @Autowired
    public MenuCategoryController(MenuCategoryService menuCategoryService) {
        this.menuCategoryService = menuCategoryService;
    }

    //curl -s http://localhost:8080/api/menuCategories/ | sed -e 's/{/\n&/g'
    @GetMapping
    public ResponseEntity<List<MenuCategoryDTO>> getMenuCategories() {
        List<MenuCategoryDTO> menuCategories = menuCategoryService.getAllMenuCategories();
        return new ResponseEntity<>(menuCategories, HttpStatus.OK);
    }
//
//    @PostMapping
//    public String createMenuCategory(
//            @RequestBody MenuCategoryDTO menuCategoryDTO
//            ) {
//
//    }

}
