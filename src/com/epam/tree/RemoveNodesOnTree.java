package com.epam.tree;

public class RemoveNodesOnTree {
    private Node root;

             /*              1
                       2          3
                   4      5    6     8
                      7
                         9             */

    public static void main(String[] args){
        RemoveNodesOnTree tree = new RemoveNodesOnTree();
        int k = 3;
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.left.left.left = new Node(7);
        tree.root.left.left.left.left = new Node(9);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(8);
        System.out.println("The inorder traversal of original tree is : ");

        System.out.println("Height of the original tree is : " + getMaxHeight(tree.root));

        tree.printInorder(tree.root);

        Node res = tree.removeShortPathNodes(tree.root, k, 1);
        System.out.println("");
        System.out.println("The inorder traversal of modified tree is : ");
        tree.printInorder(res);
    }

    private Node removeShortPathNodes(Node currentNode, int maxHeight, int currentHeight) {
        if (currentNode == null) {
            return null;
        }

        if(currentHeight > maxHeight){
            return null;
        }

        currentNode.left = removeShortPathNodes(currentNode.left, maxHeight, currentHeight + 1);
        currentNode.right = removeShortPathNodes(currentNode.right, maxHeight, currentHeight + 1);

        return currentNode;
    }

    private static int getMaxHeight(Node currentNode) {
        if (currentNode == null) {
            return 0;
        }

        int leftTreeHeight = getMaxHeight(currentNode.left);
        int rightTreeHeight = getMaxHeight(currentNode.right);

        return 1 + Math.max(leftTreeHeight, rightTreeHeight);
    }

    private void printInorder(Node root) {
        if(root != null){
            printInorder(root.left);
            System.out.print(" " + root.data);
            printInorder(root.right);
        }
    }

    static class Node
    {
        int data;
        Node left, right;

        public Node(int item)
        {
            data = item;
            left = right = null;
        }
    }
}
