package com.gcaraciolo.payroll.domain;

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
}