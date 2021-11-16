package com.epam.linkedlist;

public class PartitionLinkedListNodes {
    static class Node {
        int data;
        Node next;
    }

    static Node newNode(int data) {
        Node new_node = new Node();
        new_node.data = data;
        new_node.next = null;
        return new_node;
    }

    static Node partition(Node head, int x) {
        Node tail = head;

        // Now iterate original list and connect nodes
        Node curr = head;
        while (curr != null) {
            Node next = curr.next;
            if (curr.data < x) {
                /* Insert node at head. */
                curr.next = head;
                head = curr;
            } else // Append to the list of greater values
            {
                /* Insert node at tail. */
                tail.next = curr;
                tail = curr;
            }
            curr = next;
        }

        tail.next = null;

        return head;
    }

    /* Function to print linked list */
    static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        Node head = newNode(1);
        head.next = newNode(5);
        head.next.next = newNode(8);
        head.next.next.next = newNode(2);
        head.next.next.next.next = newNode(10);
        head.next.next.next.next.next = newNode(2);
        head.next.next.next.next.next.next = newNode(3);

        System.out.println("Nodes before partition data by 5");
        printList(head);

        int x = 5;
        head = partition(head, x);

        System.out.println();
        printList(head);
    }
}
