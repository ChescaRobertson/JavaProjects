package org.example;

import org.junit.jupiter.api.Test;

import static org.example.PasswordComplexity.isComplex;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordComplexityTest {

    @Test
    public void isComplex_True(){
        assertEquals(true, isComplex("Complex1"));
    }

    @Test
    public void isComplex_False(){
        assertEquals(false, isComplex("pass"));
    }
}
