package com.example.LMS.repository;

import com.example.LMS.enums.LoanApplicationStatus;
import com.example.LMS.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    List<LoanApplication> findByStatus(LoanApplicationStatus status);
}
