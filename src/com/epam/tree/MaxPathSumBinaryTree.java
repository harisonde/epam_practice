package com.epam.tree;

public class MaxPathSumBinaryTree {
    private Node root;
    private Integer maxSum = 0;

    public static void main(String[] args) {
        MaxPathSumBinaryTree tree = new MaxPathSumBinaryTree();
        tree.root = new Node(10);
        tree.root.left = new Node(2);
        tree.root.right = new Node(10);
        tree.root.left.left = new Node(20);
        tree.root.left.right = new Node(1);
        tree.root.right.right = new Node(-25);
        tree.root.right.right.left = new Node(3);
        tree.root.right.right.right = new Node(4);
        System.out.println("maximum path sum is : " + tree.findMaxSum());

        tree = new MaxPathSumBinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);

        System.out.println("maximum path sum is : " + tree.findMaxSum());
    }

    private Integer findMaxSum() {
        maxSumUtil(this.root);

        return maxSum;
    }

    private int maxSumUtil(Node root) {
        if (root == null) {
            return 0;
        }

        int leftSum = maxSumUtil(root.left);
        int rightSum = maxSumUtil(root.right);

        int currentMax = Math.max(
                Math.max(root.data + leftSum, root.data + rightSum),
                Math.max(leftSum + rightSum + root.data, 0)
        );

        maxSum = Math.max(maxSum, currentMax);
        return Math.max(root.data + leftSum, root.data + rightSum);
    }


    static class Node {
        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }
}
