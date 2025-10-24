package com.aithal.lendeasy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoansResponse {
    private Long userId;
    private Integer totalLoans;
    private List<LoanResponse> loans;
}
