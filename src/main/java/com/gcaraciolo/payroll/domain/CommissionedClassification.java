package com.gcaraciolo.payroll.domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class CommissionedClassification implements PaymentClassification {

    private Double salary;
    private Double commissionRate;
    private Map<String, SalesReceipt> salesreceipts = new HashMap<String, SalesReceipt>();

    public CommissionedClassification(Double salary, Double commissionRate) {
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    public SalesReceipt addSalesReceipt(LocalDate date, Double amount) {
        var sr = new SalesReceipt(date, amount);
        salesreceipts.put(date.toString(), sr);
        return sr;
    }

    public SalesReceipt getSalesReceipt(LocalDate date) {
        return salesreceipts.get(date.toString());
    }
}