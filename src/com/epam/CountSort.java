package com.epam;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CountSort {
    public static void main(String[] args) {
        int[] outputArray = sort(new int[]{9, 4, 8, 5, 4, 3, 9, 0, 6, 1}, 1);
        System.out.print("Sorted Array is ");
        IntStream.of(outputArray).forEach(val -> System.out.print(" " + val));

        System.out.println();
        int[] ints = sort1(new int[]{9, 4, 8, 5, 4, 3, 9, 0, 6, 1}, 1);
        System.out.print("Sorted Array is ");
        Arrays.stream(ints).forEach(ele -> System.out.print(" " + ele));
        System.out.println();
    }

    private static int[] sort1(int[] input, int digitPos) {

        int[] output = new int[input.length];

        int[] positions = new int[10];

        for (int eleIdx : input) {
            positions[eleIdx] = positions[eleIdx] + 1;
        }

        int outputIdx = 0;
        for (int index = 0; index < 10; index++) {
            while (positions[index] > 0) {
                output[outputIdx++] = index;
                --positions[index];
            }
        }

        return output;
    }


    public static int[] sort(int[] input, int digit) {
        int[] count = new int[10];

        for (int element : input) {
            int countIndex = (element / digit) % 10 ;
            count[countIndex] = count[countIndex] + 1;
        }

        int[] indexArray = new int[10];
        indexArray[0] = count[0];

        for (int index = 1; index < count.length; index++) {
            indexArray[index] = count[index] + indexArray[index - 1];
        }

        int[] outputArray = new int[input.length];

        for (int index = input.length -1; index >=0; index--) {
            int elementIndex = indexArray[(input[index] / digit) % 10];
            indexArray[(input[index] / digit) % 10] = indexArray[(input[index] / digit) % 10] - 1;

            outputArray[elementIndex - 1] = input[index];
        }

        return outputArray;
    }
}
