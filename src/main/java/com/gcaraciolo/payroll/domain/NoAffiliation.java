package com.gcaraciolo.payroll.domain;

import com.gcaraciolo.common.DatePeriod;

/**
 * Null Object for non affiliated employees
 */
public class NoAffiliation implements Affiliation {

    @Override
    public Double calculateDeductions(DatePeriod datePeriod) {
        return 0.0;
    }
}