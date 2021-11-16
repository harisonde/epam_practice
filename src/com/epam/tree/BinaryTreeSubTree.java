package com.epam.tree;

import com.epam.model.Node;

public class BinaryTreeSubTree {
    public static void main(String[] args){
        Tree mainTree = new Tree();
        mainTree.addNode(new Node(4));
        mainTree.addNode(new Node(1));
        mainTree.addNode(new Node(2));
        mainTree.addNode(new Node(5));
        mainTree.addNode(new Node(6));

        Tree subTree = new Tree();
        subTree.addNode(new Node(5));
        subTree.addNode(new Node(6));

        boolean isSubTree = checkSubTree(mainTree.root, subTree.root);

        System.out.println("Is Subtree ? " + isSubTree);

        subTree = new Tree();
        subTree.addNode(new Node(5));
        subTree.addNode(new Node(7));

        isSubTree = checkSubTree(mainTree.root, subTree.root);

        System.out.println("Is Subtree ? " + isSubTree);
    }

    private static boolean checkSubTree(Node mainTree, Node subTree) {
        if (mainTree == null) {
            return false;
        }

        if (subTree == null) {
            return true;
        }

        if (IdenticalTrees.identical(mainTree, subTree)) {
            return true;
        }

        return checkSubTree(mainTree.left, subTree) || checkSubTree(mainTree.right, subTree);
    }
}
