package com.epam;

/*
Problem:
Given a string S consisting of N letters a and b. In one move you can replace one letter by the other (a by b or b by a).
Write a function solution that given such a string S, returns the minimum number of moves required to obtain a string containing
no instances of three identical consecutive letters. */

import java.util.ArrayList;
import java.util.List;

public class StringReplacement {

    public static void main(String[] args) {
        String input = "baaaaaa";

        System.out.println("Minimum number of moves required for input -> " + input + " is " + solve(input ));

        System.out.println();

        input = "baaabbaabbba";
        System.out.println("Minimum number of moves required for input ->" + input + " is " + solve(input));

        input = "baabab";
        System.out.println("Minimum number of moves required for input -> " + input + " is " + solve(input));
    }

    private static int solve(String input) {
        int numbersRequired = 0;
        int currentCount = 1;

        for (int index = 0; index < input.length() - 1; index++) {
            if (input.charAt(index) == input.charAt(index + 1)) {
                currentCount = currentCount + 1;
            } else {
                numbersRequired = numbersRequired + currentCount / 3;
                currentCount = 1;
            }
        }

        if(currentCount != 0){
            numbersRequired = numbersRequired + currentCount / 3;
        }

        return numbersRequired;
    }

    private static int solve(String input, int numberOfMoves, int currentIndex) {
        if (currentIndex + 2 >= input.length()) {
            return numberOfMoves;
        }

        if (requiresReplacement(input, currentIndex)) {
            String updateInput = input.substring(0, currentIndex) + getReplacementChar(input.charAt(currentIndex)) + input.substring(currentIndex + 1);

            if (isMandatoryToReplace(input, currentIndex)) {
                numberOfMoves = solve(updateInput, numberOfMoves + 1, currentIndex + 1);
            } else {
                numberOfMoves = Math.min(
                        solve(updateInput, numberOfMoves + 1, currentIndex + 1),
                        solve(input, numberOfMoves, currentIndex + 1));
            }
        } else {
            numberOfMoves = solve(input, numberOfMoves, currentIndex + 1);
        }

        return numberOfMoves;
    }

    private static boolean requiresReplacement(String input, int index) {
        char currentChar = input.charAt(index);

        if (currentChar == input.charAt(index + 1)
                && currentChar == input.charAt(index + 2)) {
            return true;
        }

        if (index > 0 && currentChar == input.charAt(index + 1) && currentChar == input.charAt(index - 1)) {
            return true;
        }

        return isMandatoryToReplace(input, index);
    }

    private static boolean isMandatoryToReplace(String input, int index) {
        char currentChar = input.charAt(index);
        return index > 1 && currentChar == input.charAt(index - 1) && currentChar == input.charAt(index - 2);
    }

    private static char getReplacementChar(char character){
        if('a' == character){
            return 'b';
        }

        if('b' == character){
            return 'a';
        }

        return character;
    }


}
