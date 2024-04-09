package com.restaurant.restaurantorderingapp.controllers.authControllers;

import com.restaurant.restaurantorderingapp.dto.AuthReqRes;
import com.restaurant.restaurantorderingapp.dto.usersDto.CreateUserDTO;
import com.restaurant.restaurantorderingapp.dto.usersDto.SignInUserDTO;
import com.restaurant.restaurantorderingapp.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/public/signIn")
    public ResponseEntity<AuthReqRes> signIn(@RequestBody SignInUserDTO signInUserDTO) {
        AuthReqRes res = authService.signIn(signInUserDTO);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/public/signup")
    public ResponseEntity<AuthReqRes> signUp(@RequestBody CreateUserDTO createUserDTO) {
        AuthReqRes res = authService.signUp(createUserDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(res);
    }

    @PostMapping("/authUsers/refreshToken")
    public ResponseEntity<AuthReqRes> refreshToken(@RequestBody AuthReqRes refreshTokenReq) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenReq));
    }
}
