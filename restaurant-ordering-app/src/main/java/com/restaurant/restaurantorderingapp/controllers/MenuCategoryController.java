package com.restaurant.restaurantorderingapp.controllers;

import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.CreateUpdateMenuCategoryDTO;
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

    //curl -i -s http://localhost:8080/api/menuCategories | sed -e 's/{/\n&/g'
    @GetMapping
    public ResponseEntity<List<MenuCategoryDTO>> getMenuCategories() {
        List<MenuCategoryDTO> menuCategories = menuCategoryService.getAllMenuCategories();
        return new ResponseEntity<>(menuCategories, HttpStatus.OK);
    }

    // curl -i -X POST -H "Content-Type: application/json" -d '{"menuCategoryName": "JiajinCategory"}' http://localhost:8080/api/menuCategories
    @PostMapping
    public String createMenuCategory(
            @RequestBody CreateUpdateMenuCategoryDTO createUpdateMenuCategoryDTO
            ) {
        return menuCategoryService.createMenuCategories(createUpdateMenuCategoryDTO).getMenuCategoryName();
    }

    // curl -i -X DELETE http://localhost:8080/api/menuCategories/7
    @DeleteMapping("/{menuCategoryId}")
    public ResponseEntity<Void> deleteMenuCategory(@PathVariable Long menuCategoryId) {
        boolean deleted = menuCategoryService.deleteMenuCategory(menuCategoryId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PutMapping("/{menuCategoryId}")
//    public ResponseEntity<> updateMenuCategory() {
//
//        return ResponseEntity
//                .ok();
//    }

}
