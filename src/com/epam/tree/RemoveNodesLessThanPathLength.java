package com.epam.tree;

import com.epam.model.Node;

public class RemoveNodesLessThanPathLength {
    public static void main(String[] args) {
        Tree tree = new Tree();
        int k = 4;
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.left.left.left = new Node(7);
        tree.root.right.right = new Node(6);
        tree.root.right.right.left = new Node(8);

        System.out.println("Tree prior to removing nodes");
        tree.printInorder(tree.root);

        tree.root = removeNodesLessThanPathLength(tree.root, k);

        System.out.println("\nTree after removing nodes with leaf length from root less than 4");
        tree.printInorder(tree.root);

        tree.root = removeNodesLessThanPathLength(tree.root, 5);

        System.out.println("\nTree after removing nodes with leaf length from root less than 5");
        tree.printInorder(tree.root);
    }

    private static Node removeNodesLessThanPathLength(Node currentNode, int height){
        int currentHeight = removeNodesLessThanPathLengthUtil(currentNode, height, 0);

        return currentHeight < height ? null : currentNode;
    }



    private static int removeNodesLessThanPathLengthUtil(Node currentNode, int height, int currentHeight) {
        if (currentNode == null) {
            return currentHeight;
        }

        int leftNodeHeight = removeNodesLessThanPathLengthUtil(currentNode.getLeft(), height, currentHeight + 1);
        int rightNodeHeight = removeNodesLessThanPathLengthUtil(currentNode.getRight(), height, currentHeight + 1);

        if (leftNodeHeight < height) {
            currentNode.left = null;
        }

        if (rightNodeHeight < height) {
            currentNode.right = null;
        }

        return Math.max(leftNodeHeight, rightNodeHeight);
    }

    private static Node removeNodesLessThanPathLength1(Node currentNode, int height, int currentHeight) {
        if (currentNode == null) {
            return null;
        }

        currentNode.left = removeNodesLessThanPathLength1(currentNode.getLeft(), height, currentHeight + 1);
        currentNode.right = removeNodesLessThanPathLength1(currentNode.getRight(), height, currentHeight + 1);

        if (currentNode.left == null && currentNode.right == null && currentHeight < height) {
            return null;
        }
        return currentNode;
    }
}
