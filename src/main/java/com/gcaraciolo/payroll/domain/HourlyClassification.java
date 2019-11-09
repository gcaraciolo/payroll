package com.gcaraciolo.payroll.domain;

import lombok.Getter;

@Getter
public class HourlyClassification implements PaymentClassification {

    private Double hourlyRate;

    public HourlyClassification(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

}