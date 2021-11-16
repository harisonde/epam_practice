package com.epam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class Graph {
    int numberOfVertex;
    List<Edge>[] vertices;
    List<Edge> edgeList;

    public Graph(int numberOfVertex) {
        this.numberOfVertex = numberOfVertex;
        vertices = new ArrayList[numberOfVertex];
        IntStream.range(0, numberOfVertex).forEach(pos -> vertices[pos] = new ArrayList<>());
        edgeList = new ArrayList<>();
    }

    public void addEdge(int src, int dest, int weight) {
        Edge fromSrc = new Edge(src, dest, weight);
        vertices[src].add(fromSrc);
        vertices[dest].add(new Edge(dest, src, weight));
        edgeList.add(fromSrc);
    }

    public void addEdgeDirectedGraph(int src, int dest, int weight){
        Edge edge = new Edge(src, dest, weight);
        vertices[src].add(edge);
        edgeList.add(edge);
    }


    public boolean isCycle() {
        boolean[] visitedInd = new boolean[vertices.length];

        for (int vertex = 0; vertex < vertices.length; vertex++) {
            if (isCycleUtil(-1, vertex, visitedInd)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCycleUtil(int parent, int vertex, boolean[] visitedInd) {
        if (visitedInd[vertex]) {
            return true;
        }

        visitedInd[vertex] = true;
        for (int childVertex = 0; childVertex < vertices[vertex].size(); childVertex++) {
            Edge edge = vertices[vertex].get(childVertex);
            if (edge.dest != parent) {
                if (isCycleUtil(edge.src, edge.dest, visitedInd)) {
                    return true;
                }
            }
        }
        visitedInd[vertex] = false;
        return false;
    }

    public void printDfs() {
        boolean[] visitedInd = new boolean[vertices.length];
        List<Integer> resultsArray = new ArrayList<>();

        for (int vertexIndex = 0; vertexIndex < vertices.length; vertexIndex++) {

            if (!visitedInd[vertexIndex]) {
                dfsUtil(-1, vertexIndex, visitedInd, resultsArray);
            }
        }

        System.out.println("Dfs of graph is " + resultsArray);
    }

    public void printBfs() {
        boolean[] visitedInd = new boolean[vertices.length];
        List<Integer> bfs = new ArrayList<>();

        Queue<Integer> workList = new LinkedList<>();
        workList.add(0);

        while (!workList.isEmpty()) {
            int index = workList.poll();

            if (!visitedInd[index]) {
                visitedInd[index] = true;
                bfs.add(index);

                for (Edge edge : vertices[index]) {
                    if (!visitedInd[edge.dest]) {
                        workList.add(edge.dest);
                    }
                }
            }

        }

        System.out.println("Dfs of graph is " + bfs);
    }

    private void dfsUtil(int parent, int src, boolean[] visitedInd, List<Integer> resultsArray) {

        if (!visitedInd[src]) {

            resultsArray.add(src);
            visitedInd[src] = true;

            for (int edgeIndex = 0; edgeIndex < vertices[src].size(); edgeIndex++) {
                Edge currentEdge = vertices[src].get(edgeIndex);

                if (currentEdge.dest != parent) {
                    dfsUtil(currentEdge.src, currentEdge.dest, visitedInd, resultsArray);
                }
            }
        }
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }
}
