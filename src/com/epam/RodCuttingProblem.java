package com.epam;

public class RodCuttingProblem {
    public static void main(String[] args) {
        int rodLength = 5;
        int[] pieceLengths = new int[]{1, 2, 3, 4, 5};
        int[] pieceValues = new int[]{2, 5, 7, 3, 50};
        int[][] rodCuttingTable = new int[rodLength + 1][rodLength + 1];

        solve(rodLength, rodCuttingTable, pieceLengths, pieceValues);
        printSolution(rodCuttingTable, 5, 5);
        printSolution(rodCuttingTable, 4, 4);

        rodLength = 8;
        pieceLengths = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        pieceValues = new int[]{1, 5, 8, 9, 10, 17, 17, 20};
        rodCuttingTable = new int[pieceLengths.length + 1][rodLength + 1];

        solve1(rodLength, rodCuttingTable, pieceLengths, pieceValues);
    }

    private static void solve(int totalRodLength, int[][] rodCuttingTable, int[] pieceLengths, int[] pieceValues) {
        for (int pieceIndex = 1; pieceIndex <= pieceLengths.length; pieceIndex++) {
            int pieceLength = pieceLengths[pieceIndex - 1];
            int pieceValue = pieceValues[pieceIndex - 1];

            for (int currentRodLength = 1; currentRodLength <= totalRodLength; currentRodLength++) {
                if (pieceLength <= currentRodLength) {
                    rodCuttingTable[pieceIndex][currentRodLength] = Math.max(rodCuttingTable[pieceIndex - 1][currentRodLength],
                            pieceValue + rodCuttingTable[pieceIndex][Math.max(0, currentRodLength - pieceLength)]);
                } else {
                    rodCuttingTable[pieceIndex][currentRodLength] = rodCuttingTable[pieceIndex - 1][currentRodLength];
                }
            }
        }
    }

    private static void solve1(int totalRodLength, int[][] rodCuttingTable, int[] pieceLengths, int[] pieceValues) {
        for (int pieceIndex = 0; pieceIndex < pieceLengths.length; pieceIndex++) {
            for (int rodLength = 1; rodLength <= totalRodLength; rodLength++) {

                if (pieceLengths[pieceIndex] > rodLength) {
                    rodCuttingTable[pieceIndex + 1][rodLength] = rodCuttingTable[pieceIndex][rodLength];
                } else {
                    int pieceLength = pieceLengths[pieceIndex];
                    int value = Math.max(pieceValues[pieceIndex] + rodCuttingTable[pieceIndex + 1][Math.max(rodLength - pieceLength, 0)]
                            , rodCuttingTable[pieceIndex][rodLength]);
                    rodCuttingTable[pieceIndex + 1][rodLength] = value;
                }
            }
        }

       printSolution1(rodCuttingTable, totalRodLength, pieceLengths.length);
    }

    private static void printSolution1(int[][] rodCuttingTable, int rodLength, int numberOfPieces) {
        System.out.print("Max value derived is " + rodCuttingTable[numberOfPieces][rodLength] + " with including following number of pieces in solution ");

        int remRodLength = rodLength;
        while(numberOfPieces > 0 && remRodLength > 0){
            if(rodCuttingTable[numberOfPieces][remRodLength] != rodCuttingTable[numberOfPieces - 1][remRodLength]){
                System.out.print(" " + numberOfPieces);
                remRodLength = remRodLength - numberOfPieces;
            } else {
                --numberOfPieces;
            }
        }
        System.out.println();
    }

    private static void printSolution(int[][] rodCuttingTable, int rodLength, int numberOfPieces) {
        System.out.print("Max value derived is " + rodCuttingTable[numberOfPieces][rodLength] + " with including following number of pieces in solution ");
        for (int pieceNumber = numberOfPieces, currentRodLength = rodLength; pieceNumber > 0; pieceNumber--) {
            while (rodCuttingTable[pieceNumber][currentRodLength] != rodCuttingTable[pieceNumber - 1][currentRodLength] && currentRodLength > 0) {
                System.out.print(" " + pieceNumber);
                currentRodLength = currentRodLength - pieceNumber;
            }
        }
        System.out.println("");
    }
}
