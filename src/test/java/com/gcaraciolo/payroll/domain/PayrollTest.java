package com.gcaraciolo.payroll.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.gcaraciolo.payroll.domain.AddSalariedEmployee;

@SpringBootTest
public class PayrollTest {

    PayrollDatabase payrollDatabase = PayrollDatabase.instance();

    @Test
    public void testAddSalariedEmployee() {
        int empId = 1;
        var t = new AddSalariedEmployee(empId, "Guilherme", "Home", 1000.00);
        t.execute();

        var e = payrollDatabase.getEmployee(empId);
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

        var e = payrollDatabase.getEmployee(empId);
        assertTrue(e.getName().equals("Guilherme"));

        HourlyClassification pc = (HourlyClassification) e.getPaymentClassification();
        assertEquals(1, 0, pc.getHourlyRate());

        WeeklySchedule ps = (WeeklySchedule) e.getPaymentSchedule();
        assertNotNull(ps);

        HoldMethod pm = (HoldMethod) e.getPaymentMethod();
        assertNotNull(pm);
    }

    @Test
    public void testAddCommissionedEmployee() {
        int empId = 1;
        var t = new AddCommissionedEmployee(empId, "Guilherme", "Home", 1000.0, 0.1);
        t.execute();

        var e = payrollDatabase.getEmployee(empId);
        assertTrue(e.getName().equals("Guilherme"));

        CommissionedClassification pc = (CommissionedClassification) e.getPaymentClassification();
        assertEquals(1000.00, pc.getSalary());
        assertEquals(0.1, pc.getCommissionRate());

        BiweeklySchedule ps = (BiweeklySchedule) e.getPaymentSchedule();
        assertNotNull(ps);

        HoldMethod pm = (HoldMethod) e.getPaymentMethod();
        assertNotNull(pm);
    }

    @Test
    public void testDeleteEmployee() {
        int empId = 3;
        var t = new AddSalariedEmployee(empId, "Guilherme", "Home", 1000.0);
        t.execute();
        {
            var e = payrollDatabase.getEmployee(empId);
            assertNotNull(e);
        }

        var dt = new DeleteEmployeeTransaction(empId);
        dt.execute();
        {
            var e = payrollDatabase.getEmployee(empId);
            assertTrue(e == null);
        }
    }

    @Test
    public void testTimeCardTransaction() {
        int empId = 3;
        var t = new AddHourlyEmployee(empId, "Guilherme", "Home", 1.0);
        t.execute();

        var tct = new TimeCardTransaction(empId, 20011031, 8.0);
        tct.execute();

        var e = payrollDatabase.getEmployee(empId);

        var pc = (HourlyClassification) e.getPaymentClassification();
        TimeCard tc = pc.getTimeCard(20011031);
        assertEquals(8.0, tc.getHours());
    }

    @Test
    public void testSalesReceiptTransaction() {
        int empId = 3;
        var t = new AddCommissionedEmployee(empId, "Guilherme", "Home", 1000.0, 0.1);
        t.execute();

        var srt = new SalesReceiptTransaction(empId, 20011101, 79000.00);
        srt.execute();

        var e = payrollDatabase.getEmployee(empId);

        var pc = (CommissionedClassification) e.getPaymentClassification();
        SalesReceipt tc = pc.getSalesReceipt(20011101);
        assertEquals(79000.00, tc.getAmount());
    }

    @Test
    public void testAddServiceCharge() {
        int empId = 2;
        var t = new AddHourlyEmployee(empId, "Guilherme", "Home", 1.0);
        t.execute();

        var e = payrollDatabase.getEmployee(empId);
        UnionAffiliation af = new UnionAffiliation(3.5);
        e.setAffiliation(af);

        int memberId = 86;
        payrollDatabase.addUnionMember(memberId, e);

        var sct = new ServiceChargeTransaction(memberId, 20011101, 12.95);
        sct.execute();

        ServiceCharge sc = af.getServiceCharge(20011101);
        assertNotNull(sc);
        assertEquals(12.95, sc.getAmount());
    }

    @Test
    public void testChangeNameTransaction() {
        int empId = 2;
        var t = new AddHourlyEmployee(empId, "Guilherme", "Home", 1.0);
        t.execute();

        var cnt = new ChangeEmployeeNameTransaction(empId, "Gustavo");
        cnt.execute();

        var e = payrollDatabase.getEmployee(empId);
        assertTrue(e.getName().equals("Gustavo"));
    }

    @Test
    public void testChangeAddressTransaction() {
        int empId = 2;
        var t = new AddHourlyEmployee(empId, "Guilherme", "Home", 1.0);
        t.execute();

        var cnt = new ChangeEmployeeAddressTransaction(empId, "Casa");
        cnt.execute();

        var e = payrollDatabase.getEmployee(empId);
        assertTrue(e.getAddress().equals("Casa"));
    }
}