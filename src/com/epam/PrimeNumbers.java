package com.epam;

import java.util.ArrayList;
import java.util.List;

//Given a number N, the task is to print the prime numbers from 1 to N.
public class PrimeNumbers {
    public static void main(String[] args) {
        int n = 10;

        List<Integer> primeNumbers = solve(n);

        System.out.println("Prime numbers with in " + n + " are " );

        primeNumbers.forEach(number -> System.out.print(" " + number));
    }

    private static List<Integer> solve(int n) {
        List<Integer> primeNumbers = new ArrayList<>();

        if (n < 2) {
            return primeNumbers;
        }

        if (n == 2) {
            primeNumbers.add(2);
            return primeNumbers;
        }

        primeNumbers.add(2);
        primeNumbers.add(3);

        for (int index = 4; index <= n; index++) {
            if (isPrimeNumber(index)) {
                primeNumbers.add(index);
            }
        }
        return primeNumbers;
    }

    private static boolean isPrimeNumber(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number != i && number % i == 0) {
               return false;
            }
        }
        return true;
    }
}
