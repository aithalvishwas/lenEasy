package com.aithal.lendeasy.service;

import com.aithal.lendeasy.dto.LoanResponse;
import com.aithal.lendeasy.dto.UserLoansResponse;
import com.aithal.lendeasy.entity.Loan;
import com.aithal.lendeasy.repository.LoanRepository;
import com.aithal.lendeasy.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
