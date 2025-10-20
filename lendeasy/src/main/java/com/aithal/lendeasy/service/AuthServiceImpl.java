package com.aithal.lendeasy.service;

import com.aithal.lendeasy.dto.SignUpRequest;
import com.aithal.lendeasy.exception.UserAlreadyExistsException;
import com.aithal.lendeasy.user.User;
import com.aithal.lendeasy.user.UserRepository;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public com.aithal.lendeasy.user.User registerUser(SignUpRequest signUpRequest) {
        Optional<User> existing = userRepository.findByEmail(signUpRequest.email());
        if (existing.isPresent()) {
            throw new UserAlreadyExistsException("Email address is already in use.");
        }

        String hashedPassword = passwordEncoder.encode(signUpRequest.password());

        User user = new User();
        user.setFirstName(signUpRequest.firstName());
        user.setLastName(signUpRequest.lastName());
        user.setPhoneNumber(signUpRequest.phoneNumber());
        user.setEmail(signUpRequest.email());
        user.setPassword(hashedPassword);

        User saved = userRepository.save(user);
        return saved;
    }
}


