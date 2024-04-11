package com.restaurant.restaurantorderingapp.services;

import com.restaurant.restaurantorderingapp.dto.AuthReqRes;
import com.restaurant.restaurantorderingapp.dto.usersDto.CreateUserDTO;
import com.restaurant.restaurantorderingapp.dto.usersDto.SignInUserDTO;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.DuplicateKeyException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.NotFoundException;
import com.restaurant.restaurantorderingapp.models.user.User;
import com.restaurant.restaurantorderingapp.models.user.UserRole;
import com.restaurant.restaurantorderingapp.repositories.userRepositories.UserRepository;
import com.restaurant.restaurantorderingapp.repositories.userRepositories.UserRoleRepository;
import com.restaurant.restaurantorderingapp.utils.auth.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {


    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(
            UserRepository userRepository, UserRoleRepository userRoleRepository, JWTUtils jwtUtils,
            PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager)
    {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public UserRole findUserRoleByName(String userRoleName) {
        return userRoleRepository.findByUserRoleName(userRoleName)
                .orElseThrow(() -> new NotFoundException("User Role", userRoleName));
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User", username));
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
                .orElseThrow(() -> new NotFoundException("User", userId));
    }

    public AuthReqRes signUp(CreateUserDTO createUserDTO){
        AuthReqRes response = new AuthReqRes();

        UserRole userRole = findUserRoleByName(createUserDTO.userRoleName());
        boolean usernameExist = userRepository.existsByUsername(createUserDTO.username());
        if(usernameExist) throw new DuplicateKeyException("Username already exists! Cannot create user.", createUserDTO.username());
        boolean userEmailExist = userRepository.existsByUserEmail(createUserDTO.userEmail());
        if(userEmailExist) throw new DuplicateKeyException("Email already exists! Cannot create user.", createUserDTO.userEmail());

        User user = new User(userRole);
        user.setUsername(createUserDTO.username());
        user.setUserEmail(createUserDTO.userEmail());
        user.setPassword(passwordEncoder.encode(createUserDTO.password()));
        User createddUser = userRepository.save(user);
        response.setUser(createddUser);
        response.setMessage("User created Successfully");
        response.setStatusCode(200);
        return response;
    }

    public AuthReqRes signIn(SignInUserDTO signInUserDTO){
        AuthReqRes response = new AuthReqRes();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInUserDTO.username(),signInUserDTO.password()));
            User user = findUserByUsername(signInUserDTO.username());

            System.out.println("USER IS: "+ user);
            var jwt = jwtUtils.generateAccessToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setUserId(user.getUserId());
            response.setStatusCode(200);
            response.setAccessToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("6Hr");
            response.setUserRoleName(user.getUserRole().getUserRoleName());
            response.setMessage("Successfully Signed In");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public AuthReqRes refreshToken(AuthReqRes refreshTokenRequest){
        AuthReqRes response = new AuthReqRes();
        String extractUserId = jwtUtils.extractUserId(refreshTokenRequest.getAccessToken());
        User user = findUserById(extractUserId);
        if (jwtUtils.isTokenValid(refreshTokenRequest.getAccessToken(), user)) {
            var jwt = jwtUtils.generateAccessToken(user);
            response.setStatusCode(200);
            response.setAccessToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getAccessToken());
            response.setExpirationTime("6Hr");
            response.setUserRoleName(user.getUserRole().getUserRoleName());
            response.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }
}