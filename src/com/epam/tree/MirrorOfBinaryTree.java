package com.epam.tree;

import com.epam.model.Node;

public class MirrorOfBinaryTree {
    public static void main(String[] args){

        Tree tree1 = new Tree();
        tree1.root = new Node(4);
        tree1.root.left = new Node(1);
        tree1.root.left.right = new Node(2);
        tree1.root.right = new Node(5);
        tree1.root.right.right = new Node(6);

        Tree tree2 = new Tree();
        tree2.root = new Node(4);
        tree2.root.right = new Node(1);
        tree2.root.right.left = new Node(2);
        tree2.root.left = new Node(5);
        tree2.root.left.left = new Node(6);

        boolean isMirror = mirror(tree1.root, tree2.root);

        System.out.println("2 Trees are mirrored ? " + isMirror);

        tree2 = new Tree();
        tree2.root = new Node(4);
        tree2.root.right = new Node(1);
        tree2.root.left = new Node(5);
        tree2.root.left.left = new Node(6);

        isMirror = mirror(tree1.root, tree2.root);

        System.out.println("2 Trees are mirrored after change ? " + isMirror);
    }

    private static boolean mirror(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 == null || node2 == null || node1.getKey() != node2.getKey()) {
            return false;
        }

        return mirror(node1.getLeft(), node2.getRight()) && mirror(node1.getRight(), node2.getLeft());

    }
}
