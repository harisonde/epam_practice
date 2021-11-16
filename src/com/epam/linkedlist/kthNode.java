package com.epam.linkedlist;

/*Given a Linked List and a number n,
        write a function that returns the value at the n’th node from the end of the Linked List.*/
public class kthNode {

    static int kthElement = -1;

    public static void main(String[] args) {
        LinkedListCustom linkedList = new LinkedListCustom();
        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(4);
        linkedList.addNode(5);
        linkedList.addNode(6);

        int result = solve(linkedList, 4);

        System.out.println("4th node from last in 1, 2, 3, 4, 5, 6 is -> " + result);
        System.out.println("2th node from last in 1, 2, 3, 4, 5, 6 is -> " + solve(linkedList, 2));
        System.out.println("6th node from last in 1, 2, 3, 4, 5, 6 is -> " + solve(linkedList, 6));

        recursive(linkedList, 4);

        System.out.println("Recursive: 4th node from last in 1, 2, 3, 4, 5, 6 is -> " + kthElement);

        recursive(linkedList, 2);
        System.out.println("Recursive: 2th node from last in 1, 2, 3, 4, 5, 6 is -> " + kthElement);

        recursive(linkedList, 6);
        System.out.println("Recursive: 6th node from last in 1, 2, 3, 4, 5, 6 is -> " + kthElement);
    }

    private static void recursive(LinkedListCustom linkedList, int position) {
        recurseUtil(linkedList.root, position);
    }

    private static int recurseUtil(LinkedListCustom.Node currentNode, int position) {
        if (currentNode == null) {
            return 0;
        }

        int currentPos = 1 + recurseUtil(currentNode.next, position);

        if (currentPos == position) {
            kthElement = currentNode.data;
        }
        return currentPos;
    }

    private static int solve(LinkedListCustom linkedList, int kthNode) {
        int result = -1;
        int start = 0;

        LinkedListCustom.Node currentNode = linkedList.root;

        while (start < kthNode && currentNode != null) {
            currentNode = currentNode.next;
            start++;
        }

        if (start == kthNode) {
            LinkedListCustom.Node resultNode = linkedList.root;

            while (currentNode != null) {
                currentNode = currentNode.next;
                resultNode = resultNode.next;
            }

            result = resultNode != null ? resultNode.data : result;
        }

        return result;
    }
}
