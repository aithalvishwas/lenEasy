package com.aithal.lendeasy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
    @NotBlank String firstName,
    @NotBlank String lastName,
    @NotBlank String phoneNumber,
    @NotBlank @Email String email,
    @NotBlank @Size(min = 8) String password
) {}


