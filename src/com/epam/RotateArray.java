package com.epam;

import java.util.Arrays;

class RotateArray {
    public static void main(String[] args){
        int[] A = new int[]{3, 8, 9, 7, 6};
        int K = 3;

        int[] output = solution(A, K);

        Arrays.stream(output).forEach(element -> System.out.print(" " + element));
    }

    public static int[] solution(int[] A, int K) {

        if(K < 0 || K > A.length){
            return A;
        }

        int[] output = new int[A.length];

        int outputArrayIndex =0;

        for(int index=A.length - K; index< A.length; index++){
            output[outputArrayIndex++] = A[index];
        }

        for(int index=0; index< K-1; index++){
            output[outputArrayIndex++] = A[index];
        }

        return output;
    }
}

