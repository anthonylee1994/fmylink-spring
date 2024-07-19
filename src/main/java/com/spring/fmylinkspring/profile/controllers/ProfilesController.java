package com.spring.fmylinkspring.profile.controllers;

import com.spring.fmylinkspring.auth.entities.User;
import com.spring.fmylinkspring.profile.dto.CreateProfileDto;
import com.spring.fmylinkspring.profile.dto.UpdateProfileDto;
import com.spring.fmylinkspring.profile.entities.Profile;
import com.spring.fmylinkspring.profile.repositories.ProfileRepository;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author anthonylee
 */
@RestController
public class ProfilesController {

    private final ProfileRepository profileRepository;

    ProfilesController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @PostMapping("/profile")
    public ResponseEntity<Profile> createProfile(@Valid @RequestBody CreateProfileDto profileDto) throws BadRequestException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        if (profileRepository.existsByUserId(currentUser.id)) throw new BadRequestException("Profile already exists");

        var profile = new Profile();
        profile.username = profileDto.username;
        profile.userId = currentUser.id;
        profileRepository.save(profile);

        return ResponseEntity.created(null).body(profile);
    }

    @GetMapping("/profile")
    public ResponseEntity<Profile> getProfile(@AuthenticationPrincipal User currentUser) throws BadRequestException {
        Profile profile = profileRepository.findByUserId(currentUser.id).orElseThrow(() -> new BadRequestException("Profile not found"));

        return ResponseEntity.ok(profile);
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity<Profile> getProfileByUsername(@PathVariable String username) throws BadRequestException {
        Profile profile = profileRepository.findByUsername(username).orElseThrow(() -> new BadRequestException("Profile not found"));
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<Profile> updateProfile(@AuthenticationPrincipal User currentUser, @Valid @RequestBody UpdateProfileDto profileDto) throws BadRequestException {
        Profile profile = profileRepository.findByUserId(currentUser.id).orElseThrow(() -> new BadRequestException("Profile not found"));

        if (profileDto.username != null) profile.username = profileDto.username;
        if (profileDto.avatarUrl != null) profile.avatarUrl = profileDto.avatarUrl;

        profileRepository.save(profile);

        return ResponseEntity.ok(profile);
    }

    @DeleteMapping("/profile")
    public ResponseEntity<Void> deleteProfile(@AuthenticationPrincipal User currentUser) {
        Profile profile = profileRepository.findByUserId(currentUser.id).orElseThrow();

        profileRepository.delete(profile);

        return ResponseEntity.noContent().build();
    }
}
