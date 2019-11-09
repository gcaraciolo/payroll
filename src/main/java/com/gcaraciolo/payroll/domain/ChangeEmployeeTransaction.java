package com.gcaraciolo.payroll.domain;

public abstract class ChangeEmployeeTransaction implements Transaction {

    private Integer empId;

    private PayrollDatabase payrollDatabase = PayrollDatabase.instance();

    public ChangeEmployeeTransaction(Integer empId) {
        this.empId = empId;
    }

    @Override
    public void execute() {
        var e = payrollDatabase.getEmployee(empId);
        if (e != null) {
            change(e);
        } else {
            throw new IllegalStateException("Employee not found");
        }
    }

    protected abstract void change(Employee e);
}