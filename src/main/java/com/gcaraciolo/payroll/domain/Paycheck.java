package com.gcaraciolo.payroll.domain;

import java.text.DecimalFormat;
import java.time.LocalDate;

import lombok.Getter;

@Getter
public class Paycheck {

    private LocalDate payDate;
    private Double grossPay;
    private Double deductions;
    private Double netPay;

    public Paycheck(LocalDate payDate, Double grossPay, Double deductions, Double netPay) {
        this.payDate = payDate;
        this.grossPay = grossPay;
        this.deductions = deductions;
        this.netPay = netPay;
    }

    public Double getNetPay() {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(netPay));
    }
}