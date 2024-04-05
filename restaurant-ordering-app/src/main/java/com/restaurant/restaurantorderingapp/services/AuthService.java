package com.restaurant.restaurantorderingapp.services;

import com.restaurant.restaurantorderingapp.dto.ReqRes;
import com.restaurant.restaurantorderingapp.dto.usersDto.CreateUserDTO;
import com.restaurant.restaurantorderingapp.dto.usersDto.SignInUserDTO;
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

    public ReqRes signUp(CreateUserDTO createUserDTO){
        ReqRes resp = new ReqRes();

        try {
           UserRole userRole = findUserRoleByName(createUserDTO.userRoleName());
            User user = new User(userRole);
            user.setUsername(createUserDTO.username());
            user.setUserEmail(createUserDTO.userEmail());
            user.setPassword(passwordEncoder.encode(createUserDTO.userPassword()));
            User createddUser = userRepository.save(user);

            if (createddUser != null) {
                resp.setUser(createddUser);
                resp.setMessage("User created Successfully");
                resp.setStatusCode(200);
            }
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public ReqRes signIn(SignInUserDTO signInUserDTO){
        ReqRes response = new ReqRes();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInUserDTO.username(),signInUserDTO.password()));
            User user = findUserByUsername(signInUserDTO.username());
            System.out.println("USER IS: "+ user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setRole(user.getUserRole().getUserRoleName());
            response.setMessage("Successfully Signed In");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenRequest){
        ReqRes response = new ReqRes();
        String extractUsername = jwtUtils.extractUsername(refreshTokenRequest.getToken());
        User user = findUserByUsername(extractUsername);
        if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtUtils.generateToken(user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            response.setExpirationTime("24Hr");
            response.setRole(user.getUserRole().getUserRoleName());
            response.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }
}