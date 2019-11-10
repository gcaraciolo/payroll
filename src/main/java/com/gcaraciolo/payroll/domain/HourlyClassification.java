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
        return timecards.values().stream().filter(tc -> isInPayPeriod(tc, payDate))
                .map(tc -> calculatePayForTimeCard(tc)).reduce(0.0, (a, b) -> a + b);
    }

    private Double calculatePayForTimeCard(TimeCard timecard) {
        Double STRAIGHT_TIME_HOURS = 8.0;
        Double OVERTIME_RATE = 1.5;
        Double overtime = Math.max(0.0, timecard.getHours() - STRAIGHT_TIME_HOURS);
        Double straightTime = timecard.getHours() - overtime;
        return straightTime * hourlyRate + overtime * hourlyRate * OVERTIME_RATE;
    }

    private boolean isInPayPeriod(TimeCard timecard, LocalDate payDate) {
        int endDate = payDate.getDayOfMonth();
        int startDate = payDate.getDayOfMonth() - 5;
        return (timecard.getDate().getDayOfMonth() >= startDate) && (timecard.getDate().getDayOfMonth() <= endDate);
    }
}