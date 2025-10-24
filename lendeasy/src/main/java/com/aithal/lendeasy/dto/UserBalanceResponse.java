package com.aithal.lendeasy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBalanceResponse {
    private Long userId;
    private String firstName;
    private String lastName;
    private BigDecimal balance;
    private String currency = "INR";
    private LocalDateTime lastUpdated;
}
