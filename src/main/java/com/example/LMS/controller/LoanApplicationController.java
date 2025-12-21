package com.example.LMS.controller;


import com.example.LMS.dto.application.AddCollateralRequest;
import com.example.LMS.dto.application.CreateLoanApplicationRequest;
import com.example.LMS.model.Collateral;
import com.example.LMS.model.Loan;
import com.example.LMS.model.LoanApplication;
import com.example.LMS.service.LoanApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    public LoanApplicationController(LoanApplicationService loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }


    @PostMapping
    public LoanApplication create(@RequestBody CreateLoanApplicationRequest req) {
        return loanApplicationService.create(req);
    }

    @GetMapping
    public List<LoanApplication> listAll() {
        return loanApplicationService.listAll();
    }

    @PostMapping("/{id}/collateral")
    public Collateral addCollateral(@PathVariable Long id, @RequestBody AddCollateralRequest req) {
        return loanApplicationService.addCollateral(id, req);
    }

    @PostMapping("/{id}/submit")
    public LoanApplication submit(@PathVariable Long id) {
        return loanApplicationService.submit(id);
    }

    @PostMapping("/{id}/approve-disburse")
    public Loan approveAndDisburse(@PathVariable Long id) {
        return loanApplicationService.approveAndDisburse(id);
    }

}
