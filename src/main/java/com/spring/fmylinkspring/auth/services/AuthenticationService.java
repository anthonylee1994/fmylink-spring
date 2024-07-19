package com.spring.fmylinkspring.auth.services;

import com.spring.fmylinkspring.auth.dto.LoginUserDto;
import com.spring.fmylinkspring.auth.dto.RegisterUserDto;
import com.spring.fmylinkspring.auth.entities.User;
import com.spring.fmylinkspring.auth.repositories.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author anthonylee
 */
@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) throws BadRequestException {
        User existingUser = userRepository.findByEmail(input.email).orElse(null);

        if (existingUser != null) {
            throw new BadRequestException("User already exists");
        }

        User user = new User();
        user.email = input.email;
        user.passwordDigest = passwordEncoder.encode(input.password);

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.email,
                        input.password
                )
        );

        return userRepository.findByEmail(input.email)
                .orElseThrow();
    }
}
