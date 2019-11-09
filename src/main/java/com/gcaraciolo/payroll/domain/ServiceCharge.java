package com.gcaraciolo.payroll.domain;

import lombok.Getter;

@Getter
public class ServiceCharge {

    private long date;
    private Double amount;

    public ServiceCharge(long date, Double amount) {
        this.date = date;
        this.amount = amount;
    }
}