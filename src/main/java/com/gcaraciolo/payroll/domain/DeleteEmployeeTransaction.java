package com.gcaraciolo.payroll.domain;

public class DeleteEmployeeTransaction implements Transaction {

    private Integer empId;

    private PayrollDatabase payrollDatabase = PayrollDatabase.instance();

    public DeleteEmployeeTransaction(Integer empId) {
        this.empId = empId;
    }

    @Override
    public void execute() {
        payrollDatabase.deleteEmployee(empId);
    }
}