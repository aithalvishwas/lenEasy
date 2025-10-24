package com.aithal.lendeasy.dto;

import com.aithal.lendeasy.entity.Loan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanResponse {
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

    public static LoanResponse fromLoan(Loan loan) {
        return new LoanResponse(
            loan.getId(),
            loan.getClientName(),
            loan.getLoanAmount(),
            loan.getDisbursedAmount(),
            loan.getRepaymentType().name(),
            loan.getRepaymentAmount(),
            loan.getRepaymentDays(),
            loan.getRemainingBalance(),
            loan.getStartDate(),
            loan.getEndDate(),
            loan.getStatus().name()
        );
    }
}
