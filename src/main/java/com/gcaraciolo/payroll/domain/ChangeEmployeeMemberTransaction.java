package com.gcaraciolo.payroll.domain;

public class ChangeEmployeeMemberTransaction extends ChangeEmployeeAffiliationTransaction {

    private Double dues;
    private Integer memberId;

    public ChangeEmployeeMemberTransaction(Integer empId, Integer memberId, Double dues) {
        super(empId);
        this.memberId = memberId;
        this.dues = dues;
    }

    @Override
    protected Affiliation getAffiliation() {
        return new UnionAffiliation(memberId, dues);
    }

    @Override
    protected void recordMembership(Employee e) {
        PayrollDatabase.instance().addUnionMember(memberId, e);
    }
}