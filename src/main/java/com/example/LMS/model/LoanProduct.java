package com.example.LMS.model;


import jakarta.persistence.*;

@Entity
@Table(name = "loan_products")
public class LoanProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // Example: 14.5 means 14.5% APR
    @Column(nullable = false)
    private double interestRate;

    // Example: 0.5 means 50% LTV
    @Column(nullable = false)
    private double maxLtv;

    @Column(nullable = false)
    private double minAmount;

    @Column(nullable = false)
    private double maxAmount;

    public LoanProduct(){}

    public LoanProduct(Long id, String name, double interestRate, double maxLtv, double minAmount, double maxAmount) {
        this.id = id;
        this.name = name;
        this.interestRate = interestRate;
        this.maxLtv = maxLtv;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public LoanProduct(String name, double interestRate, double maxLtv, double minAmount, double maxAmount) {

        this.name = name;
        this.interestRate = interestRate;
        this.maxLtv = maxLtv;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getMaxLtv() {
        return maxLtv;
    }

    public void setMaxLtv(double maxLtv) {
        this.maxLtv = maxLtv;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }
}
