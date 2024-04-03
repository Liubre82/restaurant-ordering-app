package com.restaurant.restaurantorderingapp.utils.mappers;


import com.restaurant.restaurantorderingapp.dto.usersDto.CreateUserDTO;
import com.restaurant.restaurantorderingapp.dto.usersDto.FullUserDTO;
import com.restaurant.restaurantorderingapp.dto.usersDto.UserDTO;
import com.restaurant.restaurantorderingapp.models.user.User;
import com.restaurant.restaurantorderingapp.models.user.UserRole;

public class UserMapper {

    private UserMapper() {}

    public static UserDTO fromEntityToDTO(User user) {
        return new UserDTO(
            user.getUserId(),
            user.getUserRole(),
            user.getUserName(),
            user.getUserEmail()
        );
    }

    public static User fromDTOToEntity(CreateUserDTO createUserDTO, UserRole userRole) {
        User user = new User();
        user.setUserRole(userRole);
        user.setUserName(createUserDTO.userName());
        user.setUserEmail(createUserDTO.userEmail());
        user.setUserPassword(createUserDTO.userPassword());
        return user;
    }

    // Only for development purpose to see the User password field.
    public static FullUserDTO fromEntityToDTOFull(User user) {
        return new FullUserDTO(
                user.getUserId(),
                user.getUserRole(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserPassword()
        );
    }
}
