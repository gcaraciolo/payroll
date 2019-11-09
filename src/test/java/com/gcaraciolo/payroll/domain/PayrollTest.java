package com.gcaraciolo.payroll.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.gcaraciolo.payroll.domain.AddSalariedEmployee;

@SpringBootTest
public class PayrollTest {

    @Test
    public void testAddSalariedEmployee() {
        int empId = 1;
        var t = new AddSalariedEmployee(empId, "Guilherme", "Home", 1000.00);
        t.execute();

        var e = PayrollDatabase.instance().getEmployee(empId);
        assertTrue(e.getName().equals("Guilherme"));

        SalariedClassification pc = (SalariedClassification) e.getPaymentClassification();
        assertEquals(1000.00, pc.getSalary());

        MonthlySchedule ps = (MonthlySchedule) e.getPaymentSchedule();
        assertNotNull(ps);

        HoldMethod pm = (HoldMethod) e.getPaymentMethod();
        assertNotNull(pm);
    }

    @Test
    public void testAddHourlyEmployee() {
        int empId = 1;
        var t = new AddHourlyEmployee(empId, "Guilherme", "Home", 1.0);
        t.execute();

        var e = PayrollDatabase.instance().getEmployee(empId);
        assertTrue(e.getName().equals("Guilherme"));

        HourlyClassification pc = (HourlyClassification) e.getPaymentClassification();
        assertEquals(1, 0, pc.getHourlyRate());

        WeeklySchedule ps = (WeeklySchedule) e.getPaymentSchedule();
        assertNotNull(ps);

        HoldMethod pm = (HoldMethod) e.getPaymentMethod();
        assertNotNull(pm);
    }

    @Test
    public void addCommissionedEmployee() {
        int empId = 1;
        var t = new AddCommissionedEmployee(empId, "Guilherme", "Home", 1000.0, 0.1);
        t.execute();

        var e = PayrollDatabase.instance().getEmployee(empId);
        assertTrue(e.getName().equals("Guilherme"));

        CommissionedClassification pc = (CommissionedClassification) e.getPaymentClassification();
        assertEquals(1000.00, pc.getSalary());
        assertEquals(0.1, pc.getCommissionRate());

        BiweeklySchedule ps = (BiweeklySchedule) e.getPaymentSchedule();
        assertNotNull(ps);

        HoldMethod pm = (HoldMethod) e.getPaymentMethod();
        assertNotNull(pm);
    }

}