package com.example.LMS.model;

import com.example.LMS.enums.LoanApplicationStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "loan_applications")
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Applicant
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // Product chosen
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "loan_product_id", nullable = false)
    private LoanProduct loanProduct;

    @Column(nullable = false)
    private double requestedAmount;

    // Derived from collateral value * maxLtv
    @Column(nullable = false)
    private double eligibleLimit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanApplicationStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "loanApplication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Collateral> collaterals = new ArrayList<>();

    public LoanApplication() {}

    // Full constructor (keep if you really need it)
    public LoanApplication(Long id,
                           Customer customer,
                           LoanProduct loanProduct,
                           double requestedAmount,
                           double eligibleLimit,
                           LoanApplicationStatus status,
                           LocalDateTime createdAt,
                           List<Collateral> collaterals) {
        this.id = id;
        this.customer = customer;
        this.loanProduct = loanProduct;
        this.requestedAmount = requestedAmount;
        this.eligibleLimit = eligibleLimit;
        this.status = status;
        this.createdAt = createdAt;
        this.collaterals = (collaterals != null) ? collaterals : new ArrayList<>();
    }

    // ✅ This is the constructor you are using in service. It MUST set NOT NULL defaults.
    public LoanApplication(Customer customer, LoanProduct loanProduct, double requestedAmount) {
        this.customer = customer;
        this.loanProduct = loanProduct;
        this.requestedAmount = requestedAmount;

        // ✅ defaults to satisfy NOT NULL constraints
        this.eligibleLimit = 0.0;
        this.status = LoanApplicationStatus.DRAFT;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LoanProduct getLoanProduct() {
        return loanProduct;
    }

    public void setLoanProduct(LoanProduct loanProduct) {
        this.loanProduct = loanProduct;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public double getEligibleLimit() {
        return eligibleLimit;
    }

    public void setEligibleLimit(double eligibleLimit) {
        this.eligibleLimit = eligibleLimit;
    }

    public LoanApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(LoanApplicationStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Collateral> getCollaterals() {
        return collaterals;
    }

    public void setCollaterals(List<Collateral> collaterals) {
        this.collaterals = (collaterals != null) ? collaterals : new ArrayList<>();
    }
}
