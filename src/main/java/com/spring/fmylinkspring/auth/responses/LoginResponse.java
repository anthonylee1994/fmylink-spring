package com.spring.fmylinkspring.auth.responses;

import jakarta.validation.constraints.NotBlank;

/**
 * @author anthonylee
 */
public class LoginResponse {
    @NotBlank
    public String token;
    @NotBlank
    public Long expiresIn;
}
