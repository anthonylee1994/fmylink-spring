package com.spring.fmylinkspring.profile.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * @author anthonylee
 */
public class CreateProfileDto {
    @NotBlank
    public String username;
}
