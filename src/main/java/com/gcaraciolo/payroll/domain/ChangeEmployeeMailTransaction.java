package com.gcaraciolo.payroll.domain;

public class ChangeEmployeeMailTransaction extends ChangeEmployeeMethodTransaction {

    public ChangeEmployeeMailTransaction(Integer empId) {
        super(empId);
    }

    @Override
    protected PaymentMethod getMethod() {
        return new MailMethod();
    }

}