package com.aithal.lendeasy.controller;

import com.aithal.lendeasy.dto.UserBalanceResponse;
import com.aithal.lendeasy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}/balance")
    public ResponseEntity<?> getUserBalance(@PathVariable Long userId) {
        try {
            UserBalanceResponse response = userService.getUserBalance(userId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "User not found"));
        }
    }
}
