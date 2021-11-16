package com.epam;

import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargestElement {

    public static void main(String[] args){
        int[] input = new int[]{10, 4, 8, 90, 32, 76, 31, 56, 21, 898};


        //4th Largest element in this array
        if(largest(input, 4) == 56){
            System.out.println("4th largest element determined correctly!!");
        } else{
            System.out.println("Could not determine 4th largest element correctly");
        }

        if(largest(input, 8) == 10){
            System.out.println("8th largest element determined correctly!!");
        } else{
            System.out.println("Could not determine 8th largest element correctly");
        }

        if(largest(input, 10) == 4){
            System.out.println("10th largest element determined correctly!!");
        } else{
            System.out.println("Could not determine 8th largest element correctly");
        }
    }

    private static int largest(int[] input, int kthLargestElement) {
        Queue<Integer> queue = new PriorityQueue<>();

        if (input.length < kthLargestElement) {
            return -1;
        }

        for (int index = 0; index < kthLargestElement; index++) {
            queue.add(input[index]);
        }

        for (int index = kthLargestElement; index < input.length; index++) {
            int elementAtFirstIndexInQueue = queue.peek() != null ? queue.peek() : -1;

            if (elementAtFirstIndexInQueue < input[index]) {
                queue.poll();
                queue.add(input[index]);
            }
        }

        return queue.peek() != null ? queue.peek() : -1;
    }
}
