package com.gcaraciolo.payroll.domain;

public class TimeCardTransaction implements Transaction {

    private Integer empId;
    private long date;
    private Double hours;

    private PayrollDatabase payrollDatabase = PayrollDatabase.instance();

    public TimeCardTransaction(Integer empId, long date, Double hours) {
        this.empId = empId;
        this.date = date;
        this.hours = hours;
    }

    @Override
    public void execute() {
        var e = payrollDatabase.getEmployee(empId);
        if (e != null) {
            var pc = e.getPaymentClassification();
            if (pc instanceof HourlyClassification) {
                var hc = (HourlyClassification) pc;
                hc.addTimeCard(date, hours);
            } else {
                throw new IllegalStateException("Tried to add timecard to non-hourly employee");
            }
        } else {
            throw new IllegalStateException("Employee not found");
        }
    }
}