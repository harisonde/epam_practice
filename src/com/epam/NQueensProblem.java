package com.epam;

public class NQueensProblem {
    public static void main(String[] args) {
        System.out.println("Backtracking examples");
        //testIt();
        queensProblem(4);
        queensProblem(5);
    }

    private static void queensProblem(int size) {
        int[][] queensArray = new int[size][size];
        if (!solve(queensArray, 0)) {
            System.out.println("There is no Solution!!!");
        } else {
            System.out.println("There is a solution");
            printQueenAllocation(queensArray);
        }
    }

    private static void printQueenAllocation(int[][] queensArray) {
        for (int i = 0; i < queensArray.length; i++) {
            for (int j = 0; j < queensArray.length; j++) {
                if (queensArray[i][j] == 1) {
                    System.out.print(" * ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }

    private static boolean solve(int[][] queensArray, int colIndex) {
        if (colIndex >= queensArray.length) {
            return true;
        }

        for (int rowIndex = 0; rowIndex < queensArray.length; rowIndex++) {
            if (isValid(queensArray, rowIndex, colIndex)) {
                queensArray[rowIndex][colIndex] = 1;

                if (solve(queensArray, colIndex + 1)) {
                    return true;
                }
                queensArray[rowIndex][colIndex] = 0;
            }
        }

        return false;
    }

    static boolean isValid(int[][] queensArray, int rowIndex, int colIndex) {
        for (int i = 0; i < colIndex; i++) {
            if (queensArray[rowIndex][i] == 1) {
                return false;
            }
        }

        /* Check lower diagonal on left side */
        for (int i = rowIndex, j = colIndex; i < queensArray.length && j >= 0; i++, j--) {
            if (queensArray[i][j] == 1) {
                return false;
            }
        }

        /* Check upper diagonal on left side */
        for (int i = rowIndex, j = colIndex; i >= 0 && j >= 0; i--, j--) {
            if (queensArray[i][j] == 1) {
                return false;
            }
        }
        return true;
    }
}
