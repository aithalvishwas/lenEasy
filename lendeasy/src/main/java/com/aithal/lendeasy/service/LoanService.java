package com.aithal.lendeasy.service;

import com.aithal.lendeasy.dto.CreateLoanRequest;
import com.aithal.lendeasy.dto.CreateLoanResponse;
import com.aithal.lendeasy.dto.LoanResponse;
import com.aithal.lendeasy.dto.UserLoansResponse;
import com.aithal.lendeasy.entity.Loan;
import com.aithal.lendeasy.repository.LoanRepository;
import com.aithal.lendeasy.user.User;
import com.aithal.lendeasy.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

    public UserLoansResponse getUserLoans(Long userId) {
        // Check if user exists
        if (userRepository.findById(userId).isEmpty()) {
            throw new RuntimeException("User not found");
        }

        List<Loan> loans = loanRepository.findByUserId(userId);
        if (loans.isEmpty()) {
            throw new RuntimeException("No loan records found for this user");
        }

        List<LoanResponse> loanResponses = loans.stream()
            .map(LoanResponse::fromLoan)
            .toList();

        return new UserLoansResponse(userId, loans.size(), loanResponses);
    }

    @Transactional
    public CreateLoanResponse createLoan(CreateLoanRequest request) {
        // 1. Verify the user exists
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        
        User user = userOpt.get();
        
        // 2. Check if user's current balance >= disbursedAmount
        if (user.getBalance().compareTo(request.getDisbursedAmount()) < 0) {
            throw new RuntimeException("Insufficient balance to disburse loan");
        }
        
        // 3. Deduct the disbursedAmount from user's balance
        BigDecimal newBalance = user.getBalance().subtract(request.getDisbursedAmount());
        user.setBalance(newBalance);
        userRepository.save(user);
        
        // 4. Create new loan entry
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setClientName(request.getClientName());
        loan.setLoanAmount(request.getLoanAmount());
        loan.setDisbursedAmount(request.getDisbursedAmount());
        loan.setRepaymentType(Loan.RepaymentType.valueOf(request.getRepaymentType()));
        loan.setRepaymentAmount(request.getRepaymentAmount());
        loan.setRepaymentDays(request.getRepaymentDays());
        loan.setRemainingBalance(request.getLoanAmount());
        loan.setStartDate(LocalDate.now());
        loan.setEndDate(LocalDate.now().plusDays(request.getRepaymentDays()));
        loan.setStatus(Loan.LoanStatus.ONGOING);
        
        Loan savedLoan = loanRepository.save(loan);
        
        // 5. Return success response
        return new CreateLoanResponse(
            savedLoan.getId(),
            savedLoan.getClientName(),
            savedLoan.getLoanAmount(),
            savedLoan.getDisbursedAmount(),
            savedLoan.getRepaymentType().name(),
            savedLoan.getRepaymentAmount(),
            savedLoan.getRepaymentDays(),
            savedLoan.getRemainingBalance(),
            savedLoan.getStartDate(),
            savedLoan.getEndDate(),
            savedLoan.getStatus().name(),
            newBalance,
            "Loan created successfully"
        );
    }
}
