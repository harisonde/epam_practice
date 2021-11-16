package com.epam;

public class BitOperators {

    public static void main(String... args) {
        verifyBitOperators();

        System.out.println();

        if(isPowerOf2(256)){
            System.out.println("256 is number of 2");
        }

        if(!isPowerOf2(420)){
            System.out.println("420 is not number of 2");
        }

        System.out.println();
        System.out.println("Number of ones in 7 is " + countNumberOfOnes(7));
        System.out.println("Number of ones in 128 is " + countNumberOfOnes(128));

        System.out.println();
        System.out.println("Is 3rd bit set in number 7 ->  " + checkIthBit(7, 2));
        System.out.println("Is 1st bit set in number 5 ->  " + checkIthBit(5, 1));

        System.out.println();
        System.out.println("Largest Power of 2 less than or equal to 134 is ->  " + largestPowerOf2(134));
        System.out.println("Largest Power of 2 less than or equal to 64 is ->  " + largestPowerOf2(64));
        System.out.println("Largest Power of 2 less than or equal to 31 is ->  " + largestPowerOf2(31));

        System.out.println();
        possibleSubsets(new char[]{'A', 'B', 'C'}, 3);
    }

    private static void verifyBitOperators() {
        // 1 0 1 = 5, //1 1 0 = 6

        //1 0 1 0
        //      1

        int five = 5, six = 6;

        int four = (five & six);
        int seven = five | six;

        System.out.println(" Bitwise result of 5 AND 6 is  4 -> " + four);
        System.out.println(" Bitwise result of 5 OR 6 -> is " + seven);
        System.out.println(" Bitwise negation of 5 is -6 -> " + ~five);
        System.out.println(" Bitwise exclusive OR of 5 and 6 is " + (five ^ six));

        System.out.println("Bitwise left shift of 5 by one digit is 10 -> " + (5 << 1));
        System.out.println("Bitwise left shift of 5 by 2 digit is 20 -> " + (5 << 2));
        System.out.println("Bitwise right shift of 5 by 1 digit is 2 -> " + (5 >> 1));
        System.out.println("Bitwise right shift of 5 by 2 digit is 0 -> " + (5 >> 2));

    }

    /**
     * Properties for numbers which are powers of 2, is that they have one and only one bit set in their binary representation.
     * If the number is neither zero nor a power of two, it will have 1 in more than one place. So if x is a power of 2 then x & (x-1) will be 0.
     * @param number required
     * @return boolean
     */
    private static boolean isPowerOf2(int number){
        return number > 0 && (number & number-1) == 0 ;
    }

    private static int countNumberOfOnes(int number){
        int count = 0;

        while(number > 0){
            number = number & number -1;
            count++;
        }

        return count;
    }

    private static boolean checkIthBit(int number, int bitPos){
       return (number & (1 << bitPos)) != 0;
    }

    //Idea: Change all the bits which are at the right side of the most significant digit, to 1.
    private static int largestPowerOf2(int number){
        if(number > 1){
            number = number | (number >> 1);
            number = number | (number >> 2);
            number = number | (number >> 4);
            number = number | (number >> 8);
            number = number | (number >> 16);

            return (number + 1) /2 ;
        }

        return 0;
    }

    private static void possibleSubsets(char[] elements, int N)
    {
        for(int i = 0;i < (1 << N); ++i)
        {
            for(int j = 0;j < N;++j)
                if((i & (1 << j)) != 0){
                    System.out.println(" " + elements[j]);
                }
        }
    }
}