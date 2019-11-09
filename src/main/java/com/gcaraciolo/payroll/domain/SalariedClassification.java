package com.gcaraciolo.payroll.domain;

import lombok.Getter;

@Getter
public class SalariedClassification implements PaymentClassification {

    private Double salary;

    public SalariedClassification(Double salary) {
        this.salary = salary;
    }
}