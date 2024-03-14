package com.restaurant.restaurantorderingapp.controllers;

import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.CreateMenuCategoryDTO;
import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.MenuCategoryDTO;
import com.restaurant.restaurantorderingapp.services.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menuCategories")
public class MenuCategoryController {

    private final MenuCategoryService menuCategoryService;

    @Autowired
    public MenuCategoryController(MenuCategoryService menuCategoryService) {
        this.menuCategoryService = menuCategoryService;
    }


    // curl -i -s http://localhost:8080/api/menuCategories | sed -e 's/{/\n&/g'
    @GetMapping
    public ResponseEntity<List<MenuCategoryDTO>> getMenuCategories() {
        List<MenuCategoryDTO> menuCategories = menuCategoryService.getAllMenuCategories();
        return ResponseEntity.ok(menuCategories);
    }

    @GetMapping("/{menuCategoryId}")
    public ResponseEntity<MenuCategoryDTO> getMenuCategory(@PathVariable Long menuCategoryId) {
        MenuCategoryDTO menuCategoryDTO = menuCategoryService.getMenuCategoryById(menuCategoryId);
        return ResponseEntity.ok(menuCategoryDTO);
    }

    // curl -i -X POST -H "Content-Type: application/json" -d '{"menuCategoryName": "JiajinCategory"}' http://localhost:8080/api/menuCategories
    @PostMapping
    public ResponseEntity<String> createMenuCategory(
            @RequestBody CreateMenuCategoryDTO CreateMenuCategoryDTO
            ) {
        menuCategoryService.createMenuCategories(CreateMenuCategoryDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Menu Category created successfully.");
    }

    // curl -i -X DELETE http://localhost:8080/api/menuCategories/7
    @DeleteMapping("/{menuCategoryId}")
    public ResponseEntity<String> deleteMenuCategory(@PathVariable Long menuCategoryId) {
        menuCategoryService.deleteMenuCategory(menuCategoryId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Menu Category deleted successfully.");
    }

    @PutMapping("/{menuCategoryId}")
    public ResponseEntity<MenuCategoryDTO> updateMenuCategory(@PathVariable Long menuCategoryId) {
        MenuCategoryDTO menuCategoryDTO = menuCategoryService.updateMenuCategory(menuCategoryId);
        return ResponseEntity.ok(menuCategoryDTO);
    }

}
