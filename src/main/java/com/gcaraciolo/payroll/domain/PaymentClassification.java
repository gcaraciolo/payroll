package com.gcaraciolo.payroll.domain;

import com.gcaraciolo.common.DatePeriod;

public interface PaymentClassification {

    public Double calculatePay(DatePeriod payPeriod);
}