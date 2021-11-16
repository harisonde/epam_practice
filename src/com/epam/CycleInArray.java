package com.epam;

public class CycleInArray {

    public static void main(String[] args){
        int[] input = new int[]{1, 2, 1, 3, 4, 8};

        boolean isCycleExists = solve(input);

        System.out.println("Is Cycle exists in array 1, 2, 1, 3, 4, 8 -> " + isCycleExists);

        isCycleExists = solve(new int[]{1, 2, 3, 4, 8});

        System.out.println("Is Cycle exists in array 1, 2, 3, 4, 8 -> " + isCycleExists);
    }

    private static boolean solve(int[] input){

       int fastIndex=0;
       int slowIndex=0;

       while(fastIndex < input.length && slowIndex < input.length) {

           fastIndex = input[fastIndex];

           if (fastIndex < input.length) {
               fastIndex = input[fastIndex];

               if (slowIndex == fastIndex) {
                   System.out.println("Cycle exists");
                   return true;
               }

               slowIndex = input[slowIndex];
           }
       }

        return false;
    }
}
