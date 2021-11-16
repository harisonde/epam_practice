package com.epam.linkedlist;

public class LinkedListCustom {
    Node root;

    public static void main(String[] args) {
        LinkedListCustom linkedListCustom = new LinkedListCustom();

        linkedListCustom.addNode(10);
        linkedListCustom.addNode(20);
        linkedListCustom.addNode(30);
        linkedListCustom.addNode(40);

        linkedListCustom.removeNode(30);
        linkedListCustom.removeNode(30);
    }

    public Node getNode(int data) {
        Node resultNode = null;

        if (this.root != null) {
            Node currentNode = this.root;

            while (currentNode.next != null && resultNode == null) {
                if (currentNode.data == data) {
                    resultNode = currentNode;
                }
                currentNode = currentNode.next;
            }
        }
        return resultNode;
    }

    public void addNode(int data) {
        Node node = new Node(data);
        addNodeUtil(data, node);
    }

    public void addNode(Node node) {
        addNodeUtil(node.data, node);
    }

    private void addNodeUtil(int data, Node node) {
        if (root == null) {
            root = node;
        } else {
            root = addNodeUtil(root, data);
        }
    }

    private Node addNodeUtil(Node currentNode, int data) {
        if (currentNode == null) {
            Node node = getNode(data);
            return node != null ? node : new Node(data);
        }

        if (currentNode.data != data) {
            currentNode.next = addNodeUtil(currentNode.next, data);
        }

        return currentNode;
    }


    public void removeNode(int data) {
        if (root != null) {
            if (root.data == data) {
                root = root.next;
            } else {
                root.next = removeNodeUtil(root.next, data);
            }
        }
    }

    private Node removeNodeUtil(Node currentNode, int data) {
        if (currentNode == null) {
            return null;
        }

        if (currentNode.data == data) {
            return currentNode.next;
        }

        currentNode.next = removeNodeUtil(currentNode.next, data);
        return currentNode;
    }

    public int getSize() {
        int size = 0;
        Node currentNode = root;

        while (currentNode != null) {
            size++;
            currentNode = currentNode.next;
        }
        return size;
    }

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public void printElements() {
        LinkedListCustom.Node currentNode = this.root;

        while (currentNode != null) {
            System.out.print(" " + currentNode.data);
            currentNode = currentNode.next;
        }
    }

    public void printElements(LinkedListCustom.Node currentNode) {
        while (currentNode != null) {
            System.out.print(" " + currentNode.data);
            currentNode = currentNode.next;
        }
    }
}
