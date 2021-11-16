package com.epam;

import java.util.List;
import java.util.Stack;

public class TopologicalSort {
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdgeDirectedGraph(5, 2, 0);
        graph.addEdgeDirectedGraph(5, 0, 0);
        graph.addEdgeDirectedGraph(4, 0, 0);
        graph.addEdgeDirectedGraph(4, 1, 0);
        graph.addEdgeDirectedGraph(2, 3, 0);
        graph.addEdgeDirectedGraph(3, 1, 0);

        Stack<Integer> result = topologicalSort((graph));

        for(int i = 0; i < 6; i++)
        {
            System.out.print(" " + result.pop());
        }

        System.out.println();
        result = topoSort(graph);

        for(int i = 0; i < 6; i++)
        {
            System.out.print(" " + result.pop());
        }
    }


    private static Stack<Integer> topoSort(Graph graph) {
        Stack<Integer> result = new Stack<>();

        boolean[] visitedInd = new boolean[graph.numberOfVertex];

        for (int vertexIndex = 0; vertexIndex < graph.numberOfVertex; vertexIndex++) {
            if (!visitedInd[vertexIndex]) {
                topologicalSortUtil(result, visitedInd, vertexIndex, graph);
            }
        }

        return result;
    }

    private static void topologicalSortUtil(Stack<Integer> result, boolean[] visitedInd, int vertexIndex, Graph graph) {
        List<Edge> edges = graph.vertices[vertexIndex];

        for (Edge edge : edges) {
            if (!visitedInd[edge.dest]) {
                topologicalSortUtil(result, visitedInd, edge.dest, graph);
            }
        }

        if(!visitedInd[vertexIndex]){
            visitedInd[vertexIndex] = true;
            result.push(vertexIndex);
        }
    }


    private static Stack<Integer> topologicalSort(Graph graph) {
        Stack<Integer> result = new Stack<>();
        boolean[] visitedInd = new boolean[graph.vertices.length];

        for (int vertexIndex = 0; vertexIndex < graph.vertices.length; vertexIndex++) {
            if (!visitedInd[vertexIndex]) {
                visitedInd[vertexIndex] = true;
                topologicalSortUtil(graph, vertexIndex, result, visitedInd);
            }
        }
        return result;
    }

    private static void topologicalSortUtil(Graph graph, int vertexIdx, Stack<Integer> result, boolean[] visitedInd) {
        for (Edge edge : graph.vertices[vertexIdx]) {
            if (!visitedInd[edge.dest]) {
                visitedInd[edge.dest] = true;
                topologicalSortUtil(graph, edge.dest, result, visitedInd);
            }
        }

        result.push(vertexIdx);
    }
}
