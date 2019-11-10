package com.gcaraciolo.payroll.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class UnionAffiliation implements Affiliation {

    private Integer memberId;
    private Double dues;
    private Map<String, ServiceCharge> servicecharges = new HashMap<String, ServiceCharge>();

    public UnionAffiliation(Integer memberId, Double dues) {
        this.memberId = memberId;
        this.dues = dues;
    }

    public ServiceCharge getServiceCharge(LocalDate date) {
        return servicecharges.get(date.toString());
    }

    public ServiceCharge addServiceCharge(LocalDate date, double amount) {
        var sc = new ServiceCharge(date, amount);
        servicecharges.put(date.toString(), sc);
        return sc;
    }

    @Override
    public Double calculateDeductions(LocalDate date) {
        return dues * accruedsInPayDay(date);
    }

    private int accruedsInPayDay(LocalDate date) {
        int accrued = 0;
        LocalDate dayIterator = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
        LocalDate lastDayOfMonth = LocalDate.of(date.getYear(), date.getMonthValue(), date.getMonth().maxLength());

        while (dayIterator.isBefore(lastDayOfMonth) || dayIterator.isEqual(lastDayOfMonth)) {
            if (dayIterator.getDayOfWeek() == DayOfWeek.FRIDAY) {
                accrued++;
            }
            dayIterator = dayIterator.plusDays(1);
        }

        return accrued;
    }
}