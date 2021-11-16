package com.epam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetSumDynamicProblem {
    private final int value;
    public SubsetSumDynamicProblem(){
        this.value = 100;
    }

    public static void main(String[] args) {
        //int[] sum = new int[]{5, 2, 1, 3, -1};
        int[] sum = new int[]{5, 2, 1, 3};
        int subSetSum = 11;
        int subSetSum1 = 6;
        boolean[][] subSetSumTable = new boolean[sum.length + 1][subSetSum + 1];
        boolean[][] resultsTable = new boolean[sum.length + 1][subSetSum1 + 1];

        solve(subSetSumTable, subSetSum, sum);
        solve1(resultsTable, subSetSum1, sum);

        printAvailableSubsets(resultsTable, subSetSum1, sum);
        printAvailableSubsets(resultsTable, 5, sum);

        verifySolution(9, subSetSumTable, sum.length, sum);
        verifySolution(6, subSetSumTable, sum.length, sum);
        verifySolution(11, subSetSumTable, sum.length, sum);

        SubsetSumDynamicProblem subsetSumDynamicProblem = new SubsetSumDynamicProblem();
        Test test = subsetSumDynamicProblem.new Test();
        test.verifyCounter();

        Test1 test1 = new SubsetSumDynamicProblem.Test1();
        test1.getBuilder();
    }

    private static void solve(boolean[][] subSetSumTable, int subSetSum, int[] sum) {
        for (int rowIndex = 0; rowIndex < sum.length + 1; rowIndex++) {
            subSetSumTable[rowIndex][0] = true;
        }

        for (int sumIndex = 1; sumIndex <= sum.length; sumIndex++) {
            for (int subSum = 1; subSum <= subSetSum; subSum++) {
                if (subSum < sum[sumIndex - 1] || subSetSumTable[sumIndex - 1][subSum]) {
                    subSetSumTable[sumIndex][subSum] = subSetSumTable[sumIndex - 1][subSum];
                } else {
                    subSetSumTable[sumIndex][subSum] = subSetSumTable[sumIndex - 1][subSum - sum[sumIndex - 1]];
                }
            }
        }
    }

    private static void solve1(boolean[][] resultsTable, int subSetSum, int[] inputElements) {
        for (int rowIndex = 0; rowIndex <= inputElements.length; rowIndex++) {
            resultsTable[rowIndex][0] = true;
        }

        for (int elementIndex = 1; elementIndex <= inputElements.length; elementIndex++) {
            for (int subSum = 1; subSum <= subSetSum; subSum++) {

                if (subSum < inputElements[elementIndex - 1] || resultsTable[elementIndex - 1][subSum]) {
                    resultsTable[elementIndex][subSum] = resultsTable[elementIndex - 1][subSum];
                } else {
                    resultsTable[elementIndex][subSum] = resultsTable[elementIndex - 1][subSum - inputElements[elementIndex - 1]];
                }
            }
        }
    }

    private static void printAvailableSubsets(boolean[][] resultsTable, int subSetSum1, int[] elements) {
        for (int elementsIndex = elements.length; elementsIndex > 0; elementsIndex--) {
            int currentSum = subSetSum1;
            int currentIndex = elementsIndex;

            List<Integer> solutionList = new ArrayList<>();

            while (currentIndex > 0 && currentSum > 0) {
                if (resultsTable[currentIndex][currentSum] && resultsTable[currentIndex - 1][currentSum]) {
                    if (elements[currentIndex - 1] == subSetSum1) {
                        solutionList.add(elements[currentIndex - 1]);
                        break;
                    }

                    if (resultsTable[currentIndex - 1][currentSum - elements[currentIndex - 1]]) {
                        currentSum = currentSum - elements[currentIndex - 1];
                        solutionList.add(elements[currentIndex - 1]);
                    }
                } else if (resultsTable[currentIndex][currentSum]) {
                    currentSum = currentSum - elements[currentIndex - 1];
                    solutionList.add(elements[currentIndex - 1]);
                }

                currentIndex = currentIndex - 1;
            }

            if (currentSum == 0 && !solutionList.isEmpty()) {
                System.out.println("Subset sum " + subSetSum1 + " can be formed with " + solutionList);
            }
        }
    }

    private static void verifySolution(int subSetSum, boolean[][] subSetSumTable, int elementsToConsider, int[] sum) {
        if (subSetSumTable[elementsToConsider][subSetSum]) {
            System.out.println(" ++++++ Feasible solution does exists +++++++++ for subset sum " + subSetSum);
            int[] solutionArray = new int[elementsToConsider];
            findElementsToInclude(elementsToConsider, subSetSumTable, subSetSum, solutionArray, 0, sum);
        } else {
            System.out.println("Feasible solution doesn't exists for subset sum " + subSetSum);
        }
    }

    private static void findElementsToInclude(int elementsToConsider, boolean[][] subSetSumTable, int subSetSum, int[] solutionArray,
                                              int solutionIndex, int[] sum) {
        if (subSetSum <= 0) {
            printElements(solutionArray);
        } else if (elementsToConsider > 0 && subSetSumTable[elementsToConsider][subSetSum]) {
            //including the element
            if(subSetSum - sum[elementsToConsider - 1] < subSetSumTable[0].length){
                solutionArray[solutionIndex] = sum[elementsToConsider - 1];
                findElementsToInclude(elementsToConsider - 1, subSetSumTable, subSetSum - sum[elementsToConsider - 1], solutionArray, solutionIndex + 1, sum);
            }

            //excluding the element
            solutionArray[solutionIndex] = 0;
            findElementsToInclude(elementsToConsider - 1, subSetSumTable, subSetSum, solutionArray, solutionIndex, sum);
        }
    }


    private static void printElements(int[] solutionArray) {
        if (solutionArray[0] != 0) {
            System.out.println("One of the subset is ");
            Arrays.stream(solutionArray).filter(value -> value != 0).forEach(val -> System.out.print(" " + val));
            System.out.println("");
        }
    }

    private class Test{
        private void verifyCounter(){
            System.out.println("Counter value " + SubsetSumDynamicProblem.this.value);
        }
    }

    protected static class Test1{
       private Test1 getBuilder(){
           return new Test1();
       }

    }
}


