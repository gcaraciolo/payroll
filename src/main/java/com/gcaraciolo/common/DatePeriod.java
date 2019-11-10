package com.gcaraciolo.common;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DatePeriod {

    private LocalDate begin;
    private LocalDate end;

    public boolean containsDate(LocalDate date) {
        return date.isAfter(begin.minusDays(1)) && date.isBefore(end);
    }
}