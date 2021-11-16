package com.epam.tree;

import com.epam.model.Node;

public class MorrisTraversal {
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.addNode(new Node(4));
        tree.addNode(new Node(1));
        tree.addNode(new Node(2));
        tree.addNode(new Node(8));
        tree.addNode(new Node(6));

        Node predecessor = findPredecessor(tree.root);
        predecessor = findPredecessor1(tree.root.left);
        findPredecessorWhileLoop(tree.root);
        findSuccessorWhileLoop(tree.root);

        tree.printInorder(tree.root);

        System.out.println("\n Morris In order traversal ");
        inorderTraversal(tree.root);

        System.out.println("\n Pre order traversal");
        preOrderTraversal(tree.root);

        System.out.println("\n Morris Pre order traversal ");
        morrisPreOrderTraversal(tree.root);
    }

    private static void morrisPreOrderTraversal(Node node) {
        Node currentNode = node;

        while (currentNode != null) {
            Node predecessor = findPredecessorWhileLoop(currentNode);

            if (predecessor != null) {
                if (predecessor.getRight() != null) {
                    predecessor.right = null;
                    currentNode = currentNode.getRight();
                } else {
                    predecessor.right = currentNode;
                    System.out.print(" " + currentNode.getKey());
                    currentNode = currentNode.getLeft();
                }
            } else {
                System.out.print(" " + currentNode.getKey());
                currentNode = currentNode.getRight();
            }
        }
    }

    private static Node findSuccessorWhileLoop(Node root) {
        Node result = root.getRight();

        while (result.getLeft() != null) {
            result = result.getLeft();
        }
        return result;
    }

    private static Node findPredecessorWhileLoop(Node root) {
        Node result = root.left;

        while(result != null && result.getRight() != null && result.getRight().getKey() != root.getKey()){
            result = result.getRight();
        }

        return result;
    }

    private static void inorderTraversal(Node node) {
        Node currentNode = node;

        while (currentNode != null) {
            if (currentNode.getLeft() != null) {
                Node predecessor = findPredecessorWhileLoop(currentNode);

                if (predecessor.getRight() != null) {
                    predecessor.right = null;
                    System.out.print(" " + currentNode.getKey());
                    currentNode = currentNode.getRight();
                } else {
                    predecessor.right = currentNode;
                    currentNode = currentNode.getLeft();
                }
            } else {
                System.out.print(" " + currentNode.getKey());
                currentNode = currentNode.getRight();
            }
        }
    }

    private static void preOrderTraversal(Node currentNode){
        if(currentNode != null){
            System.out.print(" " + currentNode.getKey());
            preOrderTraversal(currentNode.getLeft());
            preOrderTraversal(currentNode.getRight());
        }
    }

    private static Node findPredecessor(Node node) {
        if (node == null) {
            return null;
        }

        if (node.getLeft() == null && node.getRight() == null) {
            return null;
        }


        Node resultNode = findPredecessor(node.getLeft());

        if (resultNode == null) {
            if (node.getRight() == null) {
                return node;
            } else {
                resultNode = findPredecessor(node.getRight());

                return resultNode == null ? node.getRight() : resultNode;
            }
        }

        return resultNode;
    }

    private static Node findPredecessor1(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right == null) {
            return node;
        }

        return node.right;
    }
}
