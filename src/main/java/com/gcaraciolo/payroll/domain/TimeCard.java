package com.gcaraciolo.payroll.domain;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class TimeCard {

    private LocalDate date;
    private double hours;

    public TimeCard(LocalDate date, double hours) {
        this.date = date;
        this.hours = hours;
    }
}