package com.gcaraciolo.payroll.domain;

public class ChangeEmployeeDirectTransaction extends ChangeEmployeeMethodTransaction {

    public ChangeEmployeeDirectTransaction(Integer empId) {
        super(empId);
    }

    @Override
    protected PaymentMethod getMethod() {
        return new DirectMethod();
    }

}