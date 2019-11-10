package com.gcaraciolo.payroll.domain;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class ServiceCharge {

    private LocalDate date;
    private Double amount;

    public ServiceCharge(LocalDate date, Double amount) {
        this.date = date;
        this.amount = amount;
    }
}