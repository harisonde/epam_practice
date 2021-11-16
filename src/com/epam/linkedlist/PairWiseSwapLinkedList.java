package com.epam.linkedlist;

public class PairWiseSwapLinkedList {
    public static void main(String[] args) {
        LinkedListCustom linkedList = new LinkedListCustom();

        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(4);
        linkedList.addNode(5);

        System.out.println("Elements Before pairwise swap");
        linkedList.printElements();;

        solve(linkedList.root);

        System.out.println("\nElements after pairwise swap");
        linkedList.printElements();

        linkedList = new LinkedListCustom();

        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(4);
        linkedList.addNode(5);
        linkedList.addNode(6);

        solveRecursive(linkedList.root);
        System.out.println("\nElements after pairwise swap recursive");
        linkedList.printElements();
    }

    private static void solve(LinkedListCustom.Node root) {
        LinkedListCustom.Node node = root;

        while(node != null && node.next != null){
            int data = node.data;
            node.data = node.next.data;
            node.next.data = data;
            node = node.next.next;
        }
    }

    private static void solveRecursive(LinkedListCustom.Node node) {
        if (node != null && node.next != null) {
            solveRecursive(node.next.next);

            swap(node);
        }
    }

    private static void swap(LinkedListCustom.Node node) {
        int data = node.data;
        node.data = node.next.data;
        node.next.data = data;
    }
}
