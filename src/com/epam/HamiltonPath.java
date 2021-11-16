package com.epam;

import java.util.stream.IntStream;

public class HamiltonPath {
    public static void main(String[] args){
        int numberOfVerticies = 4;
        int[][] verticesMetrics = new int[4][4];
       /* int numberOfVerticies = 2;
        int[][] verticesMetrics = new int[2][2];*/
        int[] hamiltonPath = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        //int[] hamiltonPath = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};

        boolean[] visitedInd = new boolean[numberOfVerticies];

        verticesMetrics[0][2] = 1;
        verticesMetrics[2][0] = 1;
        verticesMetrics[2][1] = 1;
        verticesMetrics[1][2] = 1;
        verticesMetrics[1][3] = 1;
        verticesMetrics[3][1] = 1;
        verticesMetrics[3][0] = 1;
        verticesMetrics[0][3] = 1;

       /* verticesMetrics[0][1] = 1;
        verticesMetrics[1][0] = 1;*/

       /* [0, 1, 0, 0]
        [0, 0, 1, 0]
        [0, 0, 1, 0]
        [1, 0, 0, 0]*/

        if(findFeasibleSolution(verticesMetrics, hamiltonPath, 0, numberOfVerticies, 1)){
            System.out.println("Hamilton Path cycle does exists ");
            printPath(hamiltonPath);
        } else {
            System.out.println("Hamilton Path cycle doesn't exists");
        }

        hamiltonPath = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

        if(findFeasibleSolution1(verticesMetrics, hamiltonPath, 0, numberOfVerticies, 0, visitedInd)){
            System.out.println("\n\nHamilton Path cycle does exists ");
            printPath(hamiltonPath);
        } else {
            System.out.println("Hamilton Path cycle doesn't exists");
        }
    }

    private static boolean findFeasibleSolution(int[][] verticesMetrics, int[] hamiltonPath, int currentVertex, int numberOfVerticies, int position) {
        if (position == numberOfVerticies  ) {
            if (verticesMetrics[currentVertex][0] == 1) {
                hamiltonPath[position - 1] = currentVertex;
                return true;
            } else {
                return false;
            }
        }

        for (int vertexIndex = 0; vertexIndex < numberOfVerticies; vertexIndex++) {
            if (isValidEdge(verticesMetrics[currentVertex][vertexIndex], hamiltonPath, vertexIndex)) {
                hamiltonPath[position - 1] = currentVertex;
                if (findFeasibleSolution(verticesMetrics, hamiltonPath, vertexIndex, numberOfVerticies, ++position)) {
                    return true;
                }
                hamiltonPath[position - 1] = Integer.MIN_VALUE;
            }

        }

        return false;
    }

    private static boolean findFeasibleSolution1(int[][] verticesMetrics, int[] hamiltonPath, int currentVertex,
                                                 int numberOfVerticies, int position, boolean[] visitedInd) {
        if (position == numberOfVerticies -1 ) {
             if(verticesMetrics[currentVertex][hamiltonPath[0]] == 1 ){
                 hamiltonPath[position] = currentVertex;
                 return true;
             } else{
                 return false;
             }
        }

        for (int vertexIndex = 0; vertexIndex < numberOfVerticies; vertexIndex++) {
            if (isValidEdge1(verticesMetrics[currentVertex][vertexIndex],vertexIndex, visitedInd)) {
                hamiltonPath[position] = currentVertex;
                visitedInd[currentVertex] = true;
                if (findFeasibleSolution1(verticesMetrics, hamiltonPath, vertexIndex, numberOfVerticies, ++position, visitedInd)) {
                    return true;
                }
                hamiltonPath[position] = Integer.MIN_VALUE;
                visitedInd[currentVertex] = false;
            }

        }

        return false;
    }

    private static boolean isValidEdge(int edge, int[] hamiltonPath, int vertexIndex) {
        return edge == 1 && IntStream.range(0, hamiltonPath.length).filter(val -> hamiltonPath[val] == vertexIndex).findFirst().orElse(Integer.MIN_VALUE) == Integer.MIN_VALUE;
    }

    private static boolean isValidEdge1(int edge, int vertexIndex, boolean[] visitedInd) {
        return edge == 1 && !visitedInd[vertexIndex];
    }

    private static void printPath(int[] hamiltonPath) {
        System.out.print("Hamilton Path Cycle is ");

        IntStream.range(0, hamiltonPath.length)
                .forEach(index -> System.out.print(" " + hamiltonPath[index] + " "));

        System.out.print(" " + hamiltonPath[0]);
    }
}
