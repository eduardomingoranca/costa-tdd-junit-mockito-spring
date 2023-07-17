package com.brazil.erudio.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test Math Operations in SimpleMath Class")
class SimpleMathTestS4 {
    SimpleMath math;

    @BeforeEach
    void beforeEachMethod() {
        math = new SimpleMath();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Pele", "Senna", "Keith Moon"})
    void testValueSource(String firstName) {
        System.out.println(firstName);
        assertNotNull(firstName);
    }

    @DisplayName("Test double subtraction [firstNumber, secondNumber, expected]")
    @ParameterizedTest
    @CsvFileSource(resources = "/testDivision.csv")
    void testDivision(double firstNumber, double secondNumber, double expected) {

        System.out.println("Test " + firstNumber + " / " + secondNumber + " = " + expected + "!");
        Double actual = math.division(firstNumber, secondNumber);

        assertEquals(expected, actual, 2D, () -> firstNumber + " / " + secondNumber
                + " did not produce "+ expected + "!");
    }

}
