package com.gcaraciolo.payroll.domain;

import lombok.Getter;

@Getter
public class CommissionedClassification implements PaymentClassification {

    private Double salary;
    private Double commissionRate;

    public CommissionedClassification(Double salary, Double commissionRate) {
        this.salary = salary;
        this.commissionRate = commissionRate;
    }
}