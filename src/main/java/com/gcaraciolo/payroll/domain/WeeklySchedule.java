package com.gcaraciolo.payroll.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

import com.gcaraciolo.common.DatePeriod;

public class WeeklySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDate(LocalDate payDate) {
        return payDate.getDayOfWeek() == DayOfWeek.FRIDAY;
    }

    @Override
    public DatePeriod payPeriod(LocalDate payDate) {
        return new DatePeriod(payDate.minusDays(6), payDate);
    }
}