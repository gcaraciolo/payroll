package com.gcaraciolo.payroll.domain;

public abstract class ChangeEmployeeAffiliationTransaction extends ChangeEmployeeTransaction {

    public ChangeEmployeeAffiliationTransaction(Integer empId) {
        super(empId);
    }

    @Override
    protected void change(Employee e) {
        recordMembership(e);
        e.setAffiliation(getAffiliation());
    }

    protected abstract Affiliation getAffiliation();

    protected abstract void recordMembership(Employee e);
}