package com.epam;

import org.junit.platform.commons.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class StringPermutation {

    public static void main(String[] args) {

        List<String> possiblePermutations = new ArrayList<>();
        generatePossiblePermutations("ABC", "", possiblePermutations);

        possiblePermutations.forEach(System.out::println);
    }

    private static void generatePossiblePermutations(String input, String permutation,
                                                     List<String> possibleCombinations) {

        if (input.length() > 0) {
            for (int index = 0; index < input.length(); index++) {
                String val = String.valueOf(input.charAt(index));

                String currentPermutation = permutation + val;
                generatePossiblePermutations(input.substring(0, index) + input.substring(index + 1),
                        currentPermutation, possibleCombinations);
            }
        } else {
            if (StringUtils.isNotBlank(permutation)) {
                possibleCombinations.add(permutation);
            }
        }

    }
}
