package com.epam.linkedlist;

import java.util.HashMap;

public class RemoveDuplicatesInLinkedList {
    public static void main(String[] args) {
        LinkedListCustom linkedList = getLinkedList();

        System.out.println("Before removing duplicates");
        linkedList.printElements();

        removeDuplicates(linkedList.root);

        System.out.println("\n Before removing duplicates");
        linkedList.printElements();

        linkedList = getLinkedList();

        System.out.println("\n Before removing duplicates - using hashmap");
        linkedList.printElements();

        removeDuplicatesHashMap(linkedList.root);

        System.out.println("Before removing duplicates - using hashmap");
        linkedList.printElements();
    }

    private static void removeDuplicates(LinkedListCustom.Node root) {
        LinkedListCustom.Node currentNode = root;

        while (currentNode != null) {
            LinkedListCustom.Node prevInnerNode = currentNode;
            LinkedListCustom.Node innerCurrentNode = currentNode.next;

            while (innerCurrentNode != null) {
                if (innerCurrentNode.data != currentNode.data) {
                    prevInnerNode = innerCurrentNode;
                    innerCurrentNode = innerCurrentNode.next;
                } else {
                    innerCurrentNode = innerCurrentNode.next;
                    prevInnerNode.next = innerCurrentNode;
                }
            }
            currentNode = currentNode.next;
        }
    }

    private static void removeDuplicatesHashMap(LinkedListCustom.Node root) {
        LinkedListCustom.Node currentNode = root;
        LinkedListCustom.Node prevNode = null;
        HashMap<Integer, Boolean> nodeMapByKey = new HashMap<>();

        while (currentNode != null) {
            Boolean isNodeExists = nodeMapByKey.get(currentNode.data);

            if (isNodeExists != null && isNodeExists) {
                currentNode = currentNode.next;
                prevNode.next = currentNode;
            } else {
                nodeMapByKey.put(currentNode.data, true);
                prevNode = currentNode;
                currentNode = currentNode.next;
            }
        }
    }


    private static LinkedListCustom getLinkedList() {
        LinkedListCustom linkedList = new LinkedListCustom();

        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(2);
        linkedList.addNode(5);
        linkedList.addNode(1);
        return linkedList;
    }
}
