package com.spring.fmylinkspring.auth.responses;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

/**
 * @author anthonylee
 */
public class UserResponse {
    @NotBlank
    public Integer id;
    @NotBlank
    public String email;
    @NotBlank
    public Date createdAt;
    @NotBlank
    public Date updatedAt;
}
