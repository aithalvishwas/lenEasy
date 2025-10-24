package com.aithal.lendeasy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLoanResponse {
    private Long loanId;
    private String clientName;
    private BigDecimal loanAmount;
    private BigDecimal disbursedAmount;
    private String repaymentType;
    private BigDecimal repaymentAmount;
    private Integer repaymentDays;
    private BigDecimal remainingBalance;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private BigDecimal updatedUserBalance;
    private String message;
}
