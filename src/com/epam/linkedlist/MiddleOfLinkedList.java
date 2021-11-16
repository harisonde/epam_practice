package com.epam.linkedlist;

public class MiddleOfLinkedList {

    public static void main(String[] args){
        LinkedListCustom linkedList = new LinkedListCustom();

        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(4);
        linkedList.addNode(5);
        linkedList.addNode(6);

        solve(linkedList.root);

        linkedList.removeNode(6);

        solve(linkedList.root);

        linkedList.removeNode(5);
        linkedList.removeNode(4);
        linkedList.removeNode(3);

        solve(linkedList.root);
    }

    private static void solve(LinkedListCustom.Node root) {
        LinkedListCustom.Node fastPointer = root;
        LinkedListCustom.Node slowPointer = root;
        LinkedListCustom.Node prev_slowPointer = null;

        while (fastPointer != null && fastPointer.next != null && slowPointer.next != null) {
            fastPointer = fastPointer.next.next;
            prev_slowPointer = slowPointer;
            slowPointer = slowPointer.next;
        }

        if (fastPointer == null && prev_slowPointer != null) {
            System.out.println("Middle of Linked List is " + prev_slowPointer.data + " And " + slowPointer.data);
        } else {
            assert slowPointer != null;
            System.out.println("Middle of Linked List is " + slowPointer.data);
        }

    }
}
