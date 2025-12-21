package com.example.LMS.dto.loan;

public class CreateRepaymentRequest {

    private double amount;

    public CreateRepaymentRequest(){}

    public CreateRepaymentRequest(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
