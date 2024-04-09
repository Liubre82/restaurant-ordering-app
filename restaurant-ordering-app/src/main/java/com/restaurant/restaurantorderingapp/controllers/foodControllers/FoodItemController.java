package com.restaurant.restaurantorderingapp.controllers.foodControllers;

import com.restaurant.restaurantorderingapp.dto.foodImagesDto.FoodImageDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto.FoodItemVariationDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemsDto.CreateFoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemsDto.FoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemsDto.UpdateFoodItemDTO;
import com.restaurant.restaurantorderingapp.services.foodServices.FoodItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodItemController {

    private final FoodItemService foodItemService;

    @Autowired
    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    // curl -i -s http://localhost:8080/api/foodItems | sed -e 's/{/\n&/g'
    @GetMapping("/public/foodItems")
    public ResponseEntity<List<FoodItemDTO>> getFoodItems() {
        List<FoodItemDTO> foodItems = foodItemService.getAllFoodItems();
        return ResponseEntity.ok(foodItems);
    }

    @GetMapping("/public/foodItems/{foodItemId}")
    public ResponseEntity<FoodItemDTO> getFoodItem(@PathVariable String foodItemId) {
        FoodItemDTO foodItemDTO = foodItemService.getFoodItemById(foodItemId);
        return ResponseEntity.ok(foodItemDTO);
    }

    @GetMapping("/public/foodItems/{foodItemId}/foodItemVariations")
    public ResponseEntity<List<FoodItemVariationDTO>> getAllFoodItemVariationsByFoodItemId(
            @PathVariable String foodItemId) {
        List<FoodItemVariationDTO> foodItemVariationDTOS = foodItemService.getAllFoodItemVariations(foodItemId);
        return ResponseEntity.ok(foodItemVariationDTOS);
    }

    @GetMapping("/public/foodItems/{foodItemId}/foodImages")
    public ResponseEntity<List<FoodImageDTO>> getAllFoodImagesByFoodItemId(
            @PathVariable String foodItemId) {
        List<FoodImageDTO> foodImagesDTO = foodItemService.getAllFoodImages(foodItemId);
        return ResponseEntity.ok(foodImagesDTO);
    }

    @GetMapping("/public/foodItems/search")
    public ResponseEntity<List<FoodItemDTO>> searchFoodItems(
            @RequestParam String searchInput) {
        List<FoodItemDTO> foodItems = foodItemService.searchFoodItems(searchInput);
        return foodItems.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(foodItems);
    }

    // curl -i -X POST -H "Content-Type: application/json" -d '{"foodItemName": "XXL"}' http://localhost:8080/api/foodItems
    @PostMapping("/admin/foodItems")
    public ResponseEntity<String> createFoodItem(
            @RequestBody @Valid CreateFoodItemDTO CreateFoodItemDTO) {
        foodItemService.createFoodItems(CreateFoodItemDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Food Item created successfully.");
    }

    // curl -i -X DELETE http://localhost:8080/api/foodItems/7
    @DeleteMapping("/admin/foodItems/{foodItemId}")
    public ResponseEntity<String> deleteFoodItem(@PathVariable String foodItemId) {
        foodItemService.deleteFoodItem(foodItemId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Food Item deleted successfully.");
    }

    @PutMapping("/admin/foodItems/{foodItemId}")
    public ResponseEntity<FoodItemDTO> updateFoodItem(
            @PathVariable String foodItemId, @RequestBody @Valid UpdateFoodItemDTO updateFoodItemDTO) {
        FoodItemDTO foodItemDTO = foodItemService.updateFoodItem(foodItemId, updateFoodItemDTO);
        return ResponseEntity.ok(foodItemDTO);
    }
}
