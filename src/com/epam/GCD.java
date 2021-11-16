package com.epam;

public class GCD {

    public static void main(String[] args){
        int a = 90, b = 48;

        int res = solve(a, b);

        System.out.println("GCD OF 90 AND 48 IS " + res);

    }

    private static int solve(int a, int b) {
        if(a == 0 ){
            return b;
        }

        if(b == 0){
            return a;
        }

        int rem = a % b;

        if(rem == 0){
            return b;
        }

        return solve(b, rem);
    }
}
