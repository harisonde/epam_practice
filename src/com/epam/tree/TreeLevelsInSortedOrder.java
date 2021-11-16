package com.epam.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

//Given a Complete Binary Tree as an array, the task is to print all of its levels in sorted order.
public class TreeLevelsInSortedOrder {

    public static void main(String[] args) {
        int[] input = new int[]{17, 16, 15, 14, 13, 12, 11, 7, 6, 5, 4, 3, 2, 1, 0};

        printResult(input);

        input = new int[]{5, 6, 4, 9, 2, 1};

        printResult(input);
    }

    private static void printResult(int[] input) {
        Map<Integer, TreeSet<Integer>> result = new HashMap<>();
        result.computeIfAbsent(0, key -> new TreeSet<>()).add(input[0]);

        Map<Integer, Integer> nodesLevel = new HashMap<>();
        nodesLevel.putIfAbsent(input[0], 0);

        for (int start = 0; (start * 2) + 1 < input.length; start++) {
            int currentNodeLevel = nodesLevel.get(input[start]);

            int leftNode = start * 2 + 1;
            int rightNode = start * 2 + 2;

            nodesLevel.putIfAbsent(input[leftNode], currentNodeLevel + 1);

            result.computeIfAbsent(currentNodeLevel + 1, k -> new TreeSet<>()).add(input[leftNode]);

            if (rightNode < input.length) {
                nodesLevel.putIfAbsent(input[rightNode], currentNodeLevel + 1);
                result.computeIfAbsent(currentNodeLevel + 1, k -> new TreeSet<>()).add(input[rightNode]);
            }
        }

        printSolution(result);
    }

    private static void printSolution(Map<Integer, TreeSet<Integer>> result) {
        for (Map.Entry<Integer, TreeSet<Integer>> entry : result.entrySet()) {
            System.out.println(" " + entry.getValue());
        }
    }
}
