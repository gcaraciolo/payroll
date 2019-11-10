package com.gcaraciolo.payroll.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeeklySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDate(LocalDate payDate) {
        var weekday = payDate.getDayOfWeek();
        return weekday == DayOfWeek.FRIDAY;
    }
}