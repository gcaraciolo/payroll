package com.gcaraciolo.common;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class DateUtil {

    private LocalDate date;

    public DateUtil(LocalDate date) {
        this.date = date;
    }

    public boolean isInBiweekly() {
        return this.biweeklydays().contains(date.getDayOfMonth());
    }

    private ArrayList<Integer> biweeklydays() {
        var lastDay = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
        var biweeklydays = new ArrayList<Integer>();

        for (var i = 1; i <= date.getMonth().maxLength();) {
            biweeklydays.add(i);

            if (lastDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
                i = i + 8;
            } else {
                i += 1;
            }

            lastDay = lastDay.plusDays(1);
        }

        return biweeklydays;
    }

    public boolean isAWeekday() {
        return Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(date.getDayOfWeek());
    }
}