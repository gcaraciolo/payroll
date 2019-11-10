package com.gcaraciolo.payroll.domain;

import java.time.LocalDate;

public interface PaymentSchedule {

    public boolean isPayDate(LocalDate payDate);
}