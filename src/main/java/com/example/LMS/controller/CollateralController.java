package com.example.LMS.controller;


import com.example.LMS.model.Collateral;
import com.example.LMS.repository.CollateralRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/collateral")
public class CollateralController {

    private final CollateralRepository collateralRepository;

    public CollateralController(CollateralRepository collateralRepository) {
        this.collateralRepository = collateralRepository;
    }

    @GetMapping("/by-application/{applicationId}")
    public List<Collateral> byApplication(@PathVariable Long applicationId) {
        return collateralRepository.findByLoanApplicationId(applicationId);
    }

    @GetMapping
    public List<Collateral> all() {
        return collateralRepository.findAll();
    }

}
