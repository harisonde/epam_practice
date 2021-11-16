package com.epam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShiftInSortedList {

    public static void main(String[] args) {
        //Integer[] input = new Integer[]{11, 12, 13, 14, 1, 2, 3};
        //int position = getStartOfShift(Arrays.asList(input));

        int[] input = new int[]{6, 7, 8, 9, 1, 2};

        List<Integer> elements = Arrays.stream(input)
                .flatMap(IntStream::of)
                .boxed()
                .collect(Collectors.toList());


        int position = getStartOfShift(elements, 0, elements.size() - 1);

        if (position == 4) {
            System.out.println("Start of shift in sorted list is found correctly");
        } else {
            System.out.println("Start of shift in sorted list could not be found");
        }
    }


    private static int getStartOfShift1(List<Integer> input, int start, int end) {
        if (start == end) {
            return start;
        }

        if (start < end) {
            int mid = (start + end) / 2;

            if (input.get(mid) > input.get(mid + 1)){
                return mid + 1;
            }

            if (input.get(mid) < input.get(mid - 1)) {
                return mid;
            }

            if(input.get(mid) < input.get(start)){
                return getStartOfShift1(input, start, mid - 1);
            } else {
                return getStartOfShift1(input, mid + 1, end);
            }
        }

        return -1;
    }


    private static int getStartOfShift(List<Integer> inputList, int start, int end) {
        int position = -1;

        if (start > end) {
            return position;
        }

        if (start == end) {
            return start;
        }

        // int[] input = new int[]{11, 12, 13, 14, 1, 2, 3};
        int mid = (start + end) / 2;

        if (inputList.get(mid) > inputList.get(mid + 1)) {
            return mid + 1;
        }

        if (inputList.get(mid) < inputList.get(mid - 1)) {
            return mid;
        }

        if (inputList.get(mid) > inputList.get(start)) {
            return getStartOfShift(inputList, mid + 1, end);
        } else {
            return getStartOfShift(inputList, start, mid - 1);
        }
    }
}
