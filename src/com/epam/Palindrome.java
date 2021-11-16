package com.epam;

public class Palindrome {
    public static void main(String[] args){
        String input = "redivider";
        if(verify(input)){
            System.out.println("Given String " +input+ " is palindrome");
        }else {
            System.out.println("Given String " +input+ " is not palindrome");
        }
    }

    private static boolean verify(String radar) {
        if (radar != null && radar.length() > 0) {
            int startIndex = 0, endIndex = radar.length() - 1;

            while (startIndex <= endIndex) {
                if (radar.charAt(startIndex++) != radar.charAt(endIndex--)) {
                    return false;
                }
            }
        }
        return true;
    }
}
