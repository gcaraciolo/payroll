package com.gcaraciolo.common;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

@SpringBootTest
public class DateUtilTest {

    @Test
    public void testCalculateNumberOfWeeksInAMonth() {
        assertTrue(new DateUtil(LocalDate.of(2001, 11, 2)).isInBiweekly());
        assertFalse(new DateUtil(LocalDate.of(2001, 11, 9)).isInBiweekly());
        assertTrue(new DateUtil(LocalDate.of(2001, 11, 16)).isInBiweekly());
        assertFalse(new DateUtil(LocalDate.of(2001, 11, 23)).isInBiweekly());
        assertTrue(new DateUtil(LocalDate.of(2001, 11, 30)).isInBiweekly());

        assertTrue(new DateUtil(LocalDate.of(2001, 12, 1)).isInBiweekly());
        assertFalse(new DateUtil(LocalDate.of(2001, 12, 6)).isInBiweekly());
        assertTrue(new DateUtil(LocalDate.of(2001, 12, 13)).isInBiweekly());
        assertFalse(new DateUtil(LocalDate.of(2001, 12, 20)).isInBiweekly());
        assertTrue(new DateUtil(LocalDate.of(2001, 12, 27)).isInBiweekly());
        assertFalse(new DateUtil(LocalDate.of(2001, 12, 31)).isInBiweekly());

    }
}