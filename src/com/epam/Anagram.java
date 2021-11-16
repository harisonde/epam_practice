package com.epam;

public class Anagram {
    static boolean isAnagram(String c, String d) {
        if (c.length() != d.length())
            return false;

        int count = 0;

        // Take sum of all characters of
        // first String
        for (int i = 0; i < c.length(); i++) {
            count = count + c.charAt(i);
        }

        // Subtract the Value of all the characters
        // of second String
        for (int i = 0; i < d.length(); i++) {
            count = count - d.charAt(i);
        }

        // If Count = 0 then they are anagram
        // If count > 0 or count < 0 then
        // they are not anagram
        return (count == 0);
    }

    // Driver code
    public static void main(String[] args) {
        String str1 = "beadb";
        String str2 = "eabbd";

        // Function call
        if (isAnagram1(str1, str2))
            System.out.print("The two strings are " +
                    "anagram of each other");
        else
            System.out.print("The two strings are not " +
                    "anagram of each other");
    }

    private static boolean isAnagram1(String str1, String str2) {

        if (str1 == null || str2 == null) {
            return false;
        }

        if (str1.length() != str2.length()) {
            return false;
        }

        int[] countArray = new int[26];

        for (int index = 0; index < str1.length(); index++) {
            int charAtIndex = str1.charAt(index) - 'a';

            countArray[charAtIndex] = countArray[charAtIndex] + 1;
        }

        for (int index = 0; index < str2.length(); index++) {
            int pos = str2.charAt(index) - 'a';
            int charCount = countArray[pos];

            if (--charCount < 0) {
                return false;
            }

            countArray[pos] = charCount;
        }

        return true;
    }
}
