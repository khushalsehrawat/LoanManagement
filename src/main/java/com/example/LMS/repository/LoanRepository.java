package com.example.LMS.repository;

import com.example.LMS.enums.LoanStatus;
import com.example.LMS.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan,Long> {
    List<Loan> findByStatus(LoanStatus status);

    Optional<Loan> findByLoanApplicationId(Long applicationId);

}
