package com.example.LMS.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "repayments")
public class Repayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false, updatable = false)
    private Instant paidAt;

    public Repayment() {}

    public Repayment(Long id, Loan loan, double amount, Instant paidAt) {
        this.id = id;
        this.loan = loan;
        this.amount = amount;
        this.paidAt = paidAt;
    }

    public Repayment(Loan loan, double amount) {
        this.loan = loan;
        this.amount = amount;
    }

    @PrePersist
    public void onPay() {
        if (this.paidAt == null) {
            this.paidAt = Instant.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Instant getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Instant paidAt) {
        this.paidAt = paidAt;
    }
}
