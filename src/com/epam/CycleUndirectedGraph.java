package com.epam;

import java.util.List;

public class CycleUndirectedGraph {
    public static void main(String[] args) {
        int vertices = 6;
        Graph graph = new Graph(vertices);
        graph.addEdge(0, 1, 0);
        graph.addEdge(0, 2, 0);
        graph.addEdge(1, 3, 0);
        graph.addEdge(3, 4, 0);
        //graph.addEdge(2, 3, 0);
        graph.addEdge(4, 5, 0);
        graph.addEdge(4, 2, 0);
        System.out.println("Graph contains cycle: " + graph.isCycle());
        System.out.println("Graph contains cycle: " + isCycle(graph));

        graph.printDfs();
        graph.printBfs();
    }

    private static boolean isCycle(Graph graph) {
        boolean[] visitedInd = new boolean[graph.numberOfVertex];

        for (int vertexIdx = 0; vertexIdx < graph.numberOfVertex; vertexIdx++) {
            if (verifyIfCycleExists(graph.vertices, -1, vertexIdx, visitedInd)) {
                return true;
            }
        }

        return false;
    }

    private static boolean verifyIfCycleExists(List<Edge>[] vertices, int parentIndex, int vertexIdx, boolean[] visitedInd) {
        if (visitedInd[vertexIdx]) {
            return true;
        }

        visitedInd[vertexIdx] = true;
        for (int edgeIdx = 0; edgeIdx < vertices[vertexIdx].size(); edgeIdx++) {
            Edge currentEdge = vertices[vertexIdx].get(edgeIdx);
            if (currentEdge.dest != parentIndex) {
                if(verifyIfCycleExists(vertices, currentEdge.src, currentEdge.dest, visitedInd)){
                    return true;
                }
            }
        }

        visitedInd[vertexIdx] = false;
        return false;
    }
}
