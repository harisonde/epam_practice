package com.epam.linkedlist;

public class MergeLinkedListsInAlternatePositions {
    private static LinkedListCustom linkedList1;
    private static LinkedListCustom linkedList2;

    public static void main(String[] args) {
        linkedList1 = getLinkedList1();
        linkedList2 = getLinkedList2();

        System.out.println("Linked List 1 before merge");
        linkedList1.printElements();

        System.out.println("\nLinked List 2 before merge");
        linkedList2.printElements();

        merge();

        System.out.println("\nLinked List 1 after merge");
        linkedList1.printElements();

        System.out.println("\nLinked List 2 after merge");
        linkedList2.printElements();
    }

    private static void merge() {
        LinkedListCustom.Node head1 = linkedList1.root;
        LinkedListCustom.Node head2 = linkedList2.root;

        while (head1 != null && head2 != null) {
            LinkedListCustom.Node head1Next = head1.next;
            LinkedListCustom.Node head2Next = head2.next;
            head1.next = head2;
            head1.next.next = head1Next;
            head2 = head2Next;
            head1 = head1Next;
        }

        linkedList2.root = head2;

    }

    private static LinkedListCustom getLinkedList1() {
        LinkedListCustom linkedList = new LinkedListCustom();

        linkedList.addNode(1);
        linkedList.addNode(3);
        linkedList.addNode(5);
        return linkedList;
    }

    private static LinkedListCustom getLinkedList2() {
        LinkedListCustom linkedList = new LinkedListCustom();

        linkedList.addNode(2);
        linkedList.addNode(4);
        linkedList.addNode(6);
        linkedList.addNode(8);
        linkedList.addNode(9);
        return linkedList;
    }
}
