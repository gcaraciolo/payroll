package com.gcaraciolo.payroll.domain;

import java.time.LocalDate;

public interface Affiliation {

    public Double calculateDeductions(LocalDate date);
}