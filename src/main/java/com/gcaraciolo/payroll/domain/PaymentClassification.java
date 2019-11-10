package com.gcaraciolo.payroll.domain;

import java.time.LocalDate;

public interface PaymentClassification {

    public Double calculatePay(LocalDate payDate);
}