package com.gcaraciolo.payroll.domain;

import java.time.LocalDate;

public class SalesReceiptTransaction implements Transaction {

    private Integer empId;
    private LocalDate date;
    private Double amount;

    private PayrollDatabase payrollDatabase = PayrollDatabase.instance();

    public SalesReceiptTransaction(Integer empId, LocalDate date, Double amount) {
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
                throw new IllegalStateException("Tried to add salesreceipt to non-commissioned employee");
            }
        } else {
            throw new IllegalStateException("Employee not found");
        }
    }
}