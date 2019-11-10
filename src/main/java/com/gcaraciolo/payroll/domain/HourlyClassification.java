package com.gcaraciolo.payroll.domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.gcaraciolo.common.DatePeriod;

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
    public Double calculatePay(DatePeriod datePeriod) {
        return timecards.values().stream().filter(tc -> datePeriod.containsDate(tc.getDate()))
                .map(tc -> calculatePayForTimeCard(tc)).reduce(0.0, (a, b) -> a + b);
    }

    // If the customer wants more variations of pay calculation based on overtime,
    // we should create
    // and abstraction for it.
    // Probably the timecard will have this abstraction.
    private Double calculatePayForTimeCard(TimeCard timecard) {
        if (timecard.isInWeekday()) {
            return calculatePayForWeekendTimeCard(timecard);
        }
        return calculatePayForWeekdayTimeCard(timecard);
    }

    private Double calculatePayForWeekdayTimeCard(TimeCard timecard) {
        Double STRAIGHT_TIME_HOURS = 8.0;
        Double OVERTIME_RATE = 1.5;
        Double overtime = Math.max(0.0, timecard.getHours() - STRAIGHT_TIME_HOURS);
        Double straightTime = timecard.getHours() - overtime;
        return straightTime * hourlyRate + overtime * hourlyRate * OVERTIME_RATE;
    }

    private Double calculatePayForWeekendTimeCard(TimeCard timecard) {
        Double OVERTIME_RATE = 1.5;
        return timecard.getHours() * OVERTIME_RATE * hourlyRate;
    }
}