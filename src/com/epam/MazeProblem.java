package com.epam;

public class MazeProblem {
    public static void main(String[] args) {
        int[][] mazeTable = new int[5][5];
        int[] xMoves = new int[]{1, 0, -1, 0};
        int[] yMoves = new int[]{0, 1, 0, -1};
        initializeMazeTable(mazeTable);

        mazeTable[0][0] = 1;
        if (solve(mazeTable, xMoves, yMoves, 0, 0)) {
            System.out.println("Solution exists");
            printSolution(mazeTable);
        } else {
            System.out.println("Feasible solution doesn't exists");
        }

    }

    private static void printSolution(int[][] mazeTable) {
        for (int[] ints : mazeTable) {
            for (int colIndex = 0; colIndex < mazeTable.length; colIndex++) {
                if (ints[colIndex] == 1 || ints[colIndex] == Integer.MAX_VALUE) {
                    System.out.print(" 1 ");
                } else {
                    System.out.print(" * ");
                }
            }
            System.out.println();
        }
    }

    private static boolean solve(int[][] mazeTable, int[] xMoves, int[] yMoves, int xMove, int yMove) {
        if(xMove == mazeTable.length -1 && yMove == mazeTable.length -1){
            return true;
        }

        for (int move = 0; move < 4; move++) {
            int xIndex = xMove + xMoves[move];
            int yIndex = yMove + yMoves[move];
            if (isValidMove(mazeTable, xIndex, yIndex)) {
                mazeTable[xIndex][yIndex] = 1;
                if (solve(mazeTable, xMoves, yMoves, xIndex, yIndex)) {
                    return true;
                }
                mazeTable[xIndex][yIndex] = 0;
            }
        }
        return false;
    }

    private static boolean isValidMove(int[][] mazeTable, int xIndex, int yIndex) {
        return xIndex >= 0 && xIndex < mazeTable.length && yIndex >= 0 && yIndex < mazeTable.length && mazeTable[xIndex][yIndex] != -1 && mazeTable[xIndex][yIndex] != 1;
    }

    private static void initializeMazeTable(int[][] mazeTable) {
        mazeTable[0][3] = -1;
        mazeTable[1][0] = -1;
        mazeTable[1][1] = -1;
        //   mazeTable[1][3] = -1;
        mazeTable[2][3] = -1;
        mazeTable[3][0] = -1;
        mazeTable[3][1] = -1;
        mazeTable[3][2] = -1;
        mazeTable[3][3] = -1;
        mazeTable[4][3] = -1;
    }
}
