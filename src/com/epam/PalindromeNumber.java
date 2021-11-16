package com.epam;

public class PalindromeNumber {

    public static void main(String[] args){
        int input = 12321;

        boolean isPalindrome = solve(input);

        System.out.println("Does number " + input + " is Palindrome " + isPalindrome);

        input = 1451;
        isPalindrome = solve(input);

        System.out.println("Does number " + input + " is Palindrome " + isPalindrome);
    }

    private static boolean solve(int input) {
        int start = getStartIndex(input);
        int end = 1;

        while(start >= end) {
            int startNumber = (input / start) % 10;
            int endNumber = (input / end) % 10;

            if (startNumber != endNumber) {
                return false;
            } else {
                start = start / 10;
                end = end * 10;
            }
        }
        return true;
    }

    private static int getStartIndex(int input) {
        int index = 1;

        while(input > 10){
            input = input / 10;
            index = index * 10;
        }
        return  index;
    }
}
