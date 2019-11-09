package com.gcaraciolo.payroll.domain;

public class AddHourlyEmployee extends AddEmployeeTransaction {

    private Double hourlyRate;

    public AddHourlyEmployee(Integer itsEmpId, String itsName, String itsAddress, Double hourlyRate) {
        super(itsEmpId, itsName, itsAddress);
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