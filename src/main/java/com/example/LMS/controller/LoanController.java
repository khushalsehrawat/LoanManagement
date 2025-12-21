package com.example.LMS.controller;


import com.example.LMS.dto.loan.CreateRepaymentRequest;
import com.example.LMS.model.Loan;
import com.example.LMS.model.Repayment;
import com.example.LMS.service.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }
    @GetMapping("/active")
    public List<Loan> listActive() {
        return loanService.listActive();
    }

    @GetMapping("/{loanId}/repayments")
    public List<Repayment> repayments(@PathVariable Long loanId) {
        return loanService.listRepayments(loanId);
    }

    @PostMapping("/{loanId}/repay")
    public Loan repay(@PathVariable Long loanId, @RequestBody CreateRepaymentRequest req) {
        return loanService.repay(loanId, req);
    }
}
