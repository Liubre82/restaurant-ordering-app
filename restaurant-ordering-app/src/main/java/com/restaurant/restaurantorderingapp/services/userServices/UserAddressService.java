package com.restaurant.restaurantorderingapp.services.userServices;

import com.restaurant.restaurantorderingapp.dto.userAddressesDto.CreateUserAddressDTO;
import com.restaurant.restaurantorderingapp.dto.userAddressesDto.UpdateUserAddressDTO;
import com.restaurant.restaurantorderingapp.dto.userAddressesDto.UserAddressDTO;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.EmptyDataTableException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.NotFoundException;
import com.restaurant.restaurantorderingapp.models.user.User;
import com.restaurant.restaurantorderingapp.models.user.UserAddress;
import com.restaurant.restaurantorderingapp.repositories.userRepositories.UserAddressRepository;
import com.restaurant.restaurantorderingapp.repositories.userRepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.restaurant.restaurantorderingapp.utils.mappers.UserAddressMapper.fromDTOToEntity;
import static com.restaurant.restaurantorderingapp.utils.mappers.UserAddressMapper.fromEntityToDTO;

@Service
public class UserAddressService {

    private final String entityName = "User Address";

    private final UserAddressRepository userAddressRepository;

    private final UserRepository userRepository;

    @Autowired
    public UserAddressService(UserAddressRepository userAddressRepository, UserRepository userRepository) {
        this.userAddressRepository = userAddressRepository;
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
     * Retrieves an UserAddress entity with the given id.
     *
     * @param userAddressId is an id of type Long that uniquely identifies a users address entity.
     * @return a UserAddress entity.
     * @throws NotFoundException if the userAddressId is not found/doesn't exist in our db/context.
     */
    public UserAddress findUserAddressById(Long userAddressId) {
        return userAddressRepository.findById(userAddressId)
                .orElseThrow(() -> new NotFoundException(entityName, userAddressId));
    }

    /**
     * Retrieves an UserAddress entity with the given id and convert it to its DTO form.
     *
     * @param userAddressId is an id of type Long that uniquely identifies a users address.
     * @return a UserAddress DTO.
     * @throws NotFoundException if the userAddressId is not found/doesn't exist in our db/context.
     */
    public UserAddressDTO getUserAddressById(Long userAddressId) {
        UserAddress userAddress = findUserAddressById(userAddressId);
        UserAddressDTO UserAddressDTO = fromEntityToDTO(userAddress);
        return  UserAddressDTO;
    }

    /**
     * Retrieve all entities of UserAddress then pass it into a
     * stream to be converted into DTOs to be sent to the client.
     *
     * @return a list of userAddresses in DTO form.
     * @throws EmptyDataTableException if there is no userAddresses in the datatable, AKA empty datatable.
     */
    public List<UserAddressDTO> getAllUserAddresses() {
        String entityName = "food items";
        List<UserAddress> userAddresses = (List<UserAddress>) userAddressRepository.findAll();
        if(userAddresses.isEmpty()) throw new EmptyDataTableException(entityName);
        return userAddresses.stream()
                .map(entity -> fromEntityToDTO(entity))
                .collect(Collectors.toList());
    }

    /**
     * Creates an entity of UserAddress.
     *
     * @param createUserAddressDTO the form data sent by the client to create a new UserAddress entity.
     */
    public void createUserAddress(CreateUserAddressDTO createUserAddressDTO) {
        User user = findUserById(createUserAddressDTO.userId());
        UserAddress userAddress = fromDTOToEntity(createUserAddressDTO, user);
        userAddressRepository.save(userAddress);
    }

    /**
     * Delete a UserAddress entity with the given id.
     *
     * @param userAddressId is an id of type Long that uniquely identifies a users address.
     * @throws NotFoundException if the userAddressId is not found/doesn't exist in our db/context.
     */
    public void deleteUserAddress(Long userAddressId) throws NotFoundException {
        boolean UserAddressExist = userAddressRepository.existsById(userAddressId);
        if(!UserAddressExist) throw new NotFoundException(entityName, userAddressId);
        userAddressRepository.deleteById(userAddressId);
    }

    /**
     * Update the entity and convert it to DTO form for the client.
     *
     * @param userAddressId is an id of type Long that uniquely identifies a users address.
     * @param updateUserAddressDTO the form data sent by the client to update an existing UserAddress entity.
     * @return UserAddressDTO, the updated entity in DTO form.
     * @throws NotFoundException if the userAddressId is not found/doesn't exist in our db/context.
     */
    public UserAddressDTO updateUserAddress(Long userAddressId, UpdateUserAddressDTO updateUserAddressDTO) {
        UserAddress userAddress = findUserAddressById(userAddressId);
        User user = findUserById(updateUserAddressDTO.userId());
        userAddress.setUser(user);
        userAddress.setAddressName(updateUserAddressDTO.addressName());
        userAddress.setCity(updateUserAddressDTO.city());
        userAddress.setState(updateUserAddressDTO.state());
        userAddress.setZipCode(updateUserAddressDTO.zipCode());
        userAddressRepository.save(userAddress);
        UserAddressDTO UserAddressDTOUpdated = fromEntityToDTO(userAddress);
        return UserAddressDTOUpdated;
    }
}
