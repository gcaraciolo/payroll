package com.gcaraciolo.payroll.domain;

public class AddCommissionedEmployee extends AddEmployeeTransaction {

    private Double salary;
    private Double commissionRate;

    public AddCommissionedEmployee(Integer itsEmpId, String itsName, String itsAddress, Double salary,
            Double commissionRate) {
        super(itsEmpId, itsName, itsAddress);
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