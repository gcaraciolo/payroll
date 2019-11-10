package com.gcaraciolo.payroll.domain;

import com.gcaraciolo.common.DatePeriod;

import lombok.Getter;

@Getter
public class SalariedClassification implements PaymentClassification {

    private Double salary;

    public SalariedClassification(Double salary) {
        this.salary = salary;
    }

    @Override
    public Double calculatePay(DatePeriod datePeriod) {
        return salary;
    }
}