package com.gcaraciolo.payroll.domain;

public class ChangeEmployeeHourlyTransaction extends ChangeEmployeeClassificationTransaction {

    private Double hourlyRate;

    public ChangeEmployeeHourlyTransaction(Integer empId, Double hourlyRate) {
        super(empId);
        this.hourlyRate = hourlyRate;
    }

    @Override
    protected PaymentClassification getClassification() {
        return new HourlyClassification(hourlyRate);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }

}