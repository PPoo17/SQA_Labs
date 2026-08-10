package com.sqa.lab6_2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuadraticEquationExtendedEntryTest {

    private final QuadraticEquation eq = new QuadraticEquation();

    @ParameterizedTest(name = "Rule#{index}: a={0}, b={1}, c={2} -> {3}")
    @CsvSource({
        "0, 67, 67, NOT_QUADRATIC",
        "1, 5,  2,  REAL_ROOTS",
        "1, 2,  1,  EQUAL_ROOTS",
        "1, 1,  1,  IMAGINARY_ROOTS"
    })
    void testDetermineRootNature(int a, int b, int c, RootNature expected) {
        assertEquals(expected, eq.determineRootNature(a, b, c));
    }

    @ParameterizedTest(name = "Invalid input a={0}, b={1}, c={2}")
    @CsvSource({
        "-1, 5, 2",     // a นอกช่วง (ต่ำกว่า MIN)
        "1, 101, 2",    // b นอกช่วง (สูงกว่า MAX)
        "1, 5, -3"      // c นอกช่วง
    })
    void testInvalidInputThrowsException(int a, int b, int c) {
        assertThrows(IllegalArgumentException.class,
            () -> eq.determineRootNature(a, b, c));
    }
}