package com.gcaraciolo.payroll.domain;

import lombok.Getter;

@Getter
public class AddSalariedEmployee extends AddEmployeeTransaction {

    private Double salary;

    public AddSalariedEmployee(Integer empId, String itsName, String itsAddress, Double itsSalary) {
        super(empId, itsName, itsAddress);
        this.salary = itsSalary;
    }

    @Override
    protected PaymentClassification getClassification() {
        return new SalariedClassification(salary);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }

}