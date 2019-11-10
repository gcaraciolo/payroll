package com.gcaraciolo.payroll.domain;

import java.time.LocalDate;

/**
 * Null Object for non affiliated employees
 */
public class NoAffiliation implements Affiliation {

    @Override
    public Double calculateDeductions(LocalDate date) {
        return 0.0;
    }
}