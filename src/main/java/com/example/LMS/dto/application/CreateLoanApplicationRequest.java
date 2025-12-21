package com.example.LMS.dto.application;

public class CreateLoanApplicationRequest {

    // customer fields
    private String fullName;
    private String email;
    private String phone;
    private String pan;

    // product + amount
    private Long loanProductId;
    private double requestedAmount;

    public CreateLoanApplicationRequest() {}

    public CreateLoanApplicationRequest(String fullName, String email, String phone, String pan, Long loanProductId, double requestedAmount) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.pan = pan;
        this.loanProductId = loanProductId;
        this.requestedAmount = requestedAmount;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public Long getLoanProductId() {
        return loanProductId;
    }

    public void setLoanProductId(Long loanProductId) {
        this.loanProductId = loanProductId;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }
}
