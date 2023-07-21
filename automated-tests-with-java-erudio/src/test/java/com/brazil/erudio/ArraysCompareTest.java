package com.brazil.erudio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.Arrays.sort;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ArraysCompareTest {

    @Test
    void test() {
        int[] numbers = {25, 8, 21, 32, 3};
        int[] expectedArray = {3, 8, 21, 25, 32};

        sort(numbers);

        assertArrayEquals(numbers, expectedArray);
    }

    @Test
    //@Timeout(1)
    @Timeout(value = 15, unit = MILLISECONDS)
    void testSortPerformance() {
        int[] numbers = {25, 8, 21, 32, 3};
        for (int i = 0; i < 100000; i++) {
            numbers[0] = i;
            sort(numbers);
        }
    }
}
