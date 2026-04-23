package org.howard.edu.lsp.finalexam.question3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GradeCalculatorTest {

    private GradeCalculator calc;

    @BeforeEach
    public void setUp() {
        calc = new GradeCalculator();
    }

    /** average() returns the correct mean of three scores */
    @Test
    public void testAverageOfThreeScores() {
        assertEquals(80.0, calc.average(70, 80, 90), 0.001);
    }

    /** letterGrade() returns "B" for an average of 85 */
    @Test
    public void testLetterGradeB() {
        assertEquals("B", calc.letterGrade(85.0));
    }

    /** isPassing() returns true when average is 60 or above */
    @Test
    public void testIsPassingAboveThreshold() {
        assertTrue(calc.isPassing(75.0));
    }

    /** Boundary: average of exactly 60 is passing (grade "D") */
    @Test
    public void testBoundaryExactly60IsPassing() {
        assertTrue(calc.isPassing(60.0));
        assertEquals("D", calc.letterGrade(60.0));
    }

    /** Boundary: average just below 60 is failing */
    @Test
    public void testBoundaryJustBelow60IsFailing() {
        assertFalse(calc.isPassing(59.9));
        assertEquals("F", calc.letterGrade(59.9));
    }

    /** Exception: score below 0 throws IllegalArgumentException */
    @Test
    public void testExceptionScoreBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> calc.average(-1, 50, 50));
    }

    /** Exception: score above 100 throws IllegalArgumentException */
    @Test
    public void testExceptionScoreAbove100() {
        assertThrows(IllegalArgumentException.class, () -> calc.average(50, 101, 50));
    }
}
