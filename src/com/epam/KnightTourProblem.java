package com.epam;

public class KnightTourProblem {
    private static final int[] xMoves = new int[]{2, 1, -2, -1, 2, 1, -2, -1};
    private static final int[] yMoves = new int[]{1, 2, 1, 2, -1, -2, -1, -2};


    private static final int[][] solutionMatrix = new int[8][8];

    public static void main(String[] args) {
        initializeSolutionMatrix();
        solutionMatrix[0][0] = 1;

        if (solve(0, 0, 1)) {
            System.out.println("Solution Exists ");
            printSolution();
        } else {
            System.out.println("Solution doesn't exists");
        }
    }

    private static boolean solve(int xIndex, int yIndex, int moveNumber) {
        if (moveNumber == solutionMatrix.length * solutionMatrix.length) {
            return true;
        }

        for (int strategy = 0; strategy < 8; strategy++) {
            int nextMoveX = xIndex + xMoves[strategy];
            int nextMoveY = yIndex + yMoves[strategy];
            if (isValidMove(nextMoveX, nextMoveY)) {
                solutionMatrix[nextMoveX][nextMoveY] = moveNumber + 1;

                if (solve(nextMoveX, nextMoveY, moveNumber + 1)) {
                    return true;
                }

                solutionMatrix[nextMoveX][nextMoveY] = Integer.MIN_VALUE;
            }
        }
        return false;
    }

    private static boolean solve1(int xIndex, int yIndex, int moveNumber) {
        if (moveNumber == solutionMatrix.length * solutionMatrix.length) {
            return true;
        }

        for (int strategy = 0; strategy < 8; strategy++) {
            if (isValid(xIndex, yIndex, strategy)) {
                solutionMatrix[xIndex + xMoves[strategy]][yIndex + yMoves[strategy]] = moveNumber + 1;
                if (solve1(xIndex + xMoves[strategy], yIndex + yMoves[strategy], moveNumber + 1)) {
                    return true;
                }

                solutionMatrix[xIndex + xMoves[strategy]][yIndex + yMoves[strategy]] = Integer.MIN_VALUE;
            }
        }

        return false;
    }

    private static boolean isValid(int xIndex, int yIndex, int strategy) {
        if (xIndex + xMoves[strategy] < 0 || yMoves[strategy] + yIndex < 0
                || xIndex + xMoves[strategy] >= solutionMatrix.length
                || yIndex + yMoves[strategy] >= solutionMatrix.length) {
            return false;
        }

        return solutionMatrix[xIndex + xMoves[strategy]][yIndex + yMoves[strategy]] == Integer.MIN_VALUE;
    }

    private static boolean isValidMove(int nextMoveX, int nextMoveY) {
        return nextMoveX >= 0 && nextMoveY >= 0 && nextMoveX < solutionMatrix.length
                && nextMoveY < solutionMatrix.length && solutionMatrix[nextMoveX][nextMoveY] == Integer.MIN_VALUE;
    }

    private static void printSolution() {
        for (int rowIndex = 0; rowIndex < solutionMatrix.length; rowIndex++) {
            for (int colIndex = 0; colIndex < solutionMatrix.length; colIndex++) {
                System.out.print("  " + solutionMatrix[rowIndex][colIndex]);
            }
            System.out.println("");
        }
    }

    private static void initializeSolutionMatrix() {
        for (int rowIndex = 0; rowIndex < solutionMatrix.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < solutionMatrix.length; columnIndex++) {
                solutionMatrix[rowIndex][columnIndex] = Integer.MIN_VALUE;
            }
        }
    }
}
