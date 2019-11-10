package com.gcaraciolo.payroll.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

import lombok.Getter;

@Getter
public class TimeCard {

    private LocalDate date;
    private double hours;

    public TimeCard(LocalDate date, double hours) {
        this.date = date;
        this.hours = hours;
    }

    public boolean isInPayPeriod(LocalDate payDate) {
        int endDate = payDate.getDayOfMonth();
        int startDate = payDate.getDayOfMonth() - 7;
        return (date.getDayOfMonth() >= startDate) && (date.getDayOfMonth() < endDate);
    }

    public boolean isInWeekday() {
        return Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(date.getDayOfWeek());
    }
}