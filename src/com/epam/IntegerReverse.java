package com.epam;

public class IntegerReverse {
    public static void main(String[] args) {
        int input = 5678123;

        int result = reverse(input);
        result = reverse1(input);

        System.out.println("Integer reversal of " + input + " is " + result);
    }

    private static int reverse(int input) {
        int output = 0;
        while (input > 0) {
            int mod = input % 10;
            input = input / 10;
            output =  output * 10  + mod;
        }
        return output;
    }

    private static int reverse1(int input){
        int output = 0;
        while(input > 0){
            int mod = input % 10;
            input = input / 10;
            output = output * 10 + mod;
        }

        return output;
    }
}
