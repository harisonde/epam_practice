package com.epam;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello program");

        System.out.println("Element position in list using linear search is " + search(Arrays.asList(10, 17, 18, 5, 4, 0, 10), 5));
        System.out.println("Element position in list using linear search is " + binarySearch(Arrays.asList(10, 17, 20, 25, 32, 39, 45, 89, 95), 95));
        //System.out.println("Element position in list the is " + search(Arrays.asList(10, 17, 18, 5, 4, 0, 10), 29));
    }

    private static int binarySearch(List<Integer> input, int element) {
        return binarySearch(input, element, 0, input.size());
    }

    private static int search(List<Integer> input, int element) {
        if (input != null && input.size() > 0) {
            return search(input, element, 0);
        }

        return -1;
    }

    private static int search(List<Integer> input, int element, int index) {
        if (index >= input.size()) {
            return -1;
        }

        if (input.get(index) == element) {
            return index;
        }

        return search(input, element, ++index);
    }

    private static int binarySearch(List<Integer> input, int element, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            if (input.get(mid) == element) {
                return mid;
            }

            if (input.get(mid) < element) {
                return binarySearch(input, element, mid + 1, high);
            } else {
                return binarySearch(input, element, low, mid - 1);
            }
        }
        return -1;
    }
}
