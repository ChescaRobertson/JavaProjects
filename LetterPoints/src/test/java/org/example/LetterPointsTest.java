package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LetterPointsTest {

    static LetterPoints letterPoints;

    @BeforeEach
    public void initTest(){
        LetterPoints letterPoints = new LetterPoints();
    }

    @Test
    public void LetterPointsLowerCaseTest(){
        assertEquals(8, letterPoints.wordScoreCalculator("hello"));
    }

    @Test
    public void LetterPointsUpperCaseTest(){
        assertEquals(8, letterPoints.wordScoreCalculator("HELLO"));
    }

    @Test
    public void LetterPointsMixedCase(){
        assertEquals(8, letterPoints.wordScoreCalculator("HeLLo"));
    }

    @Test
    public void LetterPointsNoValueTest(){
        assertEquals(0, letterPoints.wordScoreCalculator(""));
    }

    @Test
    public void LetterPointsSpecialCharacterTest(){
        assertEquals(0, letterPoints.wordScoreCalculator("%$^^"));
    }

}
