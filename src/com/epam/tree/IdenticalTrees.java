package com.epam.tree;

import com.epam.model.Node;

public class IdenticalTrees {

    public static void main(String[] args) {
        Tree tree1 = new Tree();
        tree1.addNode(new Node(4));
        tree1.addNode(new Node(1));
        tree1.addNode(new Node(2));
        tree1.addNode(new Node(5));
        tree1.addNode(new Node(6));

        Tree tree2 = new Tree();
        tree2.addNode(new Node(4));
        tree2.addNode(new Node(1));
        tree2.addNode(new Node(2));
        tree2.addNode(new Node(5));
        tree2.addNode(new Node(6));

        boolean isIdentical = identical(tree1.root, tree2.root);

        System.out.println("2 Trees are identical ? " + isIdentical);

        tree2 = new Tree();
        tree2.addNode(new Node(4));
        tree2.addNode(new Node(1));
        tree2.addNode(new Node(2));
        tree2.addNode(new Node(5));
        tree2.addNode(new Node(7));

        isIdentical = identical(tree1.root, tree2.root);

        System.out.println("2 Trees are identical after change ? " + isIdentical);
    }

    public static boolean identical(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 == null || node1.getKey() != node2.getKey()) {
            return false;
        }

        return identical(node1.getLeft(), node2.getLeft()) && identical(node1.getRight(), node2.getRight());

    }
}
