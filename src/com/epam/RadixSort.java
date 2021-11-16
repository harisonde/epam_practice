package com.epam;

import java.util.stream.IntStream;

public class RadixSort {
    public static void main(String[] args) {
        int[] input = new int[]{67, 10, 8119, 909090, 765, 45, 32, 8};

        int maxElement = IntStream.of(input).max().orElse(0);

        for (int digit = 1; maxElement/digit > 1; digit = digit * 10) {
            input = CountSort.sort(input, digit);
        }

        System.out.print("Sorted Array is ");
        IntStream.of(input).forEach(val -> System.out.print(" " + val));
    }
}
