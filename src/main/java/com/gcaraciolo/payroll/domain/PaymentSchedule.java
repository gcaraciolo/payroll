package com.gcaraciolo.payroll.domain;

import java.time.LocalDate;

import com.gcaraciolo.common.DatePeriod;

public interface PaymentSchedule {

    public boolean isPayDate(LocalDate payDate);

    public DatePeriod payPeriod(LocalDate payDate);
}