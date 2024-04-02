package com.restaurant.restaurantorderingapp.services.userServices;

import com.restaurant.restaurantorderingapp.models.user.User;
import com.restaurant.restaurantorderingapp.repositories.userRepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUser(String userId) {
        Optional<User> userDTO = userRepository.findById(userId);
        return userDTO;
    }


}