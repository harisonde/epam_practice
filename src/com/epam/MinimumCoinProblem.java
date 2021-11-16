package com.epam;

import java.util.Arrays;

public class MinimumCoinProblem {

    public static void main(String[] args) {
        int[] coins = new int[]{9, 6, 5, 1};
        int totalValue = 11;

        solve(coins, totalValue);

        coins = new int[]{10, 2, 1, 8, 9, 4};
        totalValue = 15;

        solve(coins, totalValue);
    }

    private static void solve(int[] coins, int totalValue) {
        int[] solution = new int[totalValue + 1];
        int[] result = new int[totalValue + 1];

        Arrays.fill(solution, Integer.MAX_VALUE - 1);
        Arrays.fill(result, -1);

        solution[0] = 0;e
        result[0] = 0;

        for (int coinIndex = 0; coinIndex < coins.length; coinIndex++) {
            int currentCoinValue = coins[coinIndex];

            for (int currentValue = 1; currentValue <= totalValue; currentValue++) {
                if (currentValue >= currentCoinValue) {
                    if (solution[currentValue - currentCoinValue] + 1 < solution[currentValue]) {
                        solution[currentValue] = 1 + solution[currentValue - currentCoinValue];
                        result[currentValue] = coinIndex;
                    }
                }
            }
        }


        printElementsToInclude(solution, result, coins, totalValue);
    }

    private static void printElementsToInclude(int[] solution, int[] result, int[] coins, int totalValue) {
        if (result[totalValue] != -1) {
            System.out.println();
            System.out.println("Minimum coins required to form value " + totalValue + " is " + solution[totalValue] + " And Coins are ");

            int start = totalValue;

            while (start != 0) {
                System.out.print(" " + coins[result[start]]);
                start = start - coins[result[start]];
            }
        }
    }
}

