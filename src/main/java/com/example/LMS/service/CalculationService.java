package com.example.LMS.service;


import com.example.LMS.model.Collateral;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculationService {

    public double totalCollateralValue(List<Collateral> collaterals) {
        double sum = 0.0;
        for (Collateral c : collaterals) {
            sum += c.getCollateralValue();
        }
        return sum;
    }

    public double eligibleLimit(double totalCollateralValue, double maxLtv) {
        return totalCollateralValue * maxLtv;
    }

}
