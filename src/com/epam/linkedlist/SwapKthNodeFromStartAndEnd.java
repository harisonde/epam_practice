package com.epam.linkedlist;

public class SwapKthNodeFromStartAndEnd {

    public static void main(String[] args) {
        LinkedListCustom linkedList = new LinkedListCustom();

        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(4);
        linkedList.addNode(5);
        linkedList.addNode(6);

        System.out.println("Elements before replacing elements at position 2 ");
        linkedList.printElements();

        solve(linkedList, 2);

        System.out.println();
        System.out.println("Elements after replacing elements at position 2 ");
        linkedList.printElements();

        linkedList = new LinkedListCustom();

        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(4);
        linkedList.addNode(5);
        linkedList.addNode(6);

        System.out.println();
        System.out.println("Elements before replacing elements at position 3 ");
        linkedList.printElements();

        solve(linkedList, 3);

        System.out.println();
        System.out.println("Elements after replacing elements at position 3 ");
        linkedList.printElements();
    }

    private static void solve(LinkedListCustom linkedListCustom, int position) {
        int linkedListSize = getLinkedListSize(linkedListCustom);
        LinkedListCustom.Node nodeFromStart = getNodeFromStart(linkedListCustom, position);
        LinkedListCustom.Node nodeFromEnd = getNodeFromEnd(linkedListCustom, position, linkedListSize);

        replaceNode(linkedListCustom, nodeFromStart, nodeFromEnd);
    }

    private static LinkedListCustom.Node getNodeFromStart(LinkedListCustom linkedListCustom, int position) {
        int start = 1;
        LinkedListCustom.Node currentNode = linkedListCustom.root;

        while (start++ < position) {
            currentNode = currentNode.next;
        }

        return currentNode;
    }

    private static LinkedListCustom.Node getNodeFromEnd(LinkedListCustom linkedListCustom, int end, int linkedListSize) {

        LinkedListCustom.Node nodeFromEnd = linkedListCustom.root;
        for (int start = 1; start < linkedListSize - end + 1; start++) {
            nodeFromEnd = nodeFromEnd.next;
        }

        return nodeFromEnd;
    }

    private static int getLinkedListSize(LinkedListCustom linkedListCustom) {
        int size = 0;

        LinkedListCustom.Node currentNode = linkedListCustom.root;

        while (currentNode != null) {
            currentNode = currentNode.next;
            size++;
        }
        return size;
    }

    private static void replaceNode(LinkedListCustom linkedListCustom, LinkedListCustom.Node node1, LinkedListCustom.Node node2) {

        //Replace first node
        LinkedListCustom.Node currentNode = linkedListCustom.root;
        LinkedListCustom.Node prevNode = null;

        while (currentNode.data != node1.data) {
            prevNode = currentNode;
            currentNode = currentNode.next;
        }

        LinkedListCustom.Node nodeTobeReplaced = new LinkedListCustom.Node(node2.data);
        nodeTobeReplaced.next = currentNode.next;

        if (prevNode != null) {
            prevNode.next = nodeTobeReplaced;
        }

        //Replace secondNode
        prevNode = nodeTobeReplaced;
        currentNode = nodeTobeReplaced.next;

        while (currentNode.data != node2.data) {
            prevNode = currentNode;
            currentNode = currentNode.next;
        }

        nodeTobeReplaced = new LinkedListCustom.Node(node1.data);
        nodeTobeReplaced.next = currentNode.next;
        prevNode.next = nodeTobeReplaced;
    }
}
