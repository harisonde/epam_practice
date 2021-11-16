package com.epam;

import java.util.ArrayList;
import java.util.List;

public class Knapsack {

    public static void main(String[] args){
        int[] weights = new int[]{4, 2, 3};
        int[] values = new int[]{10, 4, 7};
        System.out.println("Total value to be included in Knapsack is " +maxWeight1(weights, values,5, 0));
        System.out.println("Total value to be included in Knapsack is " +maxWeight1(weights, values,2, 0));
        System.out.println("Total value to be included in Knapsack is " +maxWeight(weights, values,5));
        System.out.println("Total value to be included in Knapsack is " +maxWeight(weights, values,2));
        System.out.println("Total value to be included in Knapsack using dynamic program with knapsack weight 6 is ->  "
                +maxWeightDynamicProgram(weights, values,6));
        System.out.println("Total value to be included in Knapsack using dynamic program with knapsack weight 5 is->  "
                +maxWeightDynamicProgram(weights, values,5));
    }

    private static int maxWeight(int[] weights, int[] values, int totalKnapsackWeight) {
        int[][] knapsackTable = new int[weights.length + 1][totalKnapsackWeight + 1];

        for (int itemIndex = 1; itemIndex <= weights.length; itemIndex++) {
            int itemWeight = weights[itemIndex-1];
            for (int knapsackWeight = 1; knapsackWeight <= totalKnapsackWeight; knapsackWeight++) {
                if (itemWeight <= knapsackWeight) {
                    knapsackTable[itemIndex][knapsackWeight] = Math.max(knapsackTable[itemIndex - 1][knapsackWeight],
                            values[itemIndex - 1] + knapsackTable[itemIndex][knapsackWeight - itemWeight]);
                } else {
                    knapsackTable[itemIndex][knapsackWeight] = knapsackTable[itemIndex - 1][knapsackWeight];
                }
            }
        }

        printItemsToInclude(knapsackTable, totalKnapsackWeight, weights);
        return knapsackTable[weights.length][totalKnapsackWeight];
    }

    private static void printItemsToInclude(int[][] knapsackTable, int totalKnapsackWeight, int[] weights) {
        System.out.println("Max values from Knapsack is  " + knapsackTable[weights.length][totalKnapsackWeight]);
        System.out.println("Weights included are ");
        for (int itemIndex = weights.length, knapsackWeight = totalKnapsackWeight; itemIndex > 0; itemIndex--) {
            if (knapsackTable[itemIndex][knapsackWeight] != 0 && knapsackTable[itemIndex - 1][knapsackWeight] != knapsackTable[itemIndex][knapsackWeight]) {
                System.out.println("Wight # " + itemIndex);
                knapsackWeight = knapsackWeight - weights[itemIndex - 1 ];
            }
        }
    }

    private static int maxWeight1(int[] weights, int[] values, int totalKnapsackWeight, int currentIndex) {
        if (weights.length <= currentIndex || values.length <= currentIndex) {
            return 0;
        }

        if (weights[currentIndex] > totalKnapsackWeight) {
            return maxWeight1(weights, values, totalKnapsackWeight, currentIndex + 1);
        } else {
            int maxValueIncluding = values[currentIndex] + maxWeight1(weights, values, totalKnapsackWeight - weights[currentIndex], currentIndex + 1);
            int maxValueExcluding = maxWeight1(weights, values, totalKnapsackWeight, currentIndex + 1);

            return Math.max(maxValueExcluding, maxValueIncluding);
        }
    }

    private static int maxWeightDynamicProgram(int[] weights, int[] values, int totalKnapsackWeight) {
        int[][] resultsTable = new int[weights.length + 1][totalKnapsackWeight + 1];

        for (int weightIndex = 1; weightIndex <= weights.length; weightIndex++) {
            int currentWeight = weights[weightIndex - 1];
            int currentValue = values[weightIndex - 1];

            for (int knapSackWeight = 1; knapSackWeight <= totalKnapsackWeight; knapSackWeight++) {
                if (knapSackWeight < currentWeight) {
                    resultsTable[weightIndex][knapSackWeight] = resultsTable[weightIndex - 1][knapSackWeight];
                } else {
                    int remWeightIndex = Math.max(knapSackWeight - currentWeight, 0);
                    resultsTable[weightIndex][knapSackWeight] = Math.max(
                            currentValue + resultsTable[weightIndex][remWeightIndex],
                            resultsTable[weightIndex - 1][knapSackWeight]
                    );
                }
            }
        }

        printKnapsackWeightsToInclude(resultsTable, weights, totalKnapsackWeight);
        return resultsTable[weights.length][totalKnapsackWeight];
    }

    private static void printKnapsackWeightsToInclude(int[][] resultsTable, int[] weights, int totalKnapsackWeight) {
        int currentIndex = weights.length;
        int remainingKnapsackWeight = totalKnapsackWeight;
        List<Integer> elementsIncludedInKnapsack = new ArrayList<>();

        while(currentIndex > 0 && remainingKnapsackWeight > 0){
            int currentWeight = weights[currentIndex - 1];

            if(resultsTable[currentIndex][totalKnapsackWeight] != resultsTable[currentIndex -1][totalKnapsackWeight]){
                elementsIncludedInKnapsack.add(weights[currentIndex - 1]);
                remainingKnapsackWeight = totalKnapsackWeight - currentWeight;
            }
            currentIndex = currentIndex - 1;
        }

        System.out.println("Elements to be included are " + elementsIncludedInKnapsack);

    }
}
