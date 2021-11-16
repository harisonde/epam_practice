package com.epam;

public class CoinChangeProblem {

    public static void main(String[] args) {

        int[] availableCoins = new int[]{1, 2, 3};
        int totalValue = 4;

        int numberOfWays = solve(availableCoins, totalValue);

        System.out.println("There are " + numberOfWays + " ways possible for total of " + totalValue + " with coins 1, 2, 3");

        availableCoins = new int[]{2, 5, 3, 6};
        totalValue = 10;

        numberOfWays = solve(availableCoins, totalValue);

        System.out.println("There are " + numberOfWays + " ways possible for total of " + totalValue + " with coins 2, 5, 3, 6");

    }

    private static int solve(int[] availableCoins, int totalValue) {
        int[][] solution = new int[availableCoins.length + 1][totalValue + 1];

        for (int coinIndex = 1; coinIndex <= availableCoins.length; coinIndex++) {
            int currentCoin = availableCoins[coinIndex - 1];

            for (int currentTotalValue = 1; currentTotalValue <= totalValue; currentTotalValue++) {

                if (currentCoin > currentTotalValue) {
                    solution[coinIndex][currentTotalValue] = solution[coinIndex - 1][currentTotalValue];
                } else {
                    if (currentCoin == currentTotalValue) {
                        solution[coinIndex][currentTotalValue] = 1 + solution[coinIndex - 1][currentTotalValue];
                    } else {
                        solution[coinIndex][currentTotalValue] = solution[coinIndex][currentTotalValue - currentCoin]
                                + solution[coinIndex - 1][currentTotalValue];
                    }
                }
            }
        }

        return solution[availableCoins.length][totalValue];
    }
}
