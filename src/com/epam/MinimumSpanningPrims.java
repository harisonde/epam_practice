package com.epam;

import java.util.*;

public class MinimumSpanningPrims {
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
        primsMST(graph, vertices);
    }

    private static void primsMST(Graph graph, int numberOfVertices) {
        Queue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        List<Edge> minimumSpanningTee = new ArrayList<>(numberOfVertices);
        QuickUnion quickUnion = new QuickUnion(numberOfVertices);
        priorityQueue.addAll(graph.vertices[0]);

        while (!priorityQueue.isEmpty() && minimumSpanningTee.size() != numberOfVertices - 1) {
            Edge edge = priorityQueue.poll();
            if (!quickUnion.find(edge.src, edge.dest)) {
                int destVertex = edge.dest;
                minimumSpanningTee.add(edge);
                quickUnion.union(edge.src, edge.dest);
                priorityQueue.addAll(graph.vertices[destVertex]);
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
