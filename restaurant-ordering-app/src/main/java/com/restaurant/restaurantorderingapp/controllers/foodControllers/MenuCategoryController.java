package com.restaurant.restaurantorderingapp.controllers.foodControllers;

import com.restaurant.restaurantorderingapp.dto.foodItemsDto.FoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.CreateMenuCategoryDTO;
import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.MenuCategoryDTO;
import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.UpdateMenuCategoryDTO;
import com.restaurant.restaurantorderingapp.services.foodServices.MenuCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuCategoryController {

    private final MenuCategoryService menuCategoryService;

    @Autowired
    public MenuCategoryController(MenuCategoryService menuCategoryService) {
        this.menuCategoryService = menuCategoryService;
    }

    // curl -i -s http://localhost:8080/api/menuCategories | sed -e 's/{/\n&/g'
    @GetMapping("/public/menuCategories")
    public ResponseEntity<List<MenuCategoryDTO>> getMenuCategories() {
        List<MenuCategoryDTO> menuCategories = menuCategoryService.getAllMenuCategories();
        return ResponseEntity.ok(menuCategories);
    }

    @GetMapping("/public/menuCategories/search")
    public ResponseEntity<List<MenuCategoryDTO>> searchMenuCategories(
            @RequestParam String searchInput) {
        List<MenuCategoryDTO> menuCategories = menuCategoryService.searchMenuCategories(searchInput);
        return menuCategories.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(menuCategories);
    }

    @GetMapping("/public/menuCategories/{menuCategoryId}")
    public ResponseEntity<MenuCategoryDTO> getMenuCategory(@PathVariable Long menuCategoryId) {
        MenuCategoryDTO menuCategoryDTO = menuCategoryService.getMenuCategoryById(menuCategoryId);
        return ResponseEntity.ok(menuCategoryDTO);
    }

    @GetMapping("/public/menuCategories/{menuCategoryId}/foodItems")
    public ResponseEntity<List<FoodItemDTO>> getFoodItemsByMenuCategoryId(
            @PathVariable Long menuCategoryId) {
        List<FoodItemDTO> foodItems = menuCategoryService.getAllFoodItemsByMenuCategoryId(menuCategoryId);
        return foodItems.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(foodItems);
    }

    // curl -i -X POST -H "Content-Type: application/json" -d '{"menuCategoryName": "JiajinCategory"}' http://localhost:8080/api/menuCategories
    @PostMapping("/admin/menuCategories")
    public ResponseEntity<String> createMenuCategory(
            @RequestBody @Valid CreateMenuCategoryDTO CreateMenuCategoryDTO) {
        menuCategoryService.createMenuCategories(CreateMenuCategoryDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Menu Category created successfully.");
    }

    // curl -i -X DELETE http://localhost:8080/api/menuCategories/7
    @DeleteMapping("/admin/menuCategories/{menuCategoryId}")
    public ResponseEntity<String> deleteMenuCategory(@PathVariable Long menuCategoryId) {
        menuCategoryService.deleteMenuCategory(menuCategoryId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Menu Category deleted successfully.");
    }

    @PutMapping("/admin/menuCategories/{menuCategoryId}")
    public ResponseEntity<MenuCategoryDTO> updateMenuCategory(
            @PathVariable Long menuCategoryId, @RequestBody @Valid UpdateMenuCategoryDTO updateMenuCategoryDTO) {
        MenuCategoryDTO menuCategoryDTO = menuCategoryService.updateMenuCategory(menuCategoryId, updateMenuCategoryDTO);
        return ResponseEntity.ok(menuCategoryDTO);
    }

}
