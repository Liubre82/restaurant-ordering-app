package com.restaurant.restaurantorderingapp.controllers;

import com.restaurant.restaurantorderingapp.dto.foodImagesDto.FoodImageDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto.FoodItemVariationDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemsDto.CreateFoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemsDto.FoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemsDto.UpdateFoodItemDTO;
import com.restaurant.restaurantorderingapp.services.FoodItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foodItems")
public class FoodItemController {

    private final FoodItemService foodItemService;

    @Autowired
    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    // curl -i -s http://localhost:8080/api/foodItems | sed -e 's/{/\n&/g'
    @GetMapping
    public ResponseEntity<List<FoodItemDTO>> getFoodItems() {
        List<FoodItemDTO> foodItems = foodItemService.getAllFoodItems();
        return ResponseEntity.ok(foodItems);
    }

    @GetMapping("/{foodItemId}")
    public ResponseEntity<FoodItemDTO> getFoodItem(@PathVariable String foodItemId) {
        FoodItemDTO foodItemDTO = foodItemService.getFoodItemById(foodItemId);
        return ResponseEntity.ok(foodItemDTO);
    }

    @GetMapping("/{foodItemId}/foodItemVariations")
    public ResponseEntity<List<FoodItemVariationDTO>> getAllFoodItemVariationsByFoodItemId(
            @PathVariable String foodItemId) {
        List<FoodItemVariationDTO> foodItemVariationDTOS = foodItemService.getAllFoodItemVariations(foodItemId);
        return ResponseEntity.ok(foodItemVariationDTOS);
    }

    @GetMapping("/{foodItemId}/foodImages")
    public ResponseEntity<List<FoodImageDTO>> getAllFoodImagesByFoodItemId(
            @PathVariable String foodItemId) {
        List<FoodImageDTO> foodImagesDTO = foodItemService.getAllFoodImages(foodItemId);
        return ResponseEntity.ok(foodImagesDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FoodItemDTO>> searchFoodItems(
            @RequestParam String searchInput) {
        List<FoodItemDTO> foodItems = foodItemService.searchFoodItems(searchInput);
        return foodItems.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(foodItems);
    }

    // curl -i -X POST -H "Content-Type: application/json" -d '{"foodItemName": "XXL"}' http://localhost:8080/api/foodItems
    @PostMapping
    public ResponseEntity<String> createFoodItem(
            @RequestBody @Valid CreateFoodItemDTO CreateFoodItemDTO) {
        foodItemService.createFoodItems(CreateFoodItemDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Food Item created successfully.");
    }

    // curl -i -X DELETE http://localhost:8080/api/foodItems/7
    @DeleteMapping("/{foodItemId}")
    public ResponseEntity<String> deleteFoodItem(@PathVariable String foodItemId) {
        foodItemService.deleteFoodItem(foodItemId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Food Item deleted successfully.");
    }

    @PutMapping("/{foodItemId}")
    public ResponseEntity<FoodItemDTO> updateFoodItem(
            @PathVariable String foodItemId, @RequestBody @Valid UpdateFoodItemDTO updateFoodItemDTO) {
        FoodItemDTO foodItemDTO = foodItemService.updateFoodItem(foodItemId, updateFoodItemDTO);
        return ResponseEntity.ok(foodItemDTO);
    }
}
