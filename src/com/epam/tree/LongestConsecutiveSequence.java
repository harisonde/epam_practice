package com.epam.tree;

import com.epam.model.Node;

public class LongestConsecutiveSequence {

    private static int maxSequence = 0;

    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.root = new Node(6);
        tree.root.right = new Node(9);
        tree.root.right.left = new Node(7);
        tree.root.right.right = new Node(10);
        tree.root.right.right.right = new Node(11);

        longestConsecutive(tree.root);

        System.out.println("Longest consecutive sequence is " + maxSequence);

        maxSequence = 0;

        longestConsecutiveRecursive(tree.root, 1);

        System.out.println("Longest consecutive sequence is using recursive approach : Best one" + maxSequence);
    }

    private static int longestConsecutive(Node currentNode) {
        if (currentNode != null) {

            int leftNodeSeq = longestConsecutive(currentNode.left);
            int rightNodeSeq = longestConsecutive(currentNode.right);

            int currentNodeSeq = 1;

            if (currentNode.getLeft() != null) {
                if (currentNode.getLeft().getKey() - currentNode.getKey() == 1) {
                    currentNodeSeq = leftNodeSeq + 1;
                }
            }

            if (currentNode.getRight() != null) {
                if (currentNode.getRight().getKey() - currentNode.getKey() == 1) {
                    currentNodeSeq = Math.max(currentNodeSeq, rightNodeSeq + 1);
                }
            }

            maxSequence = Math.max(currentNodeSeq, maxSequence);

            return currentNodeSeq;
        }
        return 0;
    }

    private static void longestConsecutiveRecursive(Node currentNode, int currentSeq) {
        if (currentNode != null) {
            maxSequence = Math.max(currentSeq, maxSequence);

            if (currentNode.left != null) {
                int leftSeq = currentNode.getKey() - currentNode.left.getKey() == 1 ? currentSeq + 1 : 1;
                longestConsecutiveRecursive(currentNode.getLeft(), leftSeq);
            }

            if (currentNode.right != null) {
                int rightSeq = currentNode.right.getKey() - currentNode.getKey() == 1 ? currentSeq + 1 : 1;
                longestConsecutiveRecursive(currentNode.getRight(), rightSeq);
            }
        }
    }
}
