package com.epam.linkedlist;

public class DeleteNthNodeFromEnd {
    public static void main(String[] args) {
        LinkedListCustom linkedList = new LinkedListCustom();

        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(4);
        linkedList.addNode(5);
        linkedList.addNode(6);

        System.out.println("Elements before replacing element from 2th at the end") ;
        linkedList.printElements();

        solve(linkedList, 2);

        System.out.println() ;
        System.out.println("Elements After replacing element from 2th at the end ") ;
        linkedList.printElements();

        System.out.println() ;
        System.out.println("Elements before replacing element from 3th at the end") ;
        linkedList.printElements();

        solve(linkedList, 3);

        System.out.println() ;
        System.out.println("Elements After replacing element from 3th at the end ") ;
        linkedList.printElements();

        System.out.println() ;
        System.out.println("Elements before replacing element from 4th at the end") ;
        linkedList.printElements();

        solve(linkedList, 4);

        System.out.println() ;
        System.out.println("Elements After replacing element from 4th at the end ") ;
        linkedList.printElements();

    }

    private static void solve(LinkedListCustom linkedList, int position) {
        int size = linkedList.getSize();

        LinkedListCustom.Node currentNode = linkedList.root;
        LinkedListCustom.Node prevNode = null;

        for (int start = 1; start < size - position + 1; start++) {
            prevNode = currentNode;
            currentNode = currentNode.next;
        }

        if (prevNode == null) {
            linkedList.root = currentNode.next;
        } else {
            prevNode.next = currentNode.next;
        }
    }
}
