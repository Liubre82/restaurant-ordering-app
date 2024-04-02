package com.restaurant.restaurantorderingapp.controllers.foodControllers;

import com.restaurant.restaurantorderingapp.dto.foodImagesDto.CreateFoodImageDTO;
import com.restaurant.restaurantorderingapp.dto.foodImagesDto.FoodImageDTO;
import com.restaurant.restaurantorderingapp.dto.foodImagesDto.UpdateFoodImageDTO;
import com.restaurant.restaurantorderingapp.services.foodServices.FoodImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foodImages")
public class FoodImageController {

    private final String entityName = "Food Image";

    private final FoodImageService foodImageService;

    @Autowired
    public FoodImageController(FoodImageService foodImageService) {
        this.foodImageService = foodImageService;
    }

    // curl -i -s http://localhost:8080/api/foodImages | sed -e 's/{/\n&/g'
    @GetMapping
    public ResponseEntity<List<FoodImageDTO>> getFoodImages() {
        List<FoodImageDTO> foodImages = foodImageService.getAllFoodImages();
        return ResponseEntity.ok(foodImages);
    }

    @GetMapping("/{foodImageId}")
    public ResponseEntity<FoodImageDTO> getFoodImage(@PathVariable Long foodImageId) {
        FoodImageDTO foodImageDTO = foodImageService.getFoodImageById(foodImageId);
        return ResponseEntity.ok(foodImageDTO);
    }

    // curl -i -X POST -H "Content-Type: application/json" -d '{"foodImageName": "XXL"}' http://localhost:8080/api/foodImages
    @PostMapping
    public ResponseEntity<String> createFoodImage(
            @RequestBody @Valid CreateFoodImageDTO CreateFoodImageDTO) {
        foodImageService.createFoodImages(CreateFoodImageDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entityName + " created successfully.");
    }

    // curl -i -X DELETE http://localhost:8080/api/foodImages/7
    @DeleteMapping("/{foodImageId}")
    public ResponseEntity<String> deleteFoodImage(@PathVariable Long foodImageId) {
        foodImageService.deleteFoodImage(foodImageId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityName + " deleted successfully.");
    }

    @PutMapping("/{foodImageId}")
    public ResponseEntity<FoodImageDTO> updateFoodImage(
            @PathVariable Long foodImageId, @RequestBody @Valid UpdateFoodImageDTO updateFoodImageDTO) {
        FoodImageDTO foodImageDTO = foodImageService.updateFoodImage(foodImageId, updateFoodImageDTO);
        return ResponseEntity.ok(foodImageDTO);
    }
}
