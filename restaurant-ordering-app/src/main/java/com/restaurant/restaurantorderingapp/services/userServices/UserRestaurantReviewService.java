package com.restaurant.restaurantorderingapp.services.userServices;

import com.restaurant.restaurantorderingapp.dto.userRestaurantReviewsDto.CreateUserRestaurantReviewDTO;
import com.restaurant.restaurantorderingapp.dto.userRestaurantReviewsDto.UpdateUserRestaurantReviewDTO;
import com.restaurant.restaurantorderingapp.dto.userRestaurantReviewsDto.UserRestaurantReviewDTO;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.DuplicateKeyException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.EmptyDataTableException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.NotFoundException;
import com.restaurant.restaurantorderingapp.models.user.User;
import com.restaurant.restaurantorderingapp.models.user.UserRestaurantReview;
import com.restaurant.restaurantorderingapp.repositories.userRepositories.UserRepository;
import com.restaurant.restaurantorderingapp.repositories.userRepositories.UserRestaurantReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.restaurant.restaurantorderingapp.utils.mappers.UserRestaurantReviewMapper.fromDTOToEntity;
import static com.restaurant.restaurantorderingapp.utils.mappers.UserRestaurantReviewMapper.fromEntityToDTO;

@Service
public class UserRestaurantReviewService {

    private final String entityName = "User Restaurant Review";

    public final UserRestaurantReviewRepository userRestaurantReviewRepository;
    
    private final UserRepository userRepository;

    @Autowired
    public UserRestaurantReviewService(UserRestaurantReviewRepository userRestaurantReviewRepository, UserRepository userRepository) {
        this.userRestaurantReviewRepository = userRestaurantReviewRepository;
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a user entity with the given id.
     *
     * @param userId is an id of type Long that uniquely identifies a user entity.
     * @return a User entity.
     * @throws NotFoundException if the userId is not found/doesn't exist in our db/context.
     */
    public User findUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(entityName, userId));
    }

    /**
     * Retrieves an UserRestaurantReview entity with the given id.
     *
     * @param userRestaurantReviewId is an id of type Long that uniquely identifies a users address entity.
     * @return a UserRestaurantReview entity.
     * @throws NotFoundException if the userRestaurantReviewId is not found/doesn't exist in our db/context.
     */
    public UserRestaurantReview findUserRestaurantReviewById(Long userRestaurantReviewId) {
        return userRestaurantReviewRepository.findById(userRestaurantReviewId)
                .orElseThrow(() -> new NotFoundException(entityName, userRestaurantReviewId));
    }

    /**
     * Retrieves an UserRestaurantReview entity with the given id and convert it to its DTO form.
     *
     * @param userRestaurantReviewId is an id of type Long that uniquely identifies a users address.
     * @return a UserRestaurantReview DTO.
     * @throws NotFoundException if the userRestaurantReviewId is not found/doesn't exist in our db/context.
     */
    public UserRestaurantReviewDTO getUserRestaurantReviewById(Long userRestaurantReviewId) {
        UserRestaurantReview userRestaurantReview = findUserRestaurantReviewById(userRestaurantReviewId);
        UserRestaurantReviewDTO UserRestaurantReviewDTO = fromEntityToDTO(userRestaurantReview);
        return  UserRestaurantReviewDTO;
    }

    /**
     * Retrieve all entities of UserRestaurantReview then pass it into a
     * stream to be converted into DTOs to be sent to the client.
     *
     * @return a list of userRestaurantReviews in DTO form.
     * @throws EmptyDataTableException if there is no userRestaurantReviews in the datatable, AKA empty datatable.
     */
    public List<UserRestaurantReviewDTO> getAllUserRestaurantReviews() {
        String entityName = "food items";
        List<UserRestaurantReview> userRestaurantReviews = (List<UserRestaurantReview>) userRestaurantReviewRepository.findAll();
        if(userRestaurantReviews.isEmpty()) throw new EmptyDataTableException(entityName);
        return userRestaurantReviews.stream()
                .map(entity -> fromEntityToDTO(entity))
                .collect(Collectors.toList());
    }

    /**
     * Creates an entity of UserRestaurantReview.
     *
     * @param createUserRestaurantReviewDTO the form data sent by the client to create a new UserRestaurantReview entity.
     */
    public void createUserRestaurantReview(CreateUserRestaurantReviewDTO createUserRestaurantReviewDTO) {
        String userId = createUserRestaurantReviewDTO.userId();
        boolean userIdCheck = userRestaurantReviewRepository.existsByUser(userId);
        if(userIdCheck) throw new DuplicateKeyException(userId, "A user can only create 1 review.");

        User user = findUserById(userId);
        UserRestaurantReview userRestaurantReview = fromDTOToEntity(createUserRestaurantReviewDTO, user);
        userRestaurantReviewRepository.save(userRestaurantReview);
    }

    /**
     * Delete a UserRestaurantReview entity with the given id.
     *
     * @param userRestaurantReviewId is an id of type Long that uniquely identifies a users address.
     * @throws NotFoundException if the userRestaurantReviewId is not found/doesn't exist in our db/context.
     */
    public void deleteUserRestaurantReview(Long userRestaurantReviewId) throws NotFoundException {
        boolean UserRestaurantReviewExist = userRestaurantReviewRepository.existsById(userRestaurantReviewId);
        if(!UserRestaurantReviewExist) throw new NotFoundException(entityName, userRestaurantReviewId);
        userRestaurantReviewRepository.deleteById(userRestaurantReviewId);
    }

    /**
     * Update the entity and convert it to DTO form for the client.
     *
     * @param userRestaurantReviewId is an id of type Long that uniquely identifies a users address.
     * @param updateUserRestaurantReviewDTO the form data sent by the client to update an existing UserRestaurantReview entity.
     * @return UserRestaurantReviewDTO, the updated entity in DTO form.
     * @throws NotFoundException if the userRestaurantReviewId is not found/doesn't exist in our db/context.
     */
    public UserRestaurantReviewDTO updateUserRestaurantReview(Long userRestaurantReviewId, UpdateUserRestaurantReviewDTO updateUserRestaurantReviewDTO) {
        UserRestaurantReview userRestaurantReview = findUserRestaurantReviewById(userRestaurantReviewId);
        User user = findUserById(updateUserRestaurantReviewDTO.userId());
        userRestaurantReview.setUser(user);
        userRestaurantReview.setUserRestaurantReviewTitle(updateUserRestaurantReviewDTO.userRestaurantReviewTitle());
        userRestaurantReview.setUserRestaurantRating(updateUserRestaurantReviewDTO.userRestaurantRating());
        userRestaurantReview.setUserRestaurantReviewDescription(updateUserRestaurantReviewDTO.userRestaurantReviewDescription());
        userRestaurantReviewRepository.save(userRestaurantReview);
        UserRestaurantReviewDTO UserRestaurantReviewDTOUpdated = fromEntityToDTO(userRestaurantReview);
        return UserRestaurantReviewDTOUpdated;
    }
}
