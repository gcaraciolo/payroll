package com.gcaraciolo.payroll.domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PaydayTransaction implements Transaction {

    private LocalDate payDate;
    private Map<Integer, Paycheck> paychecks = new HashMap<Integer, Paycheck>();

    public PaydayTransaction(LocalDate payDate) {
        this.payDate = payDate;
    }

    @Override
    public void execute() {
        var empIds = PayrollDatabase.instance().getAllEmployeeIds();
        empIds.stream().forEach(empId -> {
            Employee e = PayrollDatabase.instance().getEmployee(empId);
            if (e != null) {
                if (e.isPayDate(payDate)) {
                    Paycheck pc = e.payday(payDate);
                    paychecks.put(empId, pc);
                }
            }
        });
    }

    public Paycheck getPaycheck(Integer empId) {
        return paychecks.get(empId);
    }
}