package com.restaurant.restaurantorderingapp.services.userServices;

import com.restaurant.restaurantorderingapp.dto.userAddressesDto.UserAddressDTO;
import com.restaurant.restaurantorderingapp.dto.userRestaurantReviewsDto.UserRestaurantReviewDTO;
import com.restaurant.restaurantorderingapp.dto.usersDto.FullUserDTO;
import com.restaurant.restaurantorderingapp.dto.usersDto.UpdateUserDTO;
import com.restaurant.restaurantorderingapp.dto.usersDto.UpdateUserPasswordDTO;
import com.restaurant.restaurantorderingapp.dto.usersDto.UserDTO;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.EmptyDataTableException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.NotFoundException;
import com.restaurant.restaurantorderingapp.models.user.User;
import com.restaurant.restaurantorderingapp.models.user.UserAddress;
import com.restaurant.restaurantorderingapp.models.user.UserRestaurantReview;
import com.restaurant.restaurantorderingapp.models.user.UserRole;
import com.restaurant.restaurantorderingapp.repositories.userRepositories.UserRepository;
import com.restaurant.restaurantorderingapp.repositories.userRepositories.UserRoleRepository;
import com.restaurant.restaurantorderingapp.utils.mappers.UserAddressMapper;
import com.restaurant.restaurantorderingapp.utils.mappers.UserMapper;
import com.restaurant.restaurantorderingapp.utils.mappers.UserRestaurantReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.restaurant.restaurantorderingapp.utils.mappers.UserMapper.fromEntityToDTO;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final String entityName = "User";
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    /**
     * Retrieves a user entity with the given id.
     *
     * @param userRoleId is an id of type String that uniquely identifies a user role entity.
     * @return a User entity.
     * @throws NotFoundException if the userRoleId is not found/doesn't exist in our db/context.
     */
    public UserRole findUserRoleById(String userRoleId) {
        return userRoleRepository.findById(userRoleId)
                .orElseThrow(() -> new NotFoundException("User Role", userRoleId));
    }

    /**
     * Retrieves a User entity with the given id.
     *
     * @param userId is an id of type String that uniquely identifies a users address entity.
     * @return a User entity.
     * @throws NotFoundException if the userId is not found/doesn't exist in our db/context.
     */
    public User findUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(entityName, userId));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(entityName, username));
    }

    /**
     * Retrieves a User entity with the given id and convert it to its DTO form.
     *
     * @param userId is an id of type String that uniquely identifies a users address.
     * @return a User DTO.
     * @throws NotFoundException if the userId is not found/doesn't exist in our db/context.
     */
    public UserDTO getUserById(String userId) {
        User user = findUserById(userId);
        UserDTO UserDTO = fromEntityToDTO(user);
        return  UserDTO;
    }

    /**
     * Retrieve all entities of User then pass it into a
     * stream to be converted into DTOs to be sent to the client.
     *
     * @return a list of users in DTO form.
     * @throws EmptyDataTableException if there is no users in the datatable, AKA empty datatable.
     */
    public List<UserDTO> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        if(users.isEmpty()) throw new EmptyDataTableException(entityName);
        return users.stream()
                .map(entity -> fromEntityToDTO(entity))
                .collect(Collectors.toList());
    }

    // Only for development purpose to see the User password field.
    public List<FullUserDTO> getAllUsersInfo() {
        List<User> users = (List<User>) userRepository.findAll();
        if(users.isEmpty()) throw new EmptyDataTableException(entityName);
        return users.stream()
                .map(UserMapper::fromEntityToDTOFull)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves review written by a user with the given id, and it converts it to its DTO form.
     *
     * @param userId is an id of type String that uniquely identifies a user.
     * @return a UserRestaurantReview DTO.
     * @throws NotFoundException if the userId is not found/doesn't exist in our db/context.
     */
    public UserRestaurantReviewDTO getReviewByUserId(String userId) {
        User user = findUserById(userId);
        UserRestaurantReview userRestaurantReview = user.getUserRestaurantReview();
        return UserRestaurantReviewMapper.fromEntityToDTO(userRestaurantReview);
    }

    /**
     * Retrieves all addresses saved/registered by a user with the given id, and it converts it to its DTO form.
     *
     * @param userId is an id of type String that uniquely identifies a user.
     * @return a list of UserRestaurantReview DTO.
     * @throws NotFoundException if the userId is not found/doesn't exist in our db/context.
     */
    public List<UserAddressDTO> getAllAddressesByUserId(String userId) {
        User user = findUserById(userId);
        List<UserAddress> userAddresses = user.getUserAddresses();
        return userAddresses.stream()
                .map(UserAddressMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Delete a User entity with the given id.
     *
     * @param userId is an id of type String that uniquely identifies a users address.
     * @throws NotFoundException if the userId is not found/doesn't exist in our db/context.
     */
    public void deleteUser(String userId) throws NotFoundException {
        boolean UserExist = userRepository.existsById(userId);
        if(!UserExist) throw new NotFoundException(entityName, userId);
        userRepository.deleteById(userId);
    }

    /**
     * Update a User entity userName & password fields and convert it to DTO form for the client.
     *
     * @param userId is an id of type String that uniquely identifies a User entity.
     * @param updateUserDTO the form data sent by the client to update an existing User entity.
     * @return UserDTO, the updated entity in DTO form.
     * @throws NotFoundException if the userId is not found/doesn't exist in our db/context.
     */
    public UserDTO updateUser(String userId, UpdateUserDTO updateUserDTO) {
        User user = findUserById(userId);
        user.setUsername(updateUserDTO.username());
        user.setUserEmail(updateUserDTO.userEmail());
        userRepository.save(user);
        UserDTO UserDTOUpdated = fromEntityToDTO(user);
        return UserDTOUpdated;
    }

    /**
     * Update a User entity password and convert it to DTO form for the client.
     *
     * @param userId is an id of type String that uniquely identifies a User.
     * @param updateUserPasswordDTO the form data sent by the client to update the password field of
     *                              the User entity.
     * @return UserDTO, the updated entity in DTO form.
     * @throws NotFoundException if the userId is not found/doesn't exist in our db/context.
     */
    public UserDTO updateUserPassword(String userId, UpdateUserPasswordDTO updateUserPasswordDTO) {
        User user = findUserById(userId);
        user.setPassword(updateUserPasswordDTO.userPassword());
        userRepository.save(user);
        UserDTO UserDTOUpdated = fromEntityToDTO(user);
        return UserDTOUpdated;
    }

}
