package com.epam.linkedlist;

public class FindCycleInLinkedList {

    public static void main(String[] args) {
        LinkedListCustom linkedList = new LinkedListCustom();
        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(4);
        linkedList.addNode(5);
        linkedList.addNode(6);
        linkedList.addNode(7);
        linkedList.addNode(4);

        System.out.println("Does loop exists in linked list -> " + solve(linkedList));

        System.out.println("After removing loop: Does loop exists in linked list -> " + solve(linkedList));
    }

    private static boolean solve(LinkedListCustom linkedList) {
        boolean isLoopExists = false;
        LinkedListCustom.Node fastNode = linkedList.root;
        LinkedListCustom.Node slowNode = linkedList.root;

        while (fastNode != null && fastNode.next != null && slowNode.next != null) {
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;

            if (fastNode != null && fastNode.data == slowNode.data) {
                isLoopExists = true;
                break;
            }
        }

        if(isLoopExists) {
            fastNode = linkedList.root;

            while (fastNode.next != slowNode.next) {
                fastNode = fastNode.next;
                slowNode = slowNode.next;
            }

            System.out.println("Loop exists at node " + fastNode.next.data);

            slowNode.next = null;
        }

        return isLoopExists;
    }


}
