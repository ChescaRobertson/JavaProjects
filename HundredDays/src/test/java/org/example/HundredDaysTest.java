package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HundredDaysTest {

    @Test
    public void HundredDaysFromNowTest(){
        LocalDate date = LocalDate.of(2022, Month.JULY, 04);
        assertEquals(LocalDate.of(2022, Month.OCTOBER, 12), HundredDays.calculateDays(date));
    }
}
