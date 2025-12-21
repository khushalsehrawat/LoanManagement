package com.example.LMS.model;

import com.example.LMS.enums.CollateralStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "collaterals")
public class Collateral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_application_id", nullable = false)
    private LoanApplication loanApplication;


    @Column(nullable = false)
    private String schemeName;

    @Column(nullable = false)
    private double units;

    @Column(nullable = false)
    private double nav;

    @Column(nullable = false)
    private double collateralValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CollateralStatus status;

    @PrePersist
    @PreUpdate
    public void computeCollateralValue() {
        this.collateralValue = this.units * this.nav;
    }

    @Transient
    public Long getApplicationId() {
        return loanApplication != null ? loanApplication.getId() : null;
    }


    public Collateral() {}

    public Collateral(Long id, LoanApplication loanApplication, String schemeName, double units, double nav, double collateralValue, CollateralStatus status) {
        this.id = id;
        this.loanApplication = loanApplication;
        this.schemeName = schemeName;
        this.units = units;
        this.nav = nav;
        this.collateralValue = collateralValue;
        this.status = status;
    }

    public Collateral(String schemeName, double units, double nav) {
        this.schemeName = schemeName;
        this.units = units;
        this.nav = nav;
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

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }

    public double getNav() {
        return nav;
    }

    public void setNav(double nav) {
        this.nav = nav;
    }

    public double getCollateralValue() {
        return collateralValue;
    }

    public void setCollateralValue(double collateralValue) {
        this.collateralValue = collateralValue;
    }

    public CollateralStatus getStatus() {
        return status;
    }

    public void setStatus(CollateralStatus status) {
        this.status = status;
    }
}
