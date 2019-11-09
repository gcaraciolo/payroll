package com.gcaraciolo.payroll.domain;

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

    public SalesReceipt addSalesReceipt(long date, Double amount) {
        var sr = new SalesReceipt(date, amount);
        salesreceipts.put(Long.toString(date), sr);
        return sr;
    }

    public SalesReceipt getSalesReceipt(long date) {
        return salesreceipts.get(Long.toString(date));
    }
}