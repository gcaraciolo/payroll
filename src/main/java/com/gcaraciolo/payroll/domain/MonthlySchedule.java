package com.gcaraciolo.payroll.domain;

import java.time.LocalDate;

public class MonthlySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDate(LocalDate payDate) {
        return isLastDayOfMonth(payDate);
    }

    private boolean isLastDayOfMonth(LocalDate date) {
        int m1 = date.getMonthValue();
        int m2 = date.plusDays(1).getMonthValue();
        return m1 != m2;
    }
}