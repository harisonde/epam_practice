package com.epam;

import java.util.stream.IntStream;

public class QuickFind {
    int[] unionData;

    public QuickFind(int size) {
        unionData = new int[size];
        IntStream.range(0, size).forEach(val -> unionData[val] = val);
    }

    public static void main(String[] args) {

        QuickFind quickUnion = new QuickFind(256);

        quickUnion.union(1, 2);
        quickUnion.union(3, 2);
        quickUnion.union(4, 5);
        quickUnion.union(3, 5);

        System.out.println("Does Vertex 1 and 2 are connected? " + quickUnion.find(1, 2));
        System.out.println("Does Vertex 3 and 1 are connected? " + quickUnion.find(3, 1));
        System.out.println("Does Vertex 6 and 1 are connected? " + quickUnion.find(6, 1));
        System.out.println("Does Vertex 4 and 2 are connected? " + quickUnion.find(4, 2));
    }

    private void union(int element1, int element2) {
        int root = unionData[element2];
        unionData[element2] = unionData[element1];

        IntStream.range(0, unionData.length).forEach(pos -> {
            if (unionData[pos] == root) {
                unionData[pos] = unionData[element1];
            }
        });
    }

    private Boolean find(int element1, int element2) {
        return unionData[element1] == unionData[element2];
    }
}
