package com.epam.tree;

import com.epam.model.Node;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeLeftView {
    static int max_level = 0;

    public static void main(String[] args){
        /* creating a binary tree and entering the nodes */
        Tree tree = new Tree();
        tree.addNode(new Node(12));
        tree.addNode(new Node(10));
        tree.addNode(new Node(30));
        tree.addNode(new Node(25));
        tree.addNode(new Node(40));


        leftViewRecursion(tree);
        System.out.println();

        tree = new Tree();
        tree.addNode(new Node(6));
        tree.addNode(new Node(4));
        tree.addNode(new Node(5));
        tree.addNode(new Node(3));
        tree.addNode(new Node(7));
        tree.addNode(new Node(8));
        tree.addNode(new Node(9));

        System.out.println("Left view using iterator approach ");
        leftViewUtilItr(tree.root);

        System.out.println();
        max_level = 0;
        System.out.println("Left view using recursion approach ");
        leftViewRecursion(tree);

        System.out.println();
        max_level = 0;
        System.out.println("Right view using recursion approach ");
        rightViewRecursion(tree);

        System.out.println();
        System.out.println("Right view using Iterator approach ");
        rightViewItr(tree.root);
    }

    private static void rightViewItr(Node node) {
        if (node != null) {
            Queue<Node> processingNodes = new LinkedList<>();
            processingNodes.add(node);
            int nodesAtCurrentLevel = 1;

            while(!processingNodes.isEmpty()) {
                int start = 0, nodesAtNextLevel = 0;

                while (start < nodesAtCurrentLevel) {
                    Node currentNode = processingNodes.poll();

                    if (currentNode != null) {
                        if (start++ == 0) {
                            System.out.print(" " + currentNode.getKey());
                        }

                        if (currentNode.getRight() != null) {
                            processingNodes.offer(currentNode.getRight());
                            nodesAtNextLevel++;
                        }

                        if (currentNode.getLeft() != null) {
                            processingNodes.offer(currentNode.getLeft());
                            nodesAtNextLevel++;
                        }
                    }
                }

                nodesAtCurrentLevel = nodesAtNextLevel;
            }
        }
    }

    /*
           6

       4        7
    5     3         8
                       9*/

    private static void leftViewRecursion(Tree tree) {
        leftViewUtil1(tree.root, 1);
    }

    private static void rightViewRecursion(Tree tree){
        rightViewUtil(tree.root, 1);
    }

    private static void rightViewUtil(Node node, int level) {
        if(node != null){
            if(max_level < level){
                max_level++;
                System.out.print(" " + node.getKey());
            }

            rightViewUtil(node.getRight(), level + 1);
            rightViewUtil(node.getLeft(), level + 1);
        }
    }

    private static void leftViewUtil1(Node root, int level){
        if(root != null){
            if(max_level < level){
                System.out.print(" " + root.getKey());
                max_level++;
            }

            leftViewUtil(root.getLeft(), level + 1);
            leftViewUtil(root.getRight(), level + 1);
        }
    }

    // recursive function to print left view
    private static void leftViewUtil(Node node, int level) {
        // Base Case
        if (node == null)
            return;

        // If this is the first node of its level
        if (max_level < level) {
            System.out.print(" " + node.getKey());
            max_level = level;
        }

        // Recur for left and right subtrees
        leftViewUtil(node.getLeft(), level + 1);
        leftViewUtil(node.getRight(), level + 1);
    }

    private static void leftViewUtilItr(Node node) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        int nodesAtNextLevel = 0;
        int nodesAtCurrentLevel = 1;

        while (!queue.isEmpty()) {

            int start = 0;
            while (start < nodesAtCurrentLevel) {
                Node currentNode = queue.poll();

                if (currentNode != null) {
                    if (start++ == 0) {
                        System.out.print(" " + currentNode.getKey());
                    }

                    if (currentNode.getLeft() != null) {
                        queue.add(currentNode.getLeft());
                        nodesAtNextLevel++;
                    }

                    if (currentNode.getRight() != null) {
                        queue.add(currentNode.getRight());
                        nodesAtNextLevel++;
                    }
                }
            }

            nodesAtCurrentLevel = nodesAtNextLevel;
            nodesAtNextLevel = 0;
        }
    }

}
