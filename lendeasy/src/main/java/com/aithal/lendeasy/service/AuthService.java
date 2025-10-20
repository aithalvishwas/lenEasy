package com.aithal.lendeasy.service;

import com.aithal.lendeasy.dto.SignUpRequest;

public interface AuthService {
    com.aithal.lendeasy.user.User registerUser(SignUpRequest signUpRequest);
}


