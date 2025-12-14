package com.aithal.lendeasy.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLoanRequest {
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotBlank(message = "Client name is required")
    private String clientName;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Loan amount must be greater than 0")
    private BigDecimal loanAmount;
    
    @NotNull(message = "Disbursed amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Disbursed amount must be greater than 0")
    private BigDecimal disbursedAmount;
    
    @NotBlank(message = "Repayment type is required")
    private String repaymentType;
    
    @NotNull(message = "Repayment amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Repayment amount must be greater than 0")
    private BigDecimal repaymentAmount;
    
    @NotNull(message = "Repayment days is required")
    @Positive(message = "Repayment days must be positive")
    private Integer repaymentDays;
}
