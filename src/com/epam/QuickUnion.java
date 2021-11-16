package com.epam;

import java.util.stream.IntStream;

public class QuickUnion {
    int[] unionData;

    public QuickUnion(int size) {
        unionData = new int[size];
        IntStream.range(0, size).forEach(val -> unionData[val] = val);
    }

    public static void main(String[] args) {

        QuickUnion quickUnion = new QuickUnion(256);

        quickUnion.union(4, 1);
        quickUnion.union(1, 2);
        quickUnion.union(3, 2);
        quickUnion.union(4, 5);
        quickUnion.union(6, 7);

        System.out.println("Does Vertex 1 and 2 are connected? " + quickUnion.find(1, 2) );
        System.out.println("Does Vertex 3 and 1 are connected? " + quickUnion.find(3, 1));
        System.out.println("Does Vertex 5 and 1 are connected? " + quickUnion.find(1, 5));
        System.out.println("Does Vertex 6 and 7 are connected? " + quickUnion.find(6, 7));

        System.out.println("Does Vertex 6 and 1 are connected? " + quickUnion.find(6, 1));
        quickUnion.union(6, 1);
        System.out.println("Does Vertex 6 and 1 are connected? " + quickUnion.find(6, 1));
        System.out.println("Does Vertex 7 and 2 are connected? " + quickUnion.find(6, 1));
    }


    public void union(int element1, int element2) {
        int root1 = unionData[element1];
        int root2 = unionData[element2];

        unionData[root2] = unionData[root1];
    }

    public Boolean find(int element1, int element2) {
        int parent1 = findParent(element1);
        int parent2 = findParent(element2);

        return parent1 == parent2;
    }

    private int findParent(int element) {
        if (unionData[element] == element) {
            return element;
        }
        return findParent(unionData[element]);
    }
}
