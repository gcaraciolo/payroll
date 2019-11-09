package com.gcaraciolo.payroll.domain;

import lombok.Getter;

@Getter
public class SalesReceipt {

    private long date;
    private Double amount;

    public SalesReceipt(long date, Double amount) {
        this.date = date;
        this.amount = amount;
    }
}