package com.gcaraciolo.payroll.domain;

import java.time.LocalDate;

import com.gcaraciolo.common.DatePeriod;

public class MonthlySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDate(LocalDate payDate) {
        return isLastDayOfMonth(payDate);
    }

    @Override
    public DatePeriod payPeriod(LocalDate payDate) {
        var startOfMonth = LocalDate.of(payDate.getYear(), payDate.getMonthValue(), 1);
        return new DatePeriod(startOfMonth, payDate);
    }

    private boolean isLastDayOfMonth(LocalDate date) {
        int m1 = date.getMonthValue();
        int m2 = date.plusDays(1).getMonthValue();
        return m1 != m2;
    }
}