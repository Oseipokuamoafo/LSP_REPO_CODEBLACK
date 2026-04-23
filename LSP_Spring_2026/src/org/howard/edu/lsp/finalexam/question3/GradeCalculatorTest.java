package org.howard.edu.lsp.finalexam.question3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GradeCalculatorTest {

    private final GradeCalculator calc = new GradeCalculator();

    // 1. Test for average()
    @Test
    public void testAverageOfThreeScores() {
        assertEquals(80.0, calc.average(70, 80, 90), 0.01);
    }

    // 2. Test for letterGrade()
    @Test
    public void testLetterGradeB() {
        assertEquals("B", calc.letterGrade(85.0));
    }

    // 3. Test for isPassing()
    @Test
    public void testIsPassingTrue() {
        assertTrue(calc.isPassing(65.0));
    }

    // 4a. Boundary test — exactly 60 should be passing and return "D"
    @Test
    public void testBoundaryExactly60() {
        assertEquals("D", calc.letterGrade(60.0));
        assertTrue(calc.isPassing(60.0));
    }

    // 4b. Boundary test — score of 0 should be valid and return "F"
    @Test
    public void testBoundaryScoreZero() {
        assertEquals("F", calc.letterGrade(calc.average(0, 0, 0)));
        assertFalse(calc.isPassing(0.0));
    }

    // 5a. Exception test — score below 0
    @Test
    public void testExceptionScoreBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> calc.average(-1, 50, 50));
    }

    // 5b. Exception test — score above 100
    @Test
    public void testExceptionScoreAbove100() {
        assertThrows(IllegalArgumentException.class, () -> calc.average(101, 50, 50));
    }
}
