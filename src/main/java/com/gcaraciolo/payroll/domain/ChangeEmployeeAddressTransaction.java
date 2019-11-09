package com.gcaraciolo.payroll.domain;

public class ChangeEmployeeAddressTransaction extends ChangeEmployeeTransaction {

    private String name;

    public ChangeEmployeeAddressTransaction(Integer empId, String name) {
        super(empId);
        this.name = name;
    }

    @Override
    protected void change(Employee e) {
        e.changeAddress(name);
    }
}