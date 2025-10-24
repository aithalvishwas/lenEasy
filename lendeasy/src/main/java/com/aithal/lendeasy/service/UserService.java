package com.aithal.lendeasy.service;

import com.aithal.lendeasy.dto.UserBalanceResponse;
import com.aithal.lendeasy.user.User;
import com.aithal.lendeasy.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserBalanceResponse getUserBalance(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        
        User user = userOpt.get();
        return new UserBalanceResponse(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getBalance(),
            "INR",
            LocalDateTime.now()
        );
    }
}
