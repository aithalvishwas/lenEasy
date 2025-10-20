package com.aithal.lendeasy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanCreationDto {
    private Long userId; // ID of the lender creating the loan
    private String clientName; // Name of the person receiving the loan
    private double principalAmount; // Total amount to be repaid
    private double amountDisbursed; // Actual amount given to the client
    private String repaymentFrequency; // "DAILY" or "WEEKLY"
    private int tenureInDays; // Total duration of the loan in days
}
