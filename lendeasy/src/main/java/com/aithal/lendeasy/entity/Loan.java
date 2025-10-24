package com.aithal.lendeasy.entity;

import com.aithal.lendeasy.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal loanAmount;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal disbursedAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RepaymentType repaymentType;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal repaymentAmount;

    @Column(nullable = false)
    private Integer repaymentDays;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal remainingBalance;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;

    public enum RepaymentType {
        DAILY, WEEKLY
    }

    public enum LoanStatus {
        ONGOING, COMPLETED, DEFAULTED
    }
}
