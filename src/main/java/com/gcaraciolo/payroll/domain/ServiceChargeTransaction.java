package com.gcaraciolo.payroll.domain;

import java.time.LocalDate;

public class ServiceChargeTransaction implements Transaction {

    private int memberId;
    private LocalDate date;
    private Double amount;

    private PayrollDatabase payrollDatabase = PayrollDatabase.instance();

    public ServiceChargeTransaction(int memberId, LocalDate date, Double amount) {
        this.memberId = memberId;
        this.date = date;
        this.amount = amount;
    }

    @Override
    public void execute() {
        var e = payrollDatabase.getAffiliationMember(memberId);
        Affiliation af = e.getAffiliation();
        if (af instanceof UnionAffiliation) {
            var uf = (UnionAffiliation) af;
            uf.addServiceCharge(date, amount);
        }
    }

}