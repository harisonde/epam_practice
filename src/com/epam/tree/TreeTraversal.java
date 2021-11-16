package com.epam.tree;

import com.epam.model.Node;

import java.util.Stack;

public class TreeTraversal{

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.addNode(new Node(2));
        tree.addNode(new Node(1));
        tree.addNode(new Node(4));
        tree.addNode(new Node(3));
        tree.addNode(new Node(5));


        System.out.println("Preorder traversal of binary tree is ");
        printPreorder(tree.root);
        System.out.println(" ");
        System.out.println("Iterative Preorder traversal of binary tree is ");
        printPreOrderIterative(tree.root);
        printPreOrderIterative1(tree.root);

        System.out.println("\nInorder traversal of binary tree is ");
        printInorder(tree.root);
        System.out.println(" ");
        System.out.println("Iterative Inorder traversal of binary tree is ");
        printInOrderIterative(tree.root);
        printInOrderIterative1(tree.root);

        System.out.println("\nPostorder traversal of binary tree is ");
        printPostorder(tree.root);
        System.out.println(" ");
        System.out.println("Iterative PostOrder traversal of binary tree is ");
        printPostOrderIterative(tree.root);

        lowestCommonAncestor();
    }

    private static void printPostorder(Node root) {
        if(root != null){
            printPostorder(root.getLeft());
            printPostorder(root.getRight());
            System.out.print(" " + root.getKey());
        }
    }

    private static void printInorder(Node root) {
        if(root != null){
            printInorder(root.getLeft());
            System.out.print(" " + root.getKey());
            printInorder(root.getRight());
        }
    }

    private static void printPreorder(Node root) {
        if(root != null){
            System.out.print(" " + root.getKey());
            printPreorder(root.getLeft());
            printPreorder(root.getRight());
        }
    }

    private static void printPreOrderIterative(Node root) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(" " + node.getKey());

            if(node.getRight() != null){
                stack.push(node.getRight());
            }

            if(node.getLeft() != null){
                stack.push(node.getLeft());
            }
        }
    }

    private static void printPreOrderIterative1(Node root) {
        Stack<Node> stack = new Stack<>();
        Node parent = root;

        while (!stack.isEmpty() || parent != null) {

            if (parent != null) {
                System.out.print(" " + parent.getKey());
                stack.push(parent);
                parent = parent.getLeft();
            } else {

                Node currentNode = stack.pop();
                if (currentNode.getRight() != null) {
                    parent = currentNode.getRight();
                }
            }
        }
    }

    private static void printInOrderIterative1(Node root) {
        Stack<Node> stack = new Stack<>();
        Node parent = root;
        System.out.println();
        while (!stack.isEmpty() || parent != null) {
            if (parent != null) {
                stack.push(parent);
                parent = parent.getLeft();
            } else {
                Node currentNode = stack.pop();
                System.out.print(" " + currentNode.getKey());

                if (currentNode.getRight() != null) {
                    parent = currentNode.getRight();
                }
            }
        }
    }
    private static void printInOrderIterative(Node root) {
        Stack<Node> stack = new Stack<>();
        Node parent = root;

        while (!stack.isEmpty() || parent != null) {
            if (parent != null) {
                stack.push(parent);
                parent = parent.getLeft();
            } else {
                Node node = stack.pop();
                System.out.print(" " + node.getKey());
                if (node.getRight() != null) {
                    parent = node.getRight();
                }
            }
        }
    }

    private static void printPostOrderIterative(Node root) {
        Stack<Node> stack = new Stack<>();
        Node parent = root;

        while (!stack.isEmpty() || parent != null) {
            if (parent != null) {
                stack.push(parent);
                parent = parent.getLeft();
            } else {
                Node node = stack.peek();
                if (node.getRight() != null) {
                    parent = node.getRight();
                } else {
                    node = stack.pop();
                    System.out.print(" " + node.getKey());

                    printAndRemoveParentKeys(stack, node);
                }
            }
        }
    }

    private static void printAndRemoveParentKeys(Stack<Node> stack, Node rightChildNode){
        if(!stack.isEmpty()) {
            if (stack.peek().getRight().getKey() == rightChildNode.getKey()) {
                Node parentNode = stack.pop();
                System.out.print(" " + parentNode.getKey());
                printAndRemoveParentKeys(stack, parentNode);
            }
        }
    }

    private static void lowestCommonAncestor() {
        TreeTraversal treeTraversal = new TreeTraversal();
        Tree tree = new Tree();
        treeTraversal.populateData(tree);

        treeTraversal.findNodeAndReturnParent(tree.root, 14);

        Node lowestAncestor = treeTraversal.getLowestAncestor(tree.root, 10, 14);

        System.out.println();
        if(lowestAncestor != null && lowestAncestor.getKey() == 12){
            System.out.println("Lowest ancestor of 10 and 14 is 12 and it was determined correctly ");
        } else {
            System.out.println("Lowest ancestor of 10 and 14 is determined incorrectly ");
        }

        lowestAncestor = treeTraversal.getLowestAncestor(tree.root, 10, 22);

        System.out.println();
        if(lowestAncestor != null && lowestAncestor.getKey() == 20){
            System.out.println("Lowest ancestor of 10 and 22 is 20 and it was determined correctly ");
        } else {
            System.out.println("Lowest ancestor of 10 and 22 is determined incorrectly ");
        }

        lowestAncestor = treeTraversal.getLowestAncestor(tree.root, 8, 14);

        System.out.println();
        if(lowestAncestor != null && lowestAncestor.getKey() == 8){
            System.out.println("Lowest ancestor of 8 and 14 is 8 and it was determined correctly ");
        } else {
            System.out.println("Lowest ancestor of 8 and 14 is determined incorrectly ");
        }
    }

    private Node getLowestAncestor(Node rootNode, int key1, int key2) {
        if(rootNode == null){
            return null;
        }

        if(rootNode.getKey() < key1 && rootNode.getKey() < key2){
            return getLowestAncestor(rootNode.getRight(), key1, key2);
        }
        if(rootNode.getKey() > key1 && rootNode.getKey() > key2){
            return getLowestAncestor(rootNode.getLeft(), key1, key2);
        }

        return rootNode;
    }

    private void populateData(Tree tree) {
        tree.addNode(new Node(20));
        tree.addNode(new Node(8));
        tree.addNode(new Node(22));
        tree.addNode(new Node(4));
        tree.addNode(new Node(12));
        tree.addNode(new Node(10));
        tree.addNode(new Node(14));
    }

    private Node findNodeAndReturnParent(Node currentNode, int key) {
        if (currentNode == null) {
            return null;
        }

        if (currentNode.getKey() == key || currentNode.getLeft().getKey() == key
                || currentNode.getRight().getKey() == key) {
            return currentNode;
        }

        if (currentNode.getKey() < key) {
            return findNodeAndReturnParent(currentNode.getRight(), key);
        }

        return findNodeAndReturnParent(currentNode.getLeft(), key);
    }
}

