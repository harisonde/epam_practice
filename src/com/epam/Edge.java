package com.epam;

public class Edge implements Comparable<Edge> {
    int src;
    int dest;
    int weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }
}
