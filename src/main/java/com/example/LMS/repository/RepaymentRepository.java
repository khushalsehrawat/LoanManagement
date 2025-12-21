package com.example.LMS.repository;

import com.example.LMS.model.Loan;
import com.example.LMS.model.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
    List<Repayment> findByLoanId(Long loanId);

}
