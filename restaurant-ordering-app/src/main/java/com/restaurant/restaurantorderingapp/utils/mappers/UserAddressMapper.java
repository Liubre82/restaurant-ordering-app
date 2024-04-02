package com.restaurant.restaurantorderingapp.utils.mappers;

import com.restaurant.restaurantorderingapp.dto.userAddressesDto.CreateUserAddressDTO;
import com.restaurant.restaurantorderingapp.dto.userAddressesDto.UserAddressDTO;
import com.restaurant.restaurantorderingapp.models.user.User;
import com.restaurant.restaurantorderingapp.models.user.UserAddress;

public class UserAddressMapper {
    private UserAddressMapper() {}

    public static UserAddressDTO fromEntityToDTO(UserAddress userAddress) {
        return new UserAddressDTO(
                userAddress.getUserAddressId(),
                userAddress.getUser().getUserId(),
                userAddress.getAddressName(),
                userAddress.getCity(),
                userAddress.getState(),
                userAddress.getZipCode()
        );
    }

    public static UserAddress fromDTOToEntity(CreateUserAddressDTO createUserAddressDTO, User user) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUser(user);
        userAddress.setAddressName(createUserAddressDTO.addressName());
        userAddress.setCity(createUserAddressDTO.city());
        userAddress.setState(createUserAddressDTO.state());
        userAddress.setZipCode(createUserAddressDTO.zipCode());
        return userAddress;
    }
}
