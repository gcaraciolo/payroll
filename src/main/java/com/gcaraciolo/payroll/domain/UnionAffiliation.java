package com.gcaraciolo.payroll.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.gcaraciolo.common.DatePeriod;

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
    public Double calculateDeductions(DatePeriod datePeriod) {
        return dues * numberOfDeductableWeeks(datePeriod);
    }

    private int numberOfDeductableWeeks(DatePeriod datePeriod) {
        var deductableWeeks = 0;

        for (var day = datePeriod.getBegin(); day.isBefore(datePeriod.getEnd().plusDays(1)); day = day.plusDays(1)) {
            if (day.getDayOfWeek() == DayOfWeek.FRIDAY) {
                deductableWeeks++;
            }
        }

        return deductableWeeks;
    }
}