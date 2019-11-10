package com.gcaraciolo.payroll.domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class HourlyClassification implements PaymentClassification {

    private Double hourlyRate;
    private Map<String, TimeCard> timecards = new HashMap<String, TimeCard>();

    public HourlyClassification(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public TimeCard addTimeCard(LocalDate date, double hours) {
        var tc = new TimeCard(date, hours);
        timecards.put(date.toString(), tc);
        return tc;
    }

    public TimeCard getTimeCard(LocalDate date) {
        return timecards.get(date.toString());
    }

    @Override
    public Double calculatePay(LocalDate payDate) {
        return 1000.00;
    }
}