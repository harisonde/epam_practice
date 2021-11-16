package com.epam.tree;

import com.epam.model.Node;

public class Tree {
    Node root;

    public Tree() {
        root = null;
    }

    public Node addNode(Node node){
        if(root == null){
            return root = node;
        }

        return addNode(root, node);
    }

    public Node addNode(Node rootNode, Node node) {
        if (rootNode == null) {
            return node;
        }

        if (rootNode.getKey() > node.getKey()) {
            rootNode.left = addNode(rootNode.getLeft(), node);
        } else {
            rootNode.right = addNode(rootNode.getRight(), node);
        }
        return rootNode;
    }

    public void deleteNode(Node node){
        if(node != null){
            root = deleteNode(root, node);
        }
    }

    private Node deleteNode(Node currentNode, Node node) {
        if (currentNode == null) {
            return null;
        }

        if(node.getKey() == currentNode.getKey()){
            if(currentNode.getLeft() == null && currentNode.getRight() == null){
                return null;
            }

            if(currentNode.getLeft() != null && currentNode.getRight() == null){
                return currentNode.getLeft();
            }

            if(currentNode.getRight() != null && currentNode.getLeft() == null){
                return currentNode.getRight();
            } else {
                Node inOrderSuccessor = findInOrderSuccessor(currentNode.getRight());
                currentNode.setRight(deleteNode(currentNode.getRight(), inOrderSuccessor));
                currentNode.setKey(inOrderSuccessor.getKey());
                return currentNode;
            }
        }

        currentNode.setLeft( deleteNode(currentNode.getLeft(), node));
        currentNode.setRight( deleteNode(currentNode.getRight(), node));

        return currentNode;
    }

    private Node findInOrderSuccessor(Node node) {
        Node successorNode = node;

        while(successorNode.getLeft() != null){
            successorNode = successorNode.getLeft();
        }

        return successorNode;
    }

    public void printInorder(Node node){
        if(node != null){

            printInorder(node.getLeft());

            System.out.print(" " + node.getKey());

            printInorder(node.getRight());
        }
    }

}

