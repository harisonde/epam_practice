package com.epam;

import java.util.ArrayList;
import java.util.List;

public class SubsetSumNonDP {

    public static void main(String[] args) {
        int[] elements = new int[]{5, 2, 1, 3};
        int subSetSum = 6;

        solve(elements, subSetSum);
    }

    private static void solve(int[] elements, int subSetSum) {
        List<Integer> solutionSubSet = new ArrayList<>();
        solve(elements, subSetSum, solutionSubSet, 0);
    }

    private static void solve(int[] elements, int subSetSum, List<Integer> solutionSubSet, int currentIndex) {
        if (subSetSum == 0) {
            printSolution(solutionSubSet);
        } else if (currentIndex < elements.length && subSetSum > 0) {
            if (elements[currentIndex] <= subSetSum) {
                //including current element
                solutionSubSet.add(elements[currentIndex]);
                solve(elements, subSetSum - elements[currentIndex], solutionSubSet, currentIndex + 1);

                //excluding current element
                solutionSubSet.remove((Integer) elements[currentIndex]);
            }
            solve(elements, subSetSum, solutionSubSet, currentIndex + 1);
        }
    }

    private static void printSolution(List<Integer> solutionSubSet) {
        System.out.println("Subset sum can be formed with " + solutionSubSet);
    }
}


