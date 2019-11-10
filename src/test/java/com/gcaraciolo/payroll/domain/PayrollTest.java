package com.gcaraciolo.payroll.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

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

        assertSalariedEmployee(e, 1000.00);
    }

    @Test
    public void testAddHourlyEmployee() {
        int empId = 1;
        var t = new AddHourlyEmployee(empId, "Guilherme", "Home", 1.0);
        t.execute();

        var e = payrollDatabase.getEmployee(empId);
        assertHourlyEmployee(e, 1.0);
    }

    @Test
    public void testAddCommissionedEmployee() {
        int empId = 1;
        var t = new AddCommissionedEmployee(empId, "Guilherme", "Home", 1000.0, 0.1);
        t.execute();

        var e = payrollDatabase.getEmployee(empId);
        assertCommissionedEmployee(e, 1000.0, 0.1);
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

        var postingDate = LocalDate.now();
        var tct = new TimeCardTransaction(empId, postingDate, 8.0);
        tct.execute();

        var e = payrollDatabase.getEmployee(empId);

        var pc = (HourlyClassification) e.getPaymentClassification();
        TimeCard tc = pc.getTimeCard(postingDate);
        assertEquals(8.0, tc.getHours());
    }

    @Test
    public void testSalesReceiptTransaction() {
        int empId = 3;
        var t = new AddCommissionedEmployee(empId, "Guilherme", "Home", 1000.0, 0.1);
        t.execute();

        var postingDate = LocalDate.now();
        var srt = new SalesReceiptTransaction(empId, postingDate, 79000.00);
        srt.execute();

        var e = payrollDatabase.getEmployee(empId);

        var pc = (CommissionedClassification) e.getPaymentClassification();
        SalesReceipt tc = pc.getSalesReceipt(postingDate);
        assertEquals(79000.00, tc.getAmount());
    }

    @Test
    public void testAddServiceCharge() {
        int empId = 2;
        int memberId = 86;
        var t = new AddHourlyEmployee(empId, "Guilherme", "Home", 1.0);
        t.execute();

        var e = payrollDatabase.getEmployee(empId);
        UnionAffiliation af = new UnionAffiliation(memberId, 3.5);
        e.setAffiliation(af);

        payrollDatabase.addUnionMember(memberId, e);

        var postingDate = LocalDate.now();
        var sct = new ServiceChargeTransaction(memberId, postingDate, 12.95);
        sct.execute();

        ServiceCharge sc = af.getServiceCharge(postingDate);
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

    @Test
    public void testChangeHourlyTransaction() {
        int empId = 3;
        var t = new AddCommissionedEmployee(empId, "Guilherme", "Home", 1000.0, 1.0);
        t.execute();

        var cht = new ChangeEmployeeHourlyTransaction(empId, 2.5);
        cht.execute();

        var e = payrollDatabase.getEmployee(empId);
        assertHourlyEmployee(e, 2.5);
    }

    @Test
    public void testChangeSalariedTransaction() {
        int empId = 3;
        var t = new AddCommissionedEmployee(empId, "Guilherme", "Home", 1000.0, 1.0);
        t.execute();

        var cst = new ChangeEmployeeSalariedTransaction(empId, 1500.00);
        cst.execute();

        var e = payrollDatabase.getEmployee(empId);
        assertSalariedEmployee(e, 1500.00);
    }

    @Test
    public void testChangeCommissionedTransaction() {
        int empId = 3;
        var t = new AddSalariedEmployee(empId, "Guilherme", "Home", 1000.0);
        t.execute();

        var cct = new ChangeEmployeeCommissionedTransaction(empId, 1500.00, 2.5);
        cct.execute();

        var e = payrollDatabase.getEmployee(empId);
        assertCommissionedEmployee(e, 1500.00, 2.5);
    }

    @Test
    public void testChangeDirectTransaction() {
        int empId = 1;
        var t = new AddSalariedEmployee(empId, "Guilherme", "Home", 1000.00);
        t.execute();

        var cdt = new ChangeEmployeeDirectTransaction(empId);
        cdt.execute();

        var e = payrollDatabase.getEmployee(empId);

        assertTrue(e.getPaymentMethod() instanceof DirectMethod);
    }

    @Test
    public void testChangeMailTransaction() {
        int empId = 1;
        var t = new AddSalariedEmployee(empId, "Guilherme", "Home", 1000.00);
        t.execute();

        var cmt = new ChangeEmployeeMailTransaction(empId);
        cmt.execute();

        var e = payrollDatabase.getEmployee(empId);

        assertTrue(e.getPaymentMethod() instanceof MailMethod);
    }

    @Test
    public void testChangeHoldTransaction() {
        int empId = 1;
        var t = new AddSalariedEmployee(empId, "Guilherme", "Home", 1000.00);
        t.execute();

        var cmt = new ChangeEmployeeMailTransaction(empId);
        cmt.execute();

        var cht = new ChangeEmployeeHoldTransaction(empId);
        cht.execute();

        var e = payrollDatabase.getEmployee(empId);
        assertTrue(e.getPaymentMethod() instanceof HoldMethod);
    }

    @Test
    public void testChangeMemberTransaction() {
        int empId = 2;
        int memberId = 1231;
        var t = new AddHourlyEmployee(empId, "Guilherme", "Home", 1.0);
        t.execute();

        var cmt = new ChangeEmployeeMemberTransaction(empId, memberId, 99.42);
        cmt.execute();

        var e = payrollDatabase.getEmployee(empId);
        var af = (UnionAffiliation) e.getAffiliation();
        assertEquals(99.42, af.getDues());

        var m = payrollDatabase.getAffiliationMember(memberId);
        assertEquals(e, m);
    }

    @Test
    public void testChangeUnaffiliatedTransaction() {
        int empId = 2;
        int memberId = 1231;
        var t = new AddHourlyEmployee(empId, "Guilherme", "Home", 1.0);
        t.execute();

        var cmt = new ChangeEmployeeMemberTransaction(empId, memberId, 99.42);
        cmt.execute();

        var ceu = new ChangeEmployeeUnaffiliatedTransaction(empId);
        ceu.execute();

        var m = payrollDatabase.getAffiliationMember(memberId);
        assertTrue(m == null);
    }

    @Test
    public void testPaySingleSalariedEmployee() {
        {
            int empId = 1;
            var t = new AddSalariedEmployee(empId, "Guilherme", "Home", 1000.00);
            t.execute();

            LocalDate payDate = LocalDate.of(2001, 11, 30);
            var pt = new PaydayTransaction(payDate);
            pt.execute();

            Paycheck pc = pt.getPaycheck(empId);
            assertTrue(pc != null);
            assertTrue(pc.getPayDate().equals(payDate));
            assertEquals(pc.getDeductions(), 0.0);
            assertEquals(pc.getNetPay(), 1000.00);
        }
        {
            int empId = 2;
            var t = new AddSalariedEmployee(empId, "Gustavo", "Angola", 1500.00);
            t.execute();

            LocalDate payDate = LocalDate.of(2001, 11, 30);
            var pt = new PaydayTransaction(payDate);
            pt.execute();

            Paycheck pc = pt.getPaycheck(empId);
            assertTrue(pc != null);
            assertTrue(pc.getPayDate().equals(payDate));
            assertEquals(pc.getDeductions(), 0.0);
            assertEquals(pc.getNetPay(), 1500.00);
        }
    }

    @Test
    public void testPaySingleSalariedEmployeeOnWrongDate() {
        int empId = 1;
        var t = new AddSalariedEmployee(empId, "Guilherme", "Home", 1000.00);
        t.execute();

        LocalDate payDate = LocalDate.of(2001, 11, 29);
        var pt = new PaydayTransaction(payDate);
        pt.execute();

        Paycheck pc = pt.getPaycheck(empId);
        assertTrue(pc == null);
    }

    @Test
    public void testPaySingleHourlyEmployeeNoTimeCards() {
        int empId = 1;
        var t = new AddHourlyEmployee(empId, "Guilherme", "Home", 13.45);
        t.execute();

        var payDate = LocalDate.of(2001, 11, 9); // Friday
        var pt = new PaydayTransaction(payDate);
        pt.execute();
        Paycheck pc = pt.getPaycheck(empId);
        assertHourlyPaycheck(pc, payDate, 0.0);
    }

    @Test
    public void testPaySingleHourlyEmployeeWithTimeCards() {
        int empId = 1;
        {
            var t = new AddHourlyEmployee(empId, "Guilherme", "Home", 13.45);
            t.execute();
        }
        {
            var workday = LocalDate.of(2001, 11, 5);
            var t = new TimeCardTransaction(empId, workday, 2.0);
            t.execute();
        }
        {
            var workday = LocalDate.of(2001, 11, 6);
            var t = new TimeCardTransaction(empId, workday, 8.0);
            t.execute();
        }

        var payDate = LocalDate.of(2001, 11, 9); // Friday
        var pt = new PaydayTransaction(payDate);
        pt.execute();
        Paycheck pc = pt.getPaycheck(empId);
        assertHourlyPaycheck(pc, payDate, 134.5);
    }

    @Test
    public void testPaySingleHourlyEmployeeWithTimeCardsOvertime() {
        int empId = 1;
        {
            var t = new AddHourlyEmployee(empId, "Guilherme", "Home", 13.45);
            t.execute();
        }
        {
            var workday = LocalDate.of(2001, 11, 5); // Monday
            var t = new TimeCardTransaction(empId, workday, 9.0);
            t.execute();
        }

        var payDate = LocalDate.of(2001, 11, 9); // Friday
        var pt = new PaydayTransaction(payDate);
        pt.execute();
        Paycheck pc = pt.getPaycheck(empId);
        assertHourlyPaycheck(pc, payDate, 127.77);
    }

    @Test
    public void testPaySingleHourlyEmployeeOnWrongDate() {
        int empId = 1;
        {
            var t = new AddHourlyEmployee(empId, "Guilherme", "Home", 13.45);
            t.execute();
        }
        {
            var workdayOne = LocalDate.of(2001, 11, 5);
            var t = new TimeCardTransaction(empId, workdayOne, 2.0);
            t.execute();
        }

        var payDate = LocalDate.of(2001, 11, 8); // Thursday
        var pt = new PaydayTransaction(payDate);
        pt.execute();
        Paycheck pc = pt.getPaycheck(empId);
        assertTrue(pc == null);
    }

    @Test
    public void testPaySingleHourlyEmployeeWithTimeCardsSpanningPeriods() {
        int empId = 1;
        {
            var t = new AddHourlyEmployee(empId, "Guilherme", "Home", 13.45);
            t.execute();
        }
        {
            var workday = LocalDate.of(2001, 11, 1); // last week
            var t = new TimeCardTransaction(empId, workday, 2.0);
            t.execute();
        }
        {
            var workday = LocalDate.of(2001, 11, 6);
            var t = new TimeCardTransaction(empId, workday, 8.0);
            t.execute();
        }

        var payDate = LocalDate.of(2001, 11, 9); // Friday
        var pt = new PaydayTransaction(payDate);
        pt.execute();
        Paycheck pc = pt.getPaycheck(empId);
        assertHourlyPaycheck(pc, payDate, 107.6);
    }

    @Test
    public void testPaySingleHourlyEmployeeWeekendTimeCards() {
        int empId = 1;
        {
            var t = new AddHourlyEmployee(empId, "Guilherme", "Home", 13.45);
            t.execute();
        }
        {
            var workday = LocalDate.of(2001, 11, 3); // Saturday
            var t = new TimeCardTransaction(empId, workday, 6.0);
            t.execute();
        }
        {
            var workday = LocalDate.of(2001, 11, 6);
            var t = new TimeCardTransaction(empId, workday, 8.0);
            t.execute();
        }

        var payDate = LocalDate.of(2001, 11, 9); // Friday
        var pt = new PaydayTransaction(payDate);
        pt.execute();
        Paycheck pc = pt.getPaycheck(empId);
        assertHourlyPaycheck(pc, payDate, 228.65);
    }

    private void assertHourlyPaycheck(Paycheck paycheck, LocalDate payDate, Double pay) {
        assertTrue(paycheck != null);
        assertTrue(paycheck.getPayDate().equals(payDate));
        assertEquals(paycheck.getNetPay(), pay);
    }

    private void assertSalariedEmployee(Employee e, Double salary) {
        SalariedClassification pc = (SalariedClassification) e.getPaymentClassification();
        assertEquals(salary, pc.getSalary());

        MonthlySchedule ps = (MonthlySchedule) e.getPaymentSchedule();
        assertNotNull(ps);

        HoldMethod pm = (HoldMethod) e.getPaymentMethod();
        assertNotNull(pm);
    }

    private void assertHourlyEmployee(Employee e, Double hourlyRate) {
        HourlyClassification pc = (HourlyClassification) e.getPaymentClassification();
        assertEquals(hourlyRate, pc.getHourlyRate());

        WeeklySchedule ps = (WeeklySchedule) e.getPaymentSchedule();
        assertNotNull(ps);

        HoldMethod pm = (HoldMethod) e.getPaymentMethod();
        assertNotNull(pm);
    }

    private void assertCommissionedEmployee(Employee e, Double salary, Double commissionRate) {
        CommissionedClassification pc = (CommissionedClassification) e.getPaymentClassification();
        assertEquals(salary, pc.getSalary());
        assertEquals(commissionRate, pc.getCommissionRate());

        BiweeklySchedule ps = (BiweeklySchedule) e.getPaymentSchedule();
        assertNotNull(ps);

        HoldMethod pm = (HoldMethod) e.getPaymentMethod();
        assertNotNull(pm);
    }
}