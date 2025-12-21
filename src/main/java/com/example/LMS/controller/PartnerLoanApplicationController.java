package com.example.LMS.controller;


import com.example.LMS.dto.application.CreateLoanApplicationRequest;
import com.example.LMS.model.LoanApplication;
import com.example.LMS.service.LoanApplicationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partner/v1")
public class PartnerLoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    public PartnerLoanApplicationController(LoanApplicationService loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    // API Key enforced by ApiKeyAuthFilter (X-API-KEY)
    @PostMapping("/loan-applications")
    public LoanApplication createFromPartner(@RequestBody CreateLoanApplicationRequest req) {
        return loanApplicationService.create(req);
    }

}
