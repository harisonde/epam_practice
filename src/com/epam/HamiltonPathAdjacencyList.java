package com.epam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HamiltonPathAdjacencyList {

    public static void main(String... args) {

        List<List<Integer>> graph = getGraphData();
        Integer[] hamiltonPath = new Integer[graph.size() + 1];
        Arrays.fill(hamiltonPath, Integer.MIN_VALUE);

        if (findHamiltonPath(graph, hamiltonPath, 0, 0)) {
            System.out.println("Hamilton path found ");
            Arrays.stream(hamiltonPath).forEach(element -> System.out.print(" " + element));
        } else {
            System.out.println("Hamilton path not found!!!");
        }
    }

    private static boolean findHamiltonPath(List<List<Integer>> graph, Integer[] hamiltonPath, int currentVertex,
                                            int numberOfVertexAdded) {
        if (numberOfVertexAdded == hamiltonPath.length - 1 && hamiltonPath[0] == currentVertex) {
            hamiltonPath[numberOfVertexAdded] = currentVertex;
            return true;
        }

        if (!isValidToAdd(hamiltonPath, currentVertex)) {
            return false;
        }

        hamiltonPath[numberOfVertexAdded] = currentVertex;

        for (int vertex : graph.get(currentVertex)) {
            if (findHamiltonPath(graph, hamiltonPath, vertex, numberOfVertexAdded + 1)) {
                return true;
            }
        }

        hamiltonPath[numberOfVertexAdded + 1] = Integer.MIN_VALUE;
        return false;
    }

    private static boolean isValidToAdd(Integer[] hamiltonPath, int currentVertex) {
        Integer valueFound = Arrays.stream(hamiltonPath).
                filter(element -> currentVertex == element).findFirst().orElse(Integer.MIN_VALUE);

        return valueFound == Integer.MIN_VALUE;
    }


    private static List<List<Integer>> getGraphData() {
        List<List<Integer>> graph = new ArrayList<>();

        graph.add(Arrays.asList(1, 3));
        graph.add(Arrays.asList(0, 2));
        graph.add(Arrays.asList(1, 4));
        //graph.add(Arrays.asList(0, 1, 4));
        graph.add(Arrays.asList(0, 5));
        //graph.add(Arrays.asList(2, 1, 3));
        graph.add(Arrays.asList(2, 5));
        graph.add(Arrays.asList(3, 4));

        return graph;
    }
}
