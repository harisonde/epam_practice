package com.epam;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MaxCounters {

    public static void main(String[] args) {
        int counters = 5;
        int[] operations = new int[]{3, 4, 4, 6, 1, 4, 4};

        int[] output = solve(counters, operations);

        IntStream.range(0, 5).forEach(index -> System.out.print(" " + output[index]));
    }

    private static int[] solve(int counters, int[] operations) {
        int[] output = new int[counters];
        int currentMax = 0;

        for (int curVal : operations) {
            if (curVal > 0 && curVal <= counters) {
                output[curVal - 1] = output[curVal - 1] + 1;
                currentMax = Math.max(currentMax, output[curVal - 1]);
            } else {
                Arrays.fill(output, currentMax);
            }
        }

        return output;
    }
}
