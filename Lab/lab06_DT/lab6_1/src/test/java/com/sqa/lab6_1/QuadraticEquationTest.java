package com.sqa.lab6_1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuadraticEquationTest {

    private final QuadraticEquation eq = new QuadraticEquation();

    @ParameterizedTest(name = "a={0}, b={1}, c={2} -> {3}")
    @CsvSource({
        "0, 67, 67, NOT_QUADRATIC",
        "1, 5,  2,  REAL_ROOTS",
        "1, 2,  1,  EQUAL_ROOTS",
        "1, 1,  1,  IMAGINARY_ROOTS"
    })
    void testDetermineRootNature(int a, int b, int c, RootNature expected) {
        RootNature result = eq.determineRootNature(a, b, c);
        assertEquals(expected, result);
    }
}