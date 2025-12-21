package com.example.LMS.service;


import com.example.LMS.dto.application.AddCollateralRequest;
import com.example.LMS.dto.application.CreateLoanApplicationRequest;
import com.example.LMS.enums.CollateralStatus;
import com.example.LMS.enums.LoanApplicationStatus;
import com.example.LMS.exception.BadRequestException;
import com.example.LMS.exception.NotFoundException;
import com.example.LMS.model.*;
import com.example.LMS.repository.CollateralRepository;
import com.example.LMS.repository.CustomerRepository;
import com.example.LMS.repository.LoanApplicationRepository;
import com.example.LMS.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoanApplicationService {

    private final CustomerRepository customerRepository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final CollateralRepository collateralRepository;
    private final LoanRepository loanRepository;
    private final ProductService productService;
    private final CalculationService calculationService;

    public LoanApplicationService(CustomerRepository customerRepository, LoanApplicationRepository loanApplicationRepository, CollateralRepository collateralRepository, LoanRepository loanRepository, ProductService productService, CalculationService calculationService) {
        this.customerRepository = customerRepository;
        this.loanApplicationRepository = loanApplicationRepository;
        this.collateralRepository = collateralRepository;
        this.loanRepository = loanRepository;
        this.productService = productService;
        this.calculationService = calculationService;
    }

    @Transactional
    public LoanApplication create(CreateLoanApplicationRequest req) {
        if (req.getRequestedAmount() <= 0) {
            throw new BadRequestException("requestedAmount must be > 0");
        }

        LoanProduct product = productService.getById(req.getLoanProductId());

        Customer customer = customerRepository.findByPan(req.getPan())
                .orElseGet(() -> customerRepository.save(
                        new Customer(req.getFullName(), req.getEmail(), req.getPhone(), req.getPan())
                ));

        LoanApplication app = new LoanApplication(customer, product, req.getRequestedAmount());
        return loanApplicationRepository.save(app);
    }

    @Transactional
    public Collateral addCollateral(Long applicationId, AddCollateralRequest req) {
        LoanApplication app = getById(applicationId);

        if (req.getUnits() <= 0 || req.getNav() <= 0) {
            throw new BadRequestException("units and nav must be > 0");
        }

        Collateral c = new Collateral(req.getSchemeName(), req.getUnits(), req.getNav());
        c.setLoanApplication(app);
        c.setStatus(CollateralStatus.UNPLEDGED);
        Collateral saved = collateralRepository.save(c);

        recalcEligibleLimit(app.getId());
        return saved;
    }

    @Transactional
    public LoanApplication submit(Long applicationId) {
        LoanApplication app = getById(applicationId);

        List<Collateral> collaterals = collateralRepository.findByLoanApplicationId(applicationId);
        if (collaterals.isEmpty()) {
            throw new BadRequestException("Add at least 1 collateral before submit");
        }

        app.setStatus(LoanApplicationStatus.SUBMITTED);
        return loanApplicationRepository.save(app);
    }

    /*@Transactional
    public Loan approveAndDisburse(Long applicationId) {
        LoanApplication app = getById(applicationId);

        if (app.getStatus() != LoanApplicationStatus.SUBMITTED) {
            throw new BadRequestException("Only SUBMITTED applications can be approved");
        }

        // Pledge all collaterals
        List<Collateral> collaterals = collateralRepository.findByLoanApplicationId(applicationId);
        for (Collateral c : collaterals) {
            c.setStatus(CollateralStatus.PLEDGED);
        }
        collateralRepository.saveAll(collaterals);

        // Create loan using eligibleLimit (cap by requested amount if you want strictness)
        double sanctioned = Math.min(app.getEligibleLimit(), app.getRequestedAmount());
        if (sanctioned <= 0) {
            throw new BadRequestException("Eligible limit is 0, cannot disburse");
        }

        app.setStatus(LoanApplicationStatus.ACTIVE);
        loanApplicationRepository.save(app);

        Loan loan = new Loan(app, sanctioned);
        return loanRepository.save(loan);
    }
*/


    @Transactional
    public Loan approveAndDisburse(Long applicationId) {
        LoanApplication app = getById(applicationId);

        if (app.getStatus() != LoanApplicationStatus.SUBMITTED) {
            throw new BadRequestException("Only SUBMITTED applications can be approved");
        }

        // ✅ Idempotency: if already disbursed, return existing loan
        return loanRepository.findByLoanApplicationId(applicationId)
                .orElseGet(() -> {
                    // Pledge all collaterals
                    List<Collateral> collaterals = collateralRepository.findByLoanApplicationId(applicationId);
                    for (Collateral c : collaterals) {
                        c.setStatus(CollateralStatus.PLEDGED);
                    }
                    collateralRepository.saveAll(collaterals);

                    double sanctioned = Math.min(app.getEligibleLimit(), app.getRequestedAmount());
                    if (sanctioned <= 0) {
                        throw new BadRequestException("Eligible limit is 0, cannot disburse");
                    }

                    app.setStatus(LoanApplicationStatus.ACTIVE);
                    loanApplicationRepository.save(app);

                    Loan loan = new Loan(app, sanctioned);
                    return loanRepository.save(loan);
                });
    }


    public List<LoanApplication> listAll() {
        return loanApplicationRepository.findAll();
    }

    public LoanApplication getById(Long id) {
        return loanApplicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("LoanApplication not found: " + id));
    }

    @Transactional
    public void recalcEligibleLimit(Long applicationId) {
        LoanApplication app = getById(applicationId);
        List<Collateral> collaterals = collateralRepository.findByLoanApplicationId(applicationId);
        double total = calculationService.totalCollateralValue(collaterals);
        double eligible = calculationService.eligibleLimit(total, app.getLoanProduct().getMaxLtv());
        app.setEligibleLimit(eligible);
        loanApplicationRepository.save(app);
    }

}
