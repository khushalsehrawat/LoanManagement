package com.example.LMS.service;


import com.example.LMS.dto.loan.CreateRepaymentRequest;
import com.example.LMS.enums.LoanStatus;
import com.example.LMS.exception.BadRequestException;
import com.example.LMS.exception.NotFoundException;
import com.example.LMS.model.Loan;
import com.example.LMS.model.Repayment;
import com.example.LMS.repository.LoanRepository;
import com.example.LMS.repository.RepaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final RepaymentRepository repaymentRepository;

    public LoanService(LoanRepository loanRepository, RepaymentRepository repaymentRepository) {
        this.loanRepository = loanRepository;
        this.repaymentRepository = repaymentRepository;
    }

    public List<Loan> listActive() {
        return loanRepository.findByStatus(LoanStatus.ACTIVE);
    }

    public Loan getById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Loan not found: " + id));
    }

    public List<Repayment> listRepayments(Long loanId) {
        return repaymentRepository.findByLoanId(loanId);
    }

    @Transactional
    public Loan repay(Long loanId, CreateRepaymentRequest req) {
        Loan loan = getById(loanId);

        if (loan.getStatus() != LoanStatus.ACTIVE) {
            throw new BadRequestException("Loan is not ACTIVE");
        }
        if (req.getAmount() <= 0) {
            throw new BadRequestException("Repayment amount must be > 0");
        }
        if (req.getAmount() > loan.getOutstandingAmount()) {
            throw new BadRequestException("Repayment cannot exceed outstandingAmount");
        }

        Repayment repayment = new Repayment(loan, req.getAmount());
        repaymentRepository.save(repayment);

        loan.setOutstandingAmount(loan.getOutstandingAmount() - req.getAmount());
        if (loan.getOutstandingAmount() == 0.0) {
            loan.setStatus(LoanStatus.CLOSED);
        }
        return loanRepository.save(loan);
    }

}
