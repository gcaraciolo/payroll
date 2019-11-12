package com.gcaraciolo.common;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DatePeriod {

    private LocalDate begin;
    private LocalDate end;

    public boolean containsDate(LocalDate date) {
        return date.atTime(LocalTime.MAX).isAfter(begin.atTime(LocalTime.MIN))
                && date.atTime(LocalTime.MAX).isBefore(end.atTime(LocalTime.MIN));
    }
}