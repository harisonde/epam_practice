package com.epam.linkedlist;

public class PrintLastKItems {
    private static int count = 0;

    public static void main(String[] args) {
        LinkedListCustom linkedList = new LinkedListCustom();

        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(4);
        linkedList.addNode(5);
        linkedList.addNode(6);

        System.out.println("Elements before removing last 3 elements");
        linkedList.printElements();

        System.out.println("\nLast 3 nodes of Linked list is ");

        solve(linkedList.root, 3);
    }

    private static void solve(LinkedListCustom.Node currentNode, int kthElement) {
        if (currentNode != null) {

            solve(currentNode.next, kthElement);

            count = count + 1;

            if (count <= kthElement) {
                System.out.print(" " + currentNode.data);
            }
        }
    }
}
