package com.spring.fmylinkspring.auth.controllers;

import com.spring.fmylinkspring.auth.entities.User;
import com.spring.fmylinkspring.auth.responses.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anthonylee
 */
@RequestMapping("/users")
@RestController
public class UsersController {
    @GetMapping("/me")
    public ResponseEntity<UserResponse> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        var response = new UserResponse();
        response.id = currentUser.id;
        response.email = currentUser.email;
        response.createdAt = currentUser.createdAt;
        response.updatedAt = currentUser.updatedAt;

        return ResponseEntity.ok(response);
    }
}
