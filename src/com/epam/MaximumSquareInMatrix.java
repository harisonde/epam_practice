package com.epam;

public class MaximumSquareInMatrix {

    public static void main(String... args) {
        int[][] matrix = getInputMatrix();

        printSquareMatrix(matrix);
    }

    private static void printSquareMatrix(int[][] matrix) {
        int[][] solutionMatrix = new int[matrix.length + 1][matrix[0].length + 1];
        int maxSquareVal = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                if (matrix[row][column] == 1) {
                    solutionMatrix[row + 1][column + 1] = matrix[row][column]
                            + Math.min(solutionMatrix[row][column + 1], Math.min(solutionMatrix[row + 1][column],
                            solutionMatrix[row][column]));
                    maxSquareVal = Math.max(maxSquareVal, solutionMatrix[row + 1][column + 1]);
                }
            }
        }

        printResults(maxSquareVal);
    }

    private static void printResults(int maxSquareVal) {
        if (maxSquareVal > 0) {
            System.out.println("Maximum square found in matrix is as follows");
            for (int row = 0; row < maxSquareVal; row++) {
                for (int column = 0; column < maxSquareVal; column++) {
                    System.out.print(" 1 ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Maximum square is not found in matrix");
        }
    }

    private static int[][] getInputMatrix() {
        int[][] matrix = new int[6][5];

        matrix[0][1] = 1;
        matrix[0][2] = 1;
        matrix[0][4] = 1;

        matrix[1][0] = 1;
        matrix[1][1] = 1;
        matrix[1][3] = 1;

        matrix[2][1] = 1;
        matrix[2][2] = 1;
        matrix[2][3] = 1;

        matrix[3][0] = 1;
        matrix[3][1] = 1;
        matrix[3][2] = 1;
        matrix[3][3] = 1;

        matrix[4][0] = 1;
        matrix[4][1] = 1;
        matrix[4][2] = 1;
        matrix[4][3] = 1;
        matrix[4][4] = 1;

        return matrix;
    }
}
