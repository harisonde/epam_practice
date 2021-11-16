package com.epam;

import java.util.HashMap;
import java.util.Map;

public class Sudoko {
    private static final Map<String, RowColIndex> rowColIndexMap = new HashMap<>();
    private static final int[][] sudokuTable = new int[9][9];

    public static void main(String[] args) {
        initialiseSudokuTable();
        initializeRowColIndex();

        if (solve(0, 0)) {
            System.out.println("Feasible solution exists");
            printSolution();
        } else {
            System.out.println("No feasible solution exists");
        }
    }

    private static boolean solve(int rowIndex, int colIndex) {
        if (rowIndex == 9) {
            return true;
        }

        int nextRowIndex = colIndex == 8 ? rowIndex + 1 : rowIndex;
        int nextColumnIndex = colIndex == 8 ? 0 :  colIndex + 1;

        if(sudokuTable[rowIndex][colIndex] != 0){
            return solve(nextRowIndex, nextColumnIndex);
        }

        for (int value = 1; value < 10; value++) {
            if (isValidValue(rowIndex, colIndex, value)) {
                sudokuTable[rowIndex][colIndex] = value;

                if (solve(nextRowIndex, nextColumnIndex)) {
                    return true;
                }

                sudokuTable[rowIndex][colIndex] = 0;
            }
        }
        return false;
    }

    private static boolean isValidValue(int rowIndex, int colIndex, int value) {
        if (sudokuTable[rowIndex][colIndex] != 0) {
            return false;
        }

        for (int col = 0; col < 9; col++) {
            if (sudokuTable[rowIndex][col] == value) {
                return false;
            }
        }

        for (int row = 0; row < 9; row++) {
            if (sudokuTable[row][colIndex] == value) {
                return false;
            }
        }

        return verifyElementValueWithInaBox(rowIndex, colIndex, value);
    }

    private static boolean verifyElementValueWithInaBox(int rowIndex, int colIndex, int value) {
        int row = getRowColumnIndexForMap(rowIndex);
        int col = getRowColumnIndexForMap(colIndex);

        RowColIndex rowColIndex = rowColIndexMap.get(row + "" + col);

        for (int startRow = rowColIndex.rowStartIndex; startRow <= rowColIndex.rowEndIndex; startRow++) {
            for (int startCol = rowColIndex.colStartIndex; startCol <= rowColIndex.colEndIndex; startCol++) {
                if (sudokuTable[startRow][startCol] == value) {
                   return false;
                }
            }
        }

        return true;
    }

    private static int getRowColumnIndexForMap(int rowOrColumnIndex) {
        if (rowOrColumnIndex > 2 && rowOrColumnIndex < 6) {
            return 1;
        }

        if (rowOrColumnIndex > 5 && rowOrColumnIndex < 9) {
            return 2;
        }
        return 0;
    }

    private static void printSolution() {
        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            if(rowIndex % 3 ==0){
                System.out.println();
            }
            for (int colIndex = 0; colIndex < 9; colIndex++) {
                if(colIndex % 3 ==0){
                    System.out.print(" ");
                }

                System.out.print(" " + sudokuTable[rowIndex][colIndex]);

            }
            System.out.println("");
        }
    }

    private static void initialiseSudokuTable() {
        sudokuTable[0][3] = 2;
        sudokuTable[0][4] = 6;
        sudokuTable[0][6] = 7;
        sudokuTable[0][8] = 1;

        sudokuTable[1][0] = 6;
        sudokuTable[1][1] = 8;
        sudokuTable[1][4] = 7;
        sudokuTable[1][7] = 9;

        sudokuTable[2][0] = 1;
        sudokuTable[2][1] = 9;
        sudokuTable[2][5] = 4;
        sudokuTable[2][6] = 5;

        sudokuTable[3][0] = 8;
        sudokuTable[3][1] = 2;
        sudokuTable[3][3] = 1;
        sudokuTable[3][7] = 4;

        sudokuTable[4][2] = 4;
        sudokuTable[4][3] = 6;
        sudokuTable[4][5] = 2;
        sudokuTable[4][6] = 9;

        sudokuTable[5][1] = 5;
        sudokuTable[5][5] = 3;
        sudokuTable[5][7] = 2;
        sudokuTable[5][8] = 8;

        sudokuTable[6][2] = 9;
        sudokuTable[6][3] = 3;
        sudokuTable[6][7] = 7;
        sudokuTable[6][8] = 4;

        sudokuTable[7][1] = 4;
        sudokuTable[7][4] = 5;
        sudokuTable[7][7] = 3;
        sudokuTable[7][8] = 6;

        sudokuTable[8][0] = 7;
        sudokuTable[8][2] = 3;
        sudokuTable[8][4] = 1;
        sudokuTable[8][5] = 8;
    }

    private static void initializeRowColIndex() {
        rowColIndexMap.put("00", new RowColIndex(0, 2, 0, 2));
        rowColIndexMap.put("01", new RowColIndex(0, 2, 3, 5));
        rowColIndexMap.put("02", new RowColIndex(0, 2, 6, 8));

        rowColIndexMap.put("10", new RowColIndex(3, 5, 0, 2));
        rowColIndexMap.put("11", new RowColIndex(3, 5, 3, 5));
        rowColIndexMap.put("12", new RowColIndex(3, 5, 6, 8));

        rowColIndexMap.put("20", new RowColIndex(6, 8, 0, 2));
        rowColIndexMap.put("21", new RowColIndex(6, 8, 3, 5));
        rowColIndexMap.put("22", new RowColIndex(6, 8, 6, 8));
    }

    private static class RowColIndex {
        int rowStartIndex;
        int rowEndIndex;
        int colStartIndex;
        int colEndIndex;

        private RowColIndex(int rowStartIndex, int rowEndIndex, int colStartIndex, int colEndIndex) {
            this.rowStartIndex = rowStartIndex;
            this.rowEndIndex = rowEndIndex;
            this.colStartIndex = colStartIndex;
            this.colEndIndex = colEndIndex;
        }
    }
}
