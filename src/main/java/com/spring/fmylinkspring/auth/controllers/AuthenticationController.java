package com.spring.fmylinkspring.auth.controllers;

import com.spring.fmylinkspring.auth.dto.LoginUserDto;
import com.spring.fmylinkspring.auth.dto.RegisterUserDto;
import com.spring.fmylinkspring.auth.entities.User;
import com.spring.fmylinkspring.auth.responses.LoginResponse;
import com.spring.fmylinkspring.auth.responses.RegisterResponse;
import com.spring.fmylinkspring.auth.services.AuthenticationService;
import com.spring.fmylinkspring.auth.services.JwtService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anthonylee
 */
@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterUserDto registerUserDto) throws BadRequestException {
        User registeredUser = authenticationService.signup(registerUserDto);
        String jwtToken = jwtService.generateToken(registeredUser);

        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.token = jwtToken;
        registerResponse.expiresIn = jwtService.getExpirationTime();

        return ResponseEntity.ok(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.token = jwtToken;
        loginResponse.expiresIn = jwtService.getExpirationTime();

        return ResponseEntity.ok(loginResponse);
    }
}