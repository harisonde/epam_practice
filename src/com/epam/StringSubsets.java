package com.epam;

import java.util.ArrayList;
import java.util.List;

public class StringSubsets {

    public static void main(String[] args) {

        List<String> possibleCombinations = new ArrayList<>();
        generateSubsets("ABC", "", possibleCombinations);

        possibleCombinations.forEach(System.out::println);
    }

    private static void generateSubsets(String input, String currentCombination, List<String> possibleCombinations) {

        for (int index = 0; index < input.length(); index++) {
            String val = String.valueOf(input.charAt(index));

            String combinationIncluding = currentCombination + val;

            if(!possibleCombinations.contains(combinationIncluding)){
                possibleCombinations.add(combinationIncluding);
            }

            if (index + 1 < input.length()) {
                generateSubsets(input.substring(index + 1),  combinationIncluding, possibleCombinations);
                generateSubsets(input.substring(index + 1),  currentCombination, possibleCombinations);
            }
        }

    }
}
