package com.epam;

import java.util.Arrays;

public class ColoringProblem {
    public static void main(String[] args){
        int noOfVertex = 6;
        int[][] graph = new int[noOfVertex][noOfVertex];
        int[] solution = new int[noOfVertex];
        Arrays.fill(solution, Integer.MIN_VALUE);

        initializeGraph(graph);

        if(solve(graph, noOfVertex, solution,0, 3)){
            System.out.println("Feasible solution exists");
            printSolution(solution);
        } else{
            System.out.println("No Feasible solution exists");
        }
    }

    private static boolean solve(int[][] graph, int noOfVertex, int[] solution, int vertexNumber, int noOfColors) {
        if (vertexNumber == noOfVertex) {
            return true;
        }

        for (int colorIndex = 0; colorIndex < noOfColors; colorIndex++) {
            if(validColor(graph, vertexNumber, colorIndex, solution)){
                solution[vertexNumber] = colorIndex;

                if(solve(graph, noOfVertex, solution,vertexNumber + 1, noOfColors)){
                    return true;
                }

                solution[vertexNumber] = Integer.MIN_VALUE;
            }
        }
        return false;
    }

    private static boolean validColor(int[][] graph, int vertexNumber, int colorIndex, int[] solution) {
        for(int vertexCol = 0; vertexCol < graph.length; vertexCol++){
            if(graph[vertexNumber][vertexCol] == 1 && solution[vertexCol] == colorIndex){
                return false;
            }
        }
        return  true;
    }

    private static void printSolution(int[] solution) {
        Arrays.stream(solution)
                .forEach(color -> System.out.print(" " + color));
    }

    private static void initializeGraph(int[][] graph) {
        graph[0][1] = 1;
        graph[1][0] = 1;
        graph[0][2] = 1;
        graph[2][0] = 1;
        graph[0][3] = 1;
        graph[3][0] = 1;

        graph[1][2] = 1;
        graph[2][1] = 1;
        graph[1][4] = 1;
        graph[4][1] = 1;
        graph[1][5] = 1;
        graph[5][1] = 1;

        graph[2][3] = 1;
        graph[3][2] = 1;
        graph[2][5] = 1;
        graph[5][2] = 1;

        graph[3][5] = 1;
        graph[5][3] = 1;

        graph[4][5] = 1;
        graph[5][4] = 1;
    }
}
