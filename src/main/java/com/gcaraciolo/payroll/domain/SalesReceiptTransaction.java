package com.gcaraciolo.payroll.domain;

public class SalesReceiptTransaction implements Transaction {

    private Integer empId;
    private long date;
    private Double amount;

    private PayrollDatabase payrollDatabase = PayrollDatabase.instance();

    public SalesReceiptTransaction(Integer empId, long date, Double amount) {
        this.empId = empId;
        this.date = date;
        this.amount = amount;
    }

    @Override
    public void execute() {
        var e = payrollDatabase.getEmployee(empId);
        if (e != null) {
            var pc = e.getPaymentClassification();
            if (pc instanceof CommissionedClassification) {
                var cc = (CommissionedClassification) pc;
                cc.addSalesReceipt(date, amount);
            } else {
                throw new IllegalStateException("Tried to add timecard to non-hourly employee");
            }
        } else {
            throw new IllegalStateException("Employee not found");
        }
    }
}