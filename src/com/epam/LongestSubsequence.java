package com.epam;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestSubsequence {
    public static void main(String[] args){
        int[] input = new int[]{9, 7, 4, 12, 13, 1, 14, 78, 15};

        int longestSubsequence = solve(input);

        System.out.println("Longest subsequence is " + longestSubsequence);

        input = new int[]{2, 3, 1, 6, 4, 5, 7};

        longestSubsequence = solve(input);

        System.out.println("Longest subsequence is " + longestSubsequence);
    }

    private static int solve(int[] array) {
        Set<Integer> inputSet = new HashSet<>();
        int longestSubSequence = 1;

        Arrays.stream(array).forEach(inputSet::add);

        int currentSequence = 0;

        for (int currentElement : array) {
            currentSequence = 0;
            if (!inputSet.contains(currentElement - 1)) {
                while (inputSet.contains(currentElement++)) {
                    currentSequence = currentSequence + 1;
                }
            }

            longestSubSequence = Math.max(longestSubSequence, currentSequence);
        }

        return longestSubSequence;
    }
}
