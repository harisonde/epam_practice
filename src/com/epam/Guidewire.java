package com.epam;

public class Guidewire {
    public static void main(String[] args){
        int A = 123456;

        int solution = solution(A);
        solution = solution1(A);

        System.out.println(solution);
        System.out.println(solution(-1));
        System.out.println(solution(9));
        System.out.println(solution(99));
        System.out.println(solution(101));

    }

    public static int solution(int A) {
        if(A <= 100){
            return A;
        }

        String input = String.valueOf(A);
        StringBuilder output = new StringBuilder();

        int startIndex=0;
        int endIndex = input.length()-1;

        int size =0;

        while(size < input.length()) {
            if (size++ % 2 == 0) {
                output.insert(  output.length(), input.charAt(startIndex++));
            } else {
                output.insert(output.length(), input.charAt(endIndex--));
            }
        }

        return Integer.parseInt(String.valueOf(output));
    }

    public static int solution1(int A) {
        if(A <= 100){
            return A;
        }

        String input = String.valueOf(A);
        int output = 0;

        int startIndex = getNumberLength(A);
        int endIndex = 1;

        int size =0;

        int digitPos = 1;

        while(size < input.length()) {
            if (size++ % 2 == 0) {
               output = output + (A / startIndex) % 10 * digitPos;
               startIndex = startIndex / 10;
            } else {
                output = output + (A / endIndex) % 10 * digitPos;
                endIndex = endIndex * 10;
            }

            digitPos = digitPos * 10;
        }

        return output;
    }

    private static int getNumberLength(int input) {
        int index = 1;

        while(input > 10){
            input = input / 10;
            index = index * 10;
        }
        return  index;
    }
}
