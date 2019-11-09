package com.gcaraciolo.payroll.domain;

import java.util.HashMap;
import java.util.Map;

public class PayrollDatabase {

    private static PayrollDatabase theInstance = new PayrollDatabase();
    private static Map<Integer, Employee> employees = new HashMap<Integer, Employee>();

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

}