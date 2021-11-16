package com.epam;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraShortestPath {
    public static void main(String[] args){
        int numberOfVertex = 5;
        Graph graph = new Graph(numberOfVertex);
        graph.addEdge(0, 1, 9);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(0, 4, 3);
        graph.addEdge(2, 1, 2);
        graph.addEdge(2, 3, 4);
        //graph.addEdge(1, 3, 2);
        //graph.addEdge(3, 4, 1);


        int[] destination = new int[numberOfVertex];
        int[] distanceFromSrc = new int[numberOfVertex];

        for(int vertex =0; vertex<numberOfVertex;vertex++){
            destination[vertex] = Integer.MAX_VALUE;
            distanceFromSrc[vertex] = Integer.MAX_VALUE;
        }

        destination[0] = 0;
        distanceFromSrc[0] = 0;

        findPath(graph, 0, destination, distanceFromSrc);

        printPath(0, 1, destination);
        printPath(0, 4, destination);
    }

    private static void findPath(Graph graph, int src, int[] destination, int[] distanceFromSrc) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(graph.vertices[src]);

        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();

            if (distanceFromSrc[edge.dest] > (distanceFromSrc[edge.src] + edge.weight)) {
                distanceFromSrc[edge.dest] = distanceFromSrc[edge.src] + edge.weight;
                destination[edge.dest] = edge.src;
                addElementsToQueue(priorityQueue, edge.dest, graph, edge.src);
            }
        }
    }

    private static void addElementsToQueue(PriorityQueue<Edge> priorityQueue, int src, Graph graph, int parent) {
        for (int edgeCount = 0; edgeCount < graph.vertices[src].size(); edgeCount++) {
            Edge destEdge = graph.vertices[src].get(edgeCount);
            if (destEdge.dest != parent) {
                priorityQueue.add(graph.vertices[src].get(edgeCount));
            }
        }
    }

    private static void printPath(int src, int dest, int[] destination) {
        List<Integer> finalPath = new ArrayList<>();
        finalPath.add(dest);
        getPath(finalPath, src, dest, destination);

        System.out.print("Path from " + src + " to " + dest + " includes ");

        for (int i = finalPath.size() -1; i >= 0; i--) {
            System.out.print(" - " + finalPath.get(i));
        }
        System.out.println();
    }

    private static void getPath(List<Integer> finalPath, int src, int dest, int[] destination) {
        if (dest != src) {
            finalPath.add(destination[dest]);
            getPath(finalPath, src, destination[dest], destination);
        }
    }
}
