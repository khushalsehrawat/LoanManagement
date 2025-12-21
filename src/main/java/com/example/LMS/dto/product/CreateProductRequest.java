package com.example.LMS.dto.product;

public class CreateProductRequest {

    private String name;
    private double interestRate;
    private double maxLtv;
    private double minAmount;
    private double maxAmount;

    public CreateProductRequest() {}

    public CreateProductRequest(String name, double interestRate, double maxLtv, double minAmount, double maxAmount) {
        this.name = name;
        this.interestRate = interestRate;
        this.maxLtv = maxLtv;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
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
