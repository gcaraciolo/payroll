package com.gcaraciolo.payroll.domain;

public class ChangeEmployeeSalariedTransaction extends ChangeEmployeeClassificationTransaction {

    private Double salary;

    public ChangeEmployeeSalariedTransaction(Integer empId, Double salary) {
        super(empId);
        this.salary = salary;
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