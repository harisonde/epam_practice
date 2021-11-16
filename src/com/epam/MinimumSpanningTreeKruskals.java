package com.epam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MinimumSpanningTreeKruskals {
    public static void main(String[] args) {
        int vertices = 6;
        Graph graph = new Graph(vertices);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);
        kruskalMST(graph, vertices);
    }

    private static void kruskalMST(Graph graph, int numberOfVertices) {
        List<Edge> edgeList = graph.getEdgeList().stream().sorted().collect(Collectors.toList());
        List<Edge> minimumSpanningTee = new ArrayList<>(numberOfVertices);
        QuickUnion quickUnion = new QuickUnion(numberOfVertices);

        while (minimumSpanningTee.size() != numberOfVertices - 1 && !edgeList.isEmpty()) {
            for (Edge edge : edgeList) {
                if (!quickUnion.find(edge.src, edge.dest)) {
                    minimumSpanningTee.add(edge);
                    quickUnion.union(edge.src, edge.dest);
                }
            }
        }

        printGraph(minimumSpanningTee);
    }

    private static void printGraph(List<Edge> edgeList) {
        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);
            System.out.println("Edge-" + i + " source: " + edge.src + " destination: " + edge.dest + " weight: " + edge.weight);
        }
    }
}
