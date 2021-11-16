package com.epam.tree;

import com.epam.model.Node;

import java.util.*;

public class VerticalOrderOfTree {
    // Driver program to test above functions
    public static void main(String[] args) {

        // TO DO Auto-generated method stub
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.right = new Node(8);
        root.right.right.right = new Node(9);
        System.out.println("Vertical Order traversal is");
        printVerticalOrder(root, 4);

        Map<Integer, List<Integer>> resultData = new TreeMap<>();

        verticalOrder(root, resultData, 0);

        resultData.forEach((key, value) -> {
            value.forEach(node -> System.out.print(" " + node));
            System.out.println();

        });
    }

    private static void verticalOrder(Node root, Map<Integer, List<Integer>> resultData, int currentHeight) {
        if (root != null) {
            List<Integer> valuesAtCurrentLevel = resultData.get(currentHeight);

            if (valuesAtCurrentLevel == null) {
                valuesAtCurrentLevel = new ArrayList<>();
            }
            valuesAtCurrentLevel.add(root.getKey());
            resultData.put(currentHeight, valuesAtCurrentLevel);

            verticalOrder(root.left, resultData, currentHeight - 1);
            verticalOrder(root.right, resultData, currentHeight + 1);
        }
    }

    private static void printVerticalOrder(Node root, int kthElement) {
        if (root != null) {
            Map<Integer, List<Node>> nodesByHeight = new TreeMap<>();
            Map<Integer, Integer> nodeHeightInfo = new HashMap<>();
            nodeHeightInfo.put(root.getKey(), 0);

            Queue<Node> nodes = new LinkedList<>();
            nodes.add(root);

            while (!nodes.isEmpty()) {
                Node currentNode = nodes.poll();
                Integer currentNodeHeight = nodeHeightInfo.get(currentNode.getKey());

                nodesByHeight.computeIfAbsent(currentNodeHeight, key -> new ArrayList<>()).add(currentNode);

                if (currentNode.getLeft() != null) {
                    nodes.add(currentNode.getLeft());
                    nodeHeightInfo.put(currentNode.getLeft().getKey(), currentNodeHeight - 1);
                }

                if (currentNode.getRight() != null) {
                    nodes.add(currentNode.getRight());
                    nodeHeightInfo.put(currentNode.getRight().getKey(), currentNodeHeight + 1);
                }
            }

            nodesByHeight.forEach((key, val) -> {
                val.forEach(node -> System.out.print(" " + node.getKey()));
                System.out.println();
            });

            printKthElement(nodesByHeight, kthElement);
            printKthElement(nodesByHeight, 5);
            printKthElement(nodesByHeight, 14);
        }
    }

    private static void printKthElement(Map<Integer, List<Node>> nodesByHeight, int kthElement) {
        int result = -1;

        if (nodesByHeight.size() >= kthElement) {
            int currentIndex = 1;

            for (Map.Entry<Integer, List<Node>> entry : nodesByHeight.entrySet()) {
                for (int start = 0; start < entry.getValue().size(); start++) {
                    if (currentIndex == kthElement) {
                        result = entry.getValue().get(start).getKey();
                    }

                    currentIndex++;
                }
                if (result != -1) {
                    break;
                }
            }
        }

        System.out.println("\n" + kthElement + "th element in vertical order is " + result);
    }
}
