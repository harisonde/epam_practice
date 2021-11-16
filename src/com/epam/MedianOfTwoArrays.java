package com.epam;

public class MedianOfTwoArrays {
    public static void main(String[] args){
        int[] ar1 = new int[]{-5, 2, 3, 6, 12, 15};
        int[] ar2 = new int[]{-12, -10, -6, -3, 4, 10};

        double median = solve(ar1, ar2);

        System.out.println("Median value of arrays is " + median);
    }

    private static double solve(int[] ar1, int[] ar2) {
        int input1Size = ar1.length;
        int input2Size = ar2.length;
        int medIndex = (input1Size + input2Size) / 2;

        medIndex = (input1Size + input2Size) % 2 == 0 ? medIndex - 1 : medIndex;

        int startIndex = 0, medianVal1 = 0, medianVal2 = 0;
        int index1 = 0, index2 = 0;
        int selectedVal;

        while (startIndex <= medIndex + 1 && index1 < input1Size && index2 < input2Size) {

            if (ar1[index1] > ar2[index2]) {
                selectedVal = ar2[index2];
                index2++;
            } else {
                selectedVal = ar1[index1];
                index1++;
            }

            if (startIndex == medIndex) {
                medianVal1 = selectedVal;
            }

            if (startIndex == medIndex + 1) {
                medianVal2 = selectedVal;
            }

            startIndex++;
        }

        if ((input1Size + input2Size) % 2 == 1) {
            return medianVal1;
        }

        return ((double) medianVal1 + (double) medianVal2) / 2;
    }
}
