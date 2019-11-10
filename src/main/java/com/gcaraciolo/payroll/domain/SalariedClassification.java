package com.gcaraciolo.payroll.domain;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class SalariedClassification implements PaymentClassification {

    private Double salary;

    public SalariedClassification(Double salary) {
        this.salary = salary;
    }

    @Override
    public Double calculatePay(LocalDate payDate) {
        return salary;
    }
}