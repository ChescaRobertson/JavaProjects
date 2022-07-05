package org.example;

import org.junit.jupiter.api.Test;

import static org.example.EvenOrOdd.isEven;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EvenOrOddTest {

    @Test
    public void isEvenTrueTest(){
        assertEquals(true, isEven(2));
    }

    @Test
    public void isEvenFalseTest(){
        assertEquals(false, isEven(3));
    }
}
