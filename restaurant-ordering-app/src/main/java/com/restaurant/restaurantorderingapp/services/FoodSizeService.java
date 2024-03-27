package com.restaurant.restaurantorderingapp.services;

import com.restaurant.restaurantorderingapp.dto.foodSizesDto.CreateFoodSizeDTO;
import com.restaurant.restaurantorderingapp.dto.foodSizesDto.FoodSizeDTO;
import com.restaurant.restaurantorderingapp.dto.foodSizesDto.UpdateFoodSizeDTO;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.DuplicateKeyException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.EmptyDataTableException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.NotFoundException;
import com.restaurant.restaurantorderingapp.models.food.FoodSize;
import com.restaurant.restaurantorderingapp.repositories.FoodSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FoodSizeService {

    private final FoodSizeRepository foodSizeRepository;

    @Autowired
    public FoodSizeService(FoodSizeRepository foodSizeRepository) {
        this.foodSizeRepository = foodSizeRepository;
    }
    
    public FoodSizeDTO fromEntityToDTO(FoodSize foodSize) {
        return new FoodSizeDTO(
                foodSize.getFoodSizeId(),
                foodSize.getFoodSizeName()
        );
    }

    public FoodSize fromDTOToEntity(CreateFoodSizeDTO createFoodSizeDTO) {
        FoodSize foodSize = new FoodSize();
        foodSize.setFoodSizeName(createFoodSizeDTO.foodSizeName());
        return foodSize;
    }

    public FoodSize findFoodSizeById(Long foodSizeId) {
        return foodSizeRepository.findById(foodSizeId)
                .orElseThrow(() -> new NotFoundException("Food Size", foodSizeId));
    }

    public FoodSizeDTO getFoodSizeById(Long foodSizeId) {
        FoodSize foodSize = findFoodSizeById(foodSizeId);
        FoodSizeDTO FoodSizeDTO = fromEntityToDTO(foodSize);
        return  FoodSizeDTO;
    }

    /* Retrieve all entities from food_sizes table then pass it into a
     * stream to be converted into DTOs and return it in a List*/
    public List<FoodSizeDTO> getAllFoodSizes() {
        String entityName = "food sizes";
        List<FoodSize> foodSizes = (List<FoodSize>) foodSizeRepository.findAll();
        if(foodSizes.isEmpty()) throw new EmptyDataTableException(entityName);
        return foodSizes.stream()
                .map(this::fromEntityToDTO) //entity -> fromEntityToDTO(entity)
                .collect(Collectors.toList());
    }

    public void createFoodSizes(CreateFoodSizeDTO createFoodSizeDTO) {
        boolean categoryNameCheck = foodSizeRepository.existsByFoodSizeName(createFoodSizeDTO.foodSizeName());
        if(categoryNameCheck) throw new DuplicateKeyException(createFoodSizeDTO.foodSizeName());
        FoodSize foodSize = fromDTOToEntity(createFoodSizeDTO);
        foodSizeRepository.save(foodSize);
    }

    public void deleteFoodSize(Long foodSizeId) throws NotFoundException {
        boolean FoodSizeExist = foodSizeRepository.existsById(foodSizeId);
        if(!FoodSizeExist) throw new NotFoundException("Food Size", foodSizeId);
        foodSizeRepository.deleteById(foodSizeId);
    }

    public FoodSizeDTO updateFoodSize(Long foodSizeId, UpdateFoodSizeDTO updateFoodSizeDTO) {
        FoodSize FoodSize = findFoodSizeById(foodSizeId);
        FoodSize.setFoodSizeName(updateFoodSizeDTO.foodSizeName());
        foodSizeRepository.save(FoodSize);
        FoodSizeDTO FoodSizeDTOUpdated = fromEntityToDTO(FoodSize);
        return FoodSizeDTOUpdated;
    }

    public List<FoodSizeDTO> searchFoodSizes(String searchInput) {
        String wildCardSearchInput = "%" + searchInput + "%";
        Iterable<FoodSize> FoodSizesIterable = foodSizeRepository.findByFoodSizeNameLike(wildCardSearchInput);
        if(!FoodSizesIterable.iterator().hasNext()) {
            return Collections.emptyList();
        }
        List<FoodSizeDTO> FoodSizes = StreamSupport.stream(FoodSizesIterable.spliterator(), false)
                .map(this::fromEntityToDTO)
                .collect(Collectors.toList());
        return FoodSizes;
    }


}
