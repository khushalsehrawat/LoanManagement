package com.example.LMS.repository;

import com.example.LMS.model.Collateral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollateralRepository extends JpaRepository<Collateral, Long> {
    List<Collateral> findByLoanApplicationId(Long loanApplicationId);
}
