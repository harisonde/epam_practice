package com.epam;

public class EggDropProblem {
    public static void main(String[] args) {
        int numberOfEggs = 10;
        int numberOfFloors = 100;
        int[][] eggMatrix = new int[numberOfEggs + 1][numberOfFloors + 1];

        solve(numberOfEggs, numberOfFloors, eggMatrix);

        System.out.println("Minimum number of trials required to find the floor is " + eggMatrix[numberOfEggs][numberOfFloors]);
    }

    private static void solve(int numberOfEggs, int numberOfFloors, int[][] eggMatrix) {
        for (int floor = 1; floor <= numberOfFloors; floor++) {
            eggMatrix[1][floor] = floor;
        }

        for (int egg = 1; egg <= numberOfEggs; egg++) {
            eggMatrix[egg][0] = 0;
            eggMatrix[egg][1] = 1;
        }

        for (int egg = 2; egg <= numberOfEggs; egg++) {
            for (int currentFloor = 2; currentFloor <= numberOfFloors; currentFloor++) {
                eggMatrix[egg][currentFloor] = Integer.MAX_VALUE;

                for (int droppingFloor = 1; droppingFloor <= currentFloor; droppingFloor++) {
                    int res = 1 + Math.max(eggMatrix[egg - 1][droppingFloor - 1], eggMatrix[egg][currentFloor - droppingFloor]);

                    if (res < eggMatrix[egg][currentFloor]) {
                        eggMatrix[egg][currentFloor] = res;
                    }
                }
            }
        }
    }
}
