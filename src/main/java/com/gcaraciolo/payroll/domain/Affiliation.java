package com.gcaraciolo.payroll.domain;

import com.gcaraciolo.common.DatePeriod;

public interface Affiliation {

    public Double calculateDeductions(DatePeriod datePeriod);
}