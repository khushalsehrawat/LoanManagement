package com.example.LMS.dto.application;

public class AddCollateralRequest {

    private String schemeName;
    private double units;
    private double nav;

    public AddCollateralRequest() {}

    public AddCollateralRequest(String schemeName, double units, double nav) {
        this.schemeName = schemeName;
        this.units = units;
        this.nav = nav;
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
}
