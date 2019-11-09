package com.gcaraciolo.payroll.domain;

public abstract class ChangeEmployeeMethodTransaction extends ChangeEmployeeTransaction {

    public ChangeEmployeeMethodTransaction(Integer empId) {
        super(empId);
    }

    @Override
    protected void change(Employee e) {
        e.setPaymentMethod(getMethod());
    }

    protected abstract PaymentMethod getMethod();
}