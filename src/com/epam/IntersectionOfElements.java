package com.epam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntersectionOfElements {

    public static void main(String... args) {
        int[] input1 = new int[]{10, 12, 15, 89, 100, 176};
        int[] input2 = new int[]{6, 23, 89, 176};

        Integer[] result = solve(input1, input2);

        if (result.length == 2) {
            System.out.println("Interception of elements are " + Arrays.stream(result).collect(Collectors.toList()));
        } else {
            System.out.println("No Interception of elements found");
        }

        result = merge(input1, input2);

        System.out.println("Merged elements are " + Arrays.stream(result).collect(Collectors.toList()));

    }

    private static Integer[] solve(int[] input1, int[] input2) {
        List<Integer> result = new ArrayList<>();

        int firstArrayIndex = 0, secondArrayIndex = 0;

        while (firstArrayIndex < input1.length && secondArrayIndex < input2.length) {

            if (input1[firstArrayIndex] == input2[secondArrayIndex]) {
                result.add(input1[firstArrayIndex]);
                firstArrayIndex++;
                secondArrayIndex++;
            } else {
                if (input1[firstArrayIndex] > input2[secondArrayIndex]) {
                    secondArrayIndex++;
                } else {
                    firstArrayIndex++;
                }
            }
        }

        return result.toArray(new Integer[0]);
    }

    private static Integer[] merge(int[] input1, int[] input2) {
        List<Integer> result = new ArrayList<>();

        int firstArrayIndex = 0, secondArrayIndex = 0;

        while (firstArrayIndex < input1.length && secondArrayIndex < input2.length) {

            if (input1[firstArrayIndex] == input2[secondArrayIndex]) {
                result.add(input1[firstArrayIndex]);
                firstArrayIndex++;
                secondArrayIndex++;
            } else {
                if (input1[firstArrayIndex] > input2[secondArrayIndex]) {
                    result.add(input2[secondArrayIndex++]);
                } else {
                    result.add(input1[firstArrayIndex++]);
                }
            }
        }

        return result.toArray(new Integer[0]);
    }
}
