package com.gcaraciolo.payroll.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayrollDatabase {

    private static PayrollDatabase theInstance = new PayrollDatabase();
    private static Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
    private static Map<Integer, Employee> affiliationMembers = new HashMap<Integer, Employee>();

    private PayrollDatabase() {
    }

    public static PayrollDatabase instance() {
        return theInstance;
    }

    public Employee getEmployee(int empId) {
        return employees.get(empId);
    }

    public void addEmployee(int empId, Employee e) {
        employees.put(empId, e);
    }

    public Employee deleteEmployee(int empId) {
        var e = getEmployee(empId);
        employees.remove(empId);
        return e;
    }

    public void addUnionMember(int memberId, Employee e) {
        affiliationMembers.put(memberId, e);
    }

    public Employee getAffiliationMember(int memberId) {
        return affiliationMembers.get(memberId);
    }

    public void deleteUnionMember(int memberId) {
        affiliationMembers.remove(memberId);
    }

    public List<Integer> getAllEmployeeIds() {
        return new ArrayList<Integer>(employees.keySet());
    }

}