package com.gcaraciolo.payroll.domain;

import lombok.Getter;

@Getter
public abstract class AddEmployeeTransaction implements Transaction {

    private Integer empId;
    private String name;
    private String address;

    private PayrollDatabase payrollDatabase = PayrollDatabase.instance();

    public AddEmployeeTransaction(Integer itsEmpId, String itsName, String itsAddress) {
        this.empId = itsEmpId;
        this.name = itsName;
        this.address = itsAddress;
    }

    public void execute() {
        var e = new Employee(empId, name, address);
        e.setPaymentClassification(getClassification());
        e.setPaymentSchedule(getSchedule());
        e.setPaymentMethod(new HoldMethod());
        e.setAffiliation(new NoAffiliation());
        this.payrollDatabase.addEmployee(empId, e);
    }

    protected abstract PaymentClassification getClassification();

    protected abstract PaymentSchedule getSchedule();
}