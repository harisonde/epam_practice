package com.epam.tree;

import java.util.*;

public class BinaryTreeTopView {

    // Driver Program to test above functions
    public static void main(String[] args) {
        /* Create following Binary Tree
         1
        / \
      2    3
        \
          4
            \
            5
               \
                 6 */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(4);
        root.left.right.right = new Node(5);
        root.left.right.right.right = new Node(6);
        System.out.println(
                "Following are nodes in top view of Binary Tree");
        topView(root);
    }

    private static void topView(Node root) {
        if (root != null) {
            System.out.print("Elements in top view are -> " );

            Set<Integer> elementsInTopView = new HashSet<>();
            Map<Integer, Integer> elementsHeight = new HashMap<>();
            elementsHeight.put(root.data, 0);

            Queue<Node> nodes = new LinkedList<>();
            nodes.offer(root);

            while (!nodes.isEmpty()) {

                Node currentNode = nodes.poll();
                Integer currentNodeHeight = elementsHeight.get(currentNode.data);

                if (!elementsInTopView.contains(currentNodeHeight)) {
                    System.out.print(" " + currentNode.data);
                    elementsInTopView.add(currentNodeHeight);
                }

                if (currentNode.left != null) {
                    nodes.offer(currentNode.left);
                    elementsHeight.put(currentNode.left.data, currentNodeHeight - 1);
                }

                if (currentNode.right != null) {
                    nodes.offer(currentNode.right);
                    elementsHeight.put(currentNode.right.data, currentNodeHeight + 1);
                }
            }
        }

    }

    static class Node {
        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }
}
