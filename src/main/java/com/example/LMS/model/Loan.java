package com.example.LMS.model;

import com.example.LMS.enums.LoanStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "loan_application_id", nullable = false, unique = true)
    private LoanApplication loanApplication;

    @Column(nullable = false)
    private double sanctionedLimit;

    @Column(nullable = false)
    private double outstandingAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Loan() {}

    public Loan(Long id, LoanApplication loanApplication, double sanctionedLimit,
                double outstandingAmount, LoanStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.loanApplication = loanApplication;
        this.sanctionedLimit = sanctionedLimit;
        this.outstandingAmount = outstandingAmount;
        this.status = status;
        this.createdAt = createdAt;
    }

    // ✅ This is the constructor used by approveAndDisburse().
    // It MUST set NOT NULL defaults.
    public Loan(LoanApplication loanApplication, double sanctionedLimit) {
        this.loanApplication = loanApplication;
        this.sanctionedLimit = sanctionedLimit;

        // ✅ required defaults
        this.outstandingAmount = sanctionedLimit;
        this.status = LoanStatus.ACTIVE; // use whatever "active/open" status exists in your enum
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }

    public double getSanctionedLimit() {
        return sanctionedLimit;
    }

    public void setSanctionedLimit(double sanctionedLimit) {
        this.sanctionedLimit = sanctionedLimit;
    }

    public double getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(double outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
