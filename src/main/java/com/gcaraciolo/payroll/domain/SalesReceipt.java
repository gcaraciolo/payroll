package com.gcaraciolo.payroll.domain;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class SalesReceipt {

    private LocalDate date;
    private Double amount;

    public SalesReceipt(LocalDate date, Double amount) {
        this.date = date;
        this.amount = amount;
    }

    public boolean isInPayPeriod(LocalDate payDate) {
        int endDate = payDate.getDayOfMonth();
        int startDate = payDate.getDayOfMonth() - 15;
        return (date.getDayOfMonth() >= startDate) && (date.getDayOfMonth() < endDate);
    }
}