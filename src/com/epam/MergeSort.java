package com.epam;

import java.util.stream.IntStream;

public class MergeSort {
    public static void main(String[] args){
        int[] input = new int[]{10, 17, 67, 89, 4, 22, 45, 98};
        merge(input, 0, 3, 7);
        System.out.print("Merged Array is ");
        IntStream.of(input).forEach(val -> System.out.print(" " + val));
        System.out.println();

        input = new int[]{98, 45, 4, 10, 69, 17, 22, 89};
        mergeSort(input, 0, input.length-1);

        System.out.print("Sorted Array is ");
        IntStream.of(input).forEach(val -> System.out.print(" " + val));
    }

    private static void mergeSort(int[] input, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(input, start, mid);
            mergeSort(input, mid + 1, end);
            merge(input, start, mid, end);
        }
    }

    private static void merge(int[] input, int start, int mid, int end) {
        int[] input1 = new int[mid - start + 1];
        int[] input2 = new int[end - mid];

        for (int index = start, i=0; index <= mid ; index++, i++) {
            input1[i] = input[index];
        }

        for (int index = mid + 1, i = 0; index <= end; index++, i++) {
            input2[i] = input[index];
        }

        int i = 0, j = 0;
        int index = start;

        while (i < input1.length && j < input2.length) {
            if (input1[i] < input2[j]) {
                input[index] = input1[i];
                i++;
            } else {
                input[index] = input2[j];
                j++;
            }
            index++;
        }

        while( i < input1.length){
            input[index] = input1[i];
            i++;
            index++;
        }

        while( j < input2.length){
            input[index] = input2[j];
            j++;
            index++;
        }

    }
}
