package com.gcaraciolo.payroll.domain;

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

    public TimeCard addTimeCard(long date, double hours) {
        var tc = new TimeCard(date, hours);
        timecards.put(Long.toString(date), tc);
        return tc;
    }

    public TimeCard getTimeCard(long date) {
        return timecards.get(Long.toString(date));
    }
}