package com.epam;

public class LargestSumSubArray {
    public static void main(String[] args) {
        int[] input = new int[]{-3, -2, 4, -1, -2, 1, 5};

        largestSumSubArray(input);
        largestSubArraySumNegative(input);
        solve(input);
    }

    private static void solve(int[] input) {
        int maxSum = 0, startIndex = 0, endIndex = 0;

        int currentSum = 0;

        for (int index = 0; index < input.length; index++) {
            currentSum = currentSum + input[index];

            if (currentSum < 0) {
                startIndex = index + 1;
                currentSum = 0;
            }

            if (currentSum > maxSum) {
                endIndex = index;
                maxSum = currentSum;
            }
        }

        System.out.println("Max Sub Array sum is " + maxSum + " with Start index of " + startIndex + " And End index of " + endIndex);
    }

    private static void largestSumSubArray(int[] input) {
        int start = 0, end = 0, currentSum = 0, maxSum = 0, startIndex = 0;

        for (int index = 0; index < input.length; index++) {
            currentSum = currentSum + input[index];

            if (currentSum < 0) {
                startIndex = index + 1;
                currentSum = 0;
            }

            if (maxSum < currentSum) {
                maxSum = currentSum;
                start = startIndex;
                end = index;
            }
        }

        System.out.println("Max Sub Array sum is " + maxSum + " with Start index of " + start + " And End index of " + end);
    }

    private static void largestSubArraySumNegative(int[] input) {
        int currentMax = input[0], maxSoFar = input[0], start = 0, end = 0, currStartIndex = 0;

        for (int index = 1; index < input.length; index++) {
            currentMax = currentMax + input[index];
            if (input[index] > currentMax) {
                currStartIndex = index;
                currentMax = input[index];
            }

            if (currentMax > maxSoFar) {
                start = currStartIndex;
                end = index;
                maxSoFar = currentMax;
            }
        }

        System.out.println("Max Sub Array sum is " + maxSoFar + " with Start index of " + start + " And End index of " + end);
    }
}
