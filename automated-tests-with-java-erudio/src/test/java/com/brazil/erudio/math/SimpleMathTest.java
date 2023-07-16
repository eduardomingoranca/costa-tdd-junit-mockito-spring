package com.brazil.erudio.math;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Math Operations in SimpleMath Class")
class SimpleMathTest {
    SimpleMath math;

    @BeforeAll
    static void setup() {
        System.out.println("Running @BeforeAll method!");
    }

    @AfterAll
    static void cleanup() {
        System.out.println("Running @AfterAll method!");
    }

    @BeforeEach
    void beforeEachMethod() {
        math = new SimpleMath();
        System.out.println("Running @BeforeEach method!");
    }

    @AfterEach
    void afterEachMethod() {
        System.out.println("Running @AfterEach method!");
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @Test
    @DisplayName("Test 6.2 + 2 = 8.2")
    void testSum_When_SixDotTwoIsAddedByTwo_ShouldReturnEightDotTwo() {
        System.out.println("Test 6.2 + 2 = 8.2");

        // AAA - Arrange, Act, Assert
        // Given / Arrange

        double firstNumber = 6.2D;
        double secondNumber = 2D;
        double expected = 8.2D;

        // When / Act
        Double actual = math.sum(firstNumber, secondNumber);

        // Then / Assert
        assertEquals(expected, actual,() -> firstNumber + " + " + secondNumber + " did not produce "+ expected +"!");
        assertNotEquals(9.2, actual);
        assertNotNull(actual);
    }

    @Test
    @DisplayName("Test 6.2 - 2 = 4.2")
    void testSubtraction() {
        System.out.println("Test 6.2 - 2 = 4.2");

        double firstNumber = 6.2D;
        double secondNumber = 2D;

        Double actual = math.subtraction(firstNumber, secondNumber);
        double expected = 4.2D;

        assertEquals(expected, actual, () -> firstNumber + " - " + secondNumber + " did not produce "+ expected +"!");
    }

    @Test
    @DisplayName("Test 6.2 * 2 = 12.4")
    void testMultiplication() {
        System.out.println("Test 6.2 * 2 = 12.4");

        double firstNumber = 6.2D;
        double secondNumber = 2D;

        Double actual = math.multiplication(firstNumber, secondNumber);
        double expected = 12.4D;

        assertEquals(expected, actual, () -> firstNumber + " * " + secondNumber + " did not produce "+ expected +"!");
    }

    @Test
    @DisplayName("Test 6.2 / 2 = 3.1")
    void testDivision() {
        System.out.println("Test 6.2 / 2 = 3.1");

        double firstNumber = 6.2D;
        double secondNumber = 2D;

        Double actual = math.division(firstNumber, secondNumber);
        double expected = 3.1D;

        assertEquals(expected, actual, () -> firstNumber + " / " + secondNumber + " did not produce "+ expected +"!");
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    // @Disabled("TODO: We need still work on it!")
    @Test
    @DisplayName("Test Division by Zero")
    void testDivision_When_FirstNumberIsDividedByZero_ShouldThrowArithmeticException() {
        // Given
        double firstNumber = 6.2D;
        double secondNumber = 0D;

        String expectedMessage = "Impossible to divide by zero!";

        // When & Then
        ArithmeticException actual = assertThrows(ArithmeticException.class, () ->
                // When & Then
                math.division(firstNumber, secondNumber), "Division by zero should throw an ArithmeticException");

        assertEquals(expectedMessage, actual.getMessage(), "Unexpected exception message!");
    }

    @Test
    @DisplayName("Test (6.2 + 2) / 2 = 4.1")
    void testMean() {
        System.out.println("Test (6.2 + 2) / 2 = 4.1");

        double firstNumber = 6.2D;
        double secondNumber = 2D;

        Double actual = math.mean(firstNumber, secondNumber);
        double expected = 4.1D;

        assertEquals(expected, actual, () -> "(" + firstNumber + " + " + secondNumber + ")/2" + " did not produce "+ expected +"!");
    }

    @Test
    @DisplayName("Test Square Root of 16 = 4")
    void testSquareRoot() {
        System.out.println("Test Square Root of 16 = 4");

        double number = 16D;

        Double actual = math.squareRoot(number);
        double expected = 4D;

        assertEquals(expected, actual, () -> "Square Root of " + number + " did not produce "+ expected +"!");
    }

}