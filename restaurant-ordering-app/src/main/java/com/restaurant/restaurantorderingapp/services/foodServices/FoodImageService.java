package com.restaurant.restaurantorderingapp.services.foodServices;

import com.restaurant.restaurantorderingapp.dto.foodImagesDto.CreateFoodImageDTO;
import com.restaurant.restaurantorderingapp.dto.foodImagesDto.FoodImageDTO;
import com.restaurant.restaurantorderingapp.dto.foodImagesDto.UpdateFoodImageDTO;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.EmptyDataTableException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.NotFoundException;
import com.restaurant.restaurantorderingapp.models.food.FoodImage;
import com.restaurant.restaurantorderingapp.models.food.FoodItem;
import com.restaurant.restaurantorderingapp.repositories.foodRepositories.FoodImageRepository;
import com.restaurant.restaurantorderingapp.repositories.foodRepositories.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.restaurant.restaurantorderingapp.utils.mappers.FoodImageMapper.fromEntityToDTO;

@Service
public class FoodImageService {
    
    private final FoodImageRepository foodImageRepository;

    private final FoodItemRepository foodItemRepository;
    
    public FoodImageService(FoodImageRepository foodImageRepository, FoodItemRepository foodItemRepository) {
        this.foodImageRepository = foodImageRepository;
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItem findFoodItemById(String foodItemId) {
        return foodItemRepository.findById(foodItemId)
                .orElseThrow(() -> new NotFoundException("Food Item", foodItemId));
    }

    public FoodImage findFoodImageById(Long foodImageId) {
        return foodImageRepository.findById(foodImageId)
                .orElseThrow(() -> new NotFoundException("Food Image", foodImageId));
    }

    public FoodImageDTO getFoodImageById(Long foodImageId) {
        FoodImage foodImage = findFoodImageById(foodImageId);
        FoodImageDTO FoodImageDTO = fromEntityToDTO(foodImage);
        return  FoodImageDTO;
    }

    /**
     * Retrieve all entities from food_items table then pass it into a
     * stream to be converted into DTOs to be sent to the client.
     *
     * @return the list of foodImages in DTO form.
     * @throws EmptyDataTableException if there is no foodImages in the datatable, AKA empty datatable.
     */
    public List<FoodImageDTO> getAllFoodImages() {
        String entityName = "food items";
        List<FoodImage> foodImages = (List<FoodImage>) foodImageRepository.findAll();
        if(foodImages.isEmpty()) throw new EmptyDataTableException(entityName);
        return foodImages.stream()
                .map(entity -> fromEntityToDTO(entity))
                .collect(Collectors.toList());
    }

    public void createFoodImages(CreateFoodImageDTO createFoodImageDTO) {
        FoodItem foodItem = findFoodItemById(createFoodImageDTO.foodItemId());
        FoodImage foodImage = new FoodImage();
        foodImage.setFoodItem(foodItem);
        foodImage.setImageUrl(createFoodImageDTO.imageUrl());
        foodImageRepository.save(foodImage);
    }

    public void deleteFoodImage(Long foodImageId) throws NotFoundException {
        boolean FoodImageExist = foodImageRepository.existsById(foodImageId);
        if(!FoodImageExist) throw new NotFoundException("Food Item", foodImageId);
        foodImageRepository.deleteById(foodImageId);
    }

    public FoodImageDTO updateFoodImage(Long foodImageId, UpdateFoodImageDTO updateFoodImageDTO) {
        FoodImage foodImage = findFoodImageById(foodImageId);
        foodImage.setImageUrl(updateFoodImageDTO.imageUrl());
        foodImageRepository.save(foodImage);
        FoodImageDTO FoodImageDTOUpdated = fromEntityToDTO(foodImage);
        return FoodImageDTOUpdated;
    }
}
