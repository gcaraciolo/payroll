package com.gcaraciolo.payroll.domain;

public abstract class ChangeEmployeeClassificationTransaction extends ChangeEmployeeTransaction {

    public ChangeEmployeeClassificationTransaction(Integer empId) {
        super(empId);
    }

    @Override
    protected void change(Employee e) {
        e.setPaymentClassification(getClassification());
        e.setPaymentSchedule(getSchedule());
    }

    protected abstract PaymentClassification getClassification();

    protected abstract PaymentSchedule getSchedule();
}