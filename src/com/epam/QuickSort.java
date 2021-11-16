package com.epam;

import java.util.stream.IntStream;

public class QuickSort {
    public static void main(String[] args) {
        int[] input = new int[]{12, 13, 24, 10, 3, 6, 90, 70};

        System.out.print("Input prior to sorting!!");
        IntStream.of(input).forEach(val -> System.out.print(" " + val));
        System.out.println(" ");

        quickSort(input, 0, input.length - 1);

        System.out.print("Input after to sorting!!");
        IntStream.of(input).forEach(val -> System.out.print(" " + val));
        System.out.println(" ");

        input = new int[]{12, 13, 24, 10, 3, 6, 90, 70};

        System.out.print("NEW : Input prior to sorting!!");
        IntStream.of(input).forEach(val -> System.out.print(" " + val));
        System.out.println(" ");

        sort(input, 0, input.length - 1);

        System.out.print("NEW : Input after to sorting!!");
        IntStream.of(input).forEach(val -> System.out.print(" " + val));
        System.out.println(" ");
    }


    private static void sort(int[] input, int start, int end) {
        if (start < end) {

            int pivot = (start + end) / 2;

            int leftIndex = start;
            int rightIndex = end;

            while (leftIndex < pivot) {
                if (input[leftIndex] > input[pivot]) {
                    int temp = input[pivot];
                    input[pivot] = input[leftIndex];
                    input[leftIndex] = temp;
                    break;
                }
                leftIndex++;
            }

            while (pivot < rightIndex) {
                if (input[rightIndex] < input[pivot]) {
                    int temp = input[pivot];
                    input[pivot] = input[rightIndex];
                    input[rightIndex] = temp;
                    break;
                }
                rightIndex--;
            }

            sort(input, leftIndex + 1, end);
            sort(input, start, rightIndex - 1);
        }
    }

    private static void quickSort(int[] input, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            int pivot = input[mid];

            int i = low, j = high;

            while (i <= j) {
                while (input[i] < pivot) {
                    i++;
                }

                while (input[j] > pivot) {
                    j--;
                }

                if (i <= j) {
                    swap(input, i, j);
                    i++;
                    j--;
                }
            }

            if (low < j) {
                quickSort(input, low, j);
            }

            if (high > i) {
                quickSort(input, i, high);
            }
        }
    }

    private static void swap(int[] input, int indexA, int indexB) {
        int temp = input[indexA];
        input[indexA] = input[indexB];
        input[indexB] = temp;
    }
}
