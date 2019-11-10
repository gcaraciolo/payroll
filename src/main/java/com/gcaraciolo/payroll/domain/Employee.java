package com.gcaraciolo.payroll.domain;

import java.time.LocalDate;

import com.gcaraciolo.common.DatePeriod;

import lombok.Getter;

@Getter
public class Employee {

    private Integer id;
    private String name;
    private String address;
    private PaymentMethod paymentMethod;
    private PaymentClassification paymentClassification;
    private PaymentSchedule paymentSchedule;
    private Affiliation affiliation;

    public Employee(Integer itsId, String itsName, String itsAddress) {
        this.id = itsId;
        this.name = itsName;
        this.address = itsAddress;
    }

    public void setPaymentMethod(PaymentMethod method) {
        this.paymentMethod = method;
    }

    public void setPaymentClassification(PaymentClassification classification) {
        this.paymentClassification = classification;
    }

    public void setPaymentSchedule(PaymentSchedule schedule) {
        this.paymentSchedule = schedule;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeAddress(String address) {
        this.address = address;
    }

    public boolean isPayDate(LocalDate payDate) {
        return paymentSchedule.isPayDate(payDate);
    }

    public Paycheck payday(LocalDate payDate) {
        DatePeriod payPeriod = paymentSchedule.payPeriod(payDate);
        Double grossPay = paymentClassification.calculatePay(payPeriod);
        Double deductions = affiliation.calculateDeductions(payPeriod);
        Double netPay = grossPay - deductions;
        return new Paycheck(payDate, grossPay, deductions, netPay);
    }
}