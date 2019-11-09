package com.gcaraciolo.payroll.domain;

public class ChangeEmployeeCommissionedTransaction extends ChangeEmployeeClassificationTransaction {

    private Double salary;
    private Double commissionRate;

    public ChangeEmployeeCommissionedTransaction(int empId, Double salary, Double commissionRate) {
        super(empId);
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    @Override
    protected PaymentClassification getClassification() {
        return new CommissionedClassification(salary, commissionRate);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }

}