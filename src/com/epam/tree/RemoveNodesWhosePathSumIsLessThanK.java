package com.epam.tree;

import com.epam.model.Node;

public class RemoveNodesWhosePathSumIsLessThanK {

    public static void main(String[] args) {
        Tree tree = new Tree();
        Node root = tree.root;
        root = new Node(5);
        root.right = new Node(3);
        root.left = new Node(4);
        root.left.left = new Node(9);
        root.right.right = new Node(9);
        root.left.right = new Node(8);
        root.left.right.right = new Node(11);
        root.left.right.left = new Node(5);
        root.left.right.left.left = new Node(6);
        root.left.right.left.right = new Node(2);
        root.right.right.right = new Node(4);

        System.out.println("Prior to removing nodes with path sum less than 27");
        tree.printInorder(root);

        root = solve(root, 27, 0);

        System.out.println("\nAfter to removing nodes with path sum less than 27");
        tree.printInorder(root);
    }

    private static Node solve(Node currentNode, int pathSum, int currentPathSum) {
        if (currentNode == null) {
            return null;
        }

        currentPathSum = currentPathSum + currentNode.getKey();
        currentNode.left = solve(currentNode.left, pathSum, currentPathSum);
        currentNode.right = solve(currentNode.right, pathSum, currentPathSum);

        if (currentNode.left == null && currentNode.right == null && currentPathSum < pathSum) {
            return null;
        }

        return currentNode;
    }


}
