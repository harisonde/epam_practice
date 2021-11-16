package com.epam;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SumOfSubSets {
    public static void main(String[] args){

        sumOfSubSet(new int[]{5, 10, 20, 15, 25}, 25);
    }

    private static void sumOfSubSet(int[] input, int requiredSum){
        sumOfSubSet(input,requiredSum, 0, 0,  new int[input.length], Arrays.stream(input).sum());
    }

    private static void sumOfSubSet(int[] inputElements, int requiredWeight, int currentWeight, int index,
                                    int[] selectedValues, int remainingWeight) {

        if (index < inputElements.length && requiredWeight <= remainingWeight) {
            selectedValues[index] = 1;
            if (inputElements[index] + currentWeight == requiredWeight) {
                printSubsetSum(selectedValues, inputElements);

            } else if (inputElements[index] + currentWeight <= requiredWeight) {
                sumOfSubSet(inputElements, requiredWeight, currentWeight + inputElements[index],
                        index + 1, selectedValues, remainingWeight - inputElements[index]);
            }

            selectedValues[index] = 0;
            sumOfSubSet(inputElements, requiredWeight, currentWeight, index + 1, selectedValues, remainingWeight);
        }
    }

    private static void printSubsetSum(int[] selectedValues, int[] inputElements) {
        IntStream.range(0, selectedValues.length)
                .filter(val -> selectedValues[val] == 1)
                .forEach(index -> System.out.print(" " + inputElements[index] + " "));

        System.out.println();

    }

}
