package com.epam.tree;

import com.epam.model.Node;

import java.util.*;

//Given a Binary Search Tree, the task is to print the nodes of the BST in the following order:
//
//If the BST contains levels numbered from 1 to N then, the printing order is level 1, level N, level 2, level N – 1, and so on.
//The top-level order (1, 2, …) nodes are printed from left to right, while the bottom level order (N, N-1, …) nodes are printed from right to left.
public class BinaryTreeTopLevelAndReversedBottomLevel {

    public static void main(String[] args) {
        Node root = new Node(27);
        root.left = new Node(14);
        root.right = new Node(35);
        root.left.left = new Node(10);
        root.left.right = new Node(19);
        root.right.left = new Node(31);
        root.right.right = new Node(42);
        System.out.println("Top level order and Reversed bottom level order is");

        printSolution(root);

        System.out.println("Solution using recursive approach");

        Map<Integer, List<Integer>> resultData= new HashMap<>();
        solution(root, resultData, 0);

        printResult(resultData);
    }

    private static void printSolution(Node rootNode) {
        if (rootNode != null) {
            Map<Integer, List<Integer>> resultData = new HashMap<>();

            Map<Integer, Integer> nodesHeight = new HashMap<>();

            Queue<Node> nodes = new LinkedList<>();
            nodes.offer(rootNode);

            while (!nodes.isEmpty()) {
                Node currentNode = nodes.poll();
                Integer currentNodeHeight = nodesHeight.getOrDefault(currentNode.getKey(), 0);

                resultData.computeIfAbsent(currentNodeHeight, key -> new ArrayList<>()).add(currentNode.getKey());

                if (currentNode.getLeft() != null) {
                    nodesHeight.put(currentNode.getLeft().getKey(), currentNodeHeight + 1);
                    nodes.offer(currentNode.getLeft());
                }

                if (currentNode.getRight() != null) {
                    nodesHeight.put(currentNode.getRight().getKey(), currentNodeHeight + 1);
                    nodes.offer(currentNode.getRight());
                }
            }

            printResult(resultData);
        }
    }

    private static void solution(Node rootNode, Map<Integer, List<Integer>> resultData, int currentHeight) {
        if (rootNode != null) {
            resultData.computeIfAbsent(currentHeight, key -> new ArrayList<>()).add(rootNode.getKey());

            solution(rootNode.getLeft(), resultData, currentHeight + 1);
            solution(rootNode.getRight(), resultData, currentHeight + 1);
        }
    }

    private static void printResult(Map<Integer, List<Integer>> resultData) {
        List<List<Integer>> finalData = new ArrayList<>();
        resultData.forEach((key, val) -> finalData.add(val));

        int startIndex = 0;
        int endIndex = finalData.size() - 1;

        if (startIndex == endIndex) {
            System.out.println(finalData.get(0));
        } else {

            for (int count = 0; count < finalData.size(); count++) {
                if (count % 2 == 0) {
                    System.out.println(finalData.get(startIndex++));
                } else {
                    List<Integer> reversedOrder = finalData.get(endIndex--);
                    reversedOrder.sort((e1, e2) -> e2 - e1);
                    System.out.println(reversedOrder);
                }
            }
        }

    }
}
