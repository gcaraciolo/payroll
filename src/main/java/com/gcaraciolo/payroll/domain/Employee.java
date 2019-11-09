package com.gcaraciolo.payroll.domain;

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
}