package com.brazil.erudio.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleMathTest {

    @Test
    void testSum() {
        SimpleMath math = new SimpleMath();
        Double actual = math.sum(6.2D, 2D);
        double expected = 8.2D;

        assertEquals(expected, actual, "6.2 + 2 did not produce 8.2");
        assertNotEquals(9.2, actual);
        assertNotNull(actual);
    }

}