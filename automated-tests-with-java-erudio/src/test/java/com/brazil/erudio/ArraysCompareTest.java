package com.brazil.erudio;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.sort;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ArraysCompareTest {

    @Test
    void test() {
        int[] numbers = {25, 8, 21, 32, 3};
        int[] expectedArray = {3, 8, 21, 25, 32};

        sort(numbers);

        assertArrayEquals(numbers, expectedArray);
    }
}
