package com.gcaraciolo.payroll.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

import com.gcaraciolo.common.DatePeriod;
import com.gcaraciolo.common.DateUtil;

public class BiweeklySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDate(LocalDate payDate) {
        return (payDate.getDayOfWeek() == DayOfWeek.FRIDAY) && new DateUtil(payDate).isInBiweekly();
    }

    @Override
    public DatePeriod payPeriod(LocalDate payDate) {
        return new DatePeriod(payDate.minusDays(15), payDate);
    }
}