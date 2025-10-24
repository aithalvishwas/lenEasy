package com.aithal.lendeasy.repository;

import com.aithal.lendeasy.entity.Loan;
import com.aithal.lendeasy.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUser(User user);
    List<Loan> findByUserId(Long userId);
}
