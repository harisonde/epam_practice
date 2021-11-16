package com.epam.linkedlist;

public class ReverseLinkedList {
    public static void main(String[] args) {
        LinkedListCustom linkedList = new LinkedListCustom();

        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(4);
        linkedList.addNode(5);
        linkedList.addNode(6);

        System.out.println("Initial Linked list data  ");
        linkedList.printElements();

        solve(linkedList);

        System.out.println();
        System.out.println("Linked List after reversing");
        linkedList.printElements();

        System.out.println();
        System.out.println("Reverse Linked list starting from node 3");
        LinkedListCustom.Node node = linkedList.getNode(4);

        node.next = reverseElementsStartingFrom(node.next);
        linkedList.printElements();

        System.out.println();
        System.out.println("Reverse Linked list starting from node 4");
        LinkedListCustom.Node nodeFive = linkedList.getNode(5);

        nodeFive.next = reverseElementsStartingFrom(nodeFive.next);
        linkedList.printElements();
    }

    private static void solve(LinkedListCustom linkedList) {
        LinkedListCustom.Node prevNode = null;
        LinkedListCustom.Node currentNode = linkedList.root;

        while (currentNode != null) {
            LinkedListCustom.Node nextNode = currentNode.next;
            currentNode.next = prevNode;
            prevNode = currentNode;
            currentNode = nextNode;
        }

        if (prevNode != null) {
            linkedList.root = prevNode;
        }
    }

    private static LinkedListCustom.Node reverseElementsStartingFrom(LinkedListCustom.Node node) {
        LinkedListCustom.Node prevNode = null;
        LinkedListCustom.Node currentNode = node;

        while (currentNode != null) {
            LinkedListCustom.Node nextNode = currentNode.next;
            currentNode.next = prevNode;
            prevNode = currentNode;
            currentNode = nextNode;
        }

        return prevNode;
    }


    private static void printElements(LinkedListCustom linkedListCustom) {
        printNodes(linkedListCustom.root);
    }

    private static void printNodes(LinkedListCustom.Node currentNode) {
        while (currentNode != null) {
            System.out.print(" " + currentNode.data);
            currentNode = currentNode.next;
        }
    }

    private static void printElements(LinkedListCustom.Node currentNode) {
        printNodes(currentNode);
    }
}
