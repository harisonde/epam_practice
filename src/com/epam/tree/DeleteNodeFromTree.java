package com.epam.tree;

import com.epam.model.Node;
import com.epam.tree.Tree;

public class DeleteNodeFromTree {
    Node root;

    DeleteNodeFromTree() {
        root = null;
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.addNode(new Node(50));
        tree.addNode(new Node(30));
        tree.addNode(new Node(20));
        tree.addNode(new Node(40));
        tree.addNode(new Node(70));
        tree.addNode(new Node(60));
        tree.addNode(new Node(80));

        System.out.println("\nInorder traversal of binary tree is ");
        tree.printInorder(tree.root);

        tree.deleteNode(new Node(20));
        System.out.println("\nInorder traversal of binary tree after deleting node 30 is");
        tree.printInorder(tree.root);

        tree.deleteNode(new Node(30));
        System.out.println("\nInorder traversal of binary tree after deleting node 30 is");
        tree.printInorder(tree.root);

        tree.deleteNode(new Node(50));
        System.out.println("\nInorder traversal of binary tree after deleting node 50 is");
        tree.printInorder(tree.root);

    }
}

