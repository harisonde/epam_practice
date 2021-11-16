package com.epam;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class DuplicatesInArray {
    public static void main(String[] args) {
        int[] input = new int[]{4, 4, 1, 2, 1, 3, 5, 12, 12};

        OptionalInt maxValue = Arrays.stream(input).max();

        findDuplicates(input, maxValue.getAsInt());
        findDuplicates1();
    }

    private static void findDuplicates(int[] input, int maxValue) {
        int[] outputArray = new int[maxValue + 1];
        Arrays.fill(outputArray, 1);

        for (int element : input) {
            int val = Math.abs(element);
            if (outputArray[val] < 0) {
                System.out.println("Number " + Math.abs(element) + " duplicate ");
            } else {
                outputArray[val] = -1 * outputArray[val];
            }
        }
    }

    private static void findDuplicates1() {
        int[] numRay = {0, 4, 3, 2, 7, 8, 2, 3, 12, 12, 8, 12, 12, 12};

        IntStream.range(0, numRay.length).forEach(index -> {
            int indexToUpdate = numRay[index] % numRay.length;
            numRay[indexToUpdate] = numRay[indexToUpdate] + numRay.length;
        });

        System.out.println("The repeating elements are : ");
        for (int i = 0; i < numRay.length; i++) {
            if (numRay[i] >= numRay.length * 2) {
                System.out.print(i + " ");
            }
        }
    }
}
