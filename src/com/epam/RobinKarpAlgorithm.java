package com.epam;

public class RobinKarpAlgorithm {

    public static void main(String[] args) {
        String text = "This text textTTTT needs to be text searched";
        String searchString = "text";

        System.out.println("Index of textTTTT is " + text.indexOf("textTTTT"));
        System.out.println("First Index of text is " + text.indexOf(searchString));
        System.out.println("Last Index of text is " + text.lastIndexOf(searchString));

        search(text, searchString);
    }

    private static void search(String text, String searchString) {

        if (text == null || searchString == null || searchString.length() > text.length()) {
            System.out.println("Search string is not found");
        } else {
            int searchStringHash = findHash(searchString, searchString.length());
            int textHash = findHash(text, searchString.length() - 1);

            for (int index = 0; index <= text.length() - searchString.length(); index++) {
                int lastIndexHashInSearchString = String.valueOf(text.charAt(index + searchString.length() - 1)).hashCode();
                textHash = textHash + lastIndexHashInSearchString;

                if (textHash == searchStringHash) {
                    if (isSameCharacters(text, searchString, index)) {
                        System.out.println("Search string is found at starting at " +
                                "" + index + " and Ending at " + (index + searchString.length() - 1));
                    }
                }

                textHash = textHash - String.valueOf(text.charAt(index)).hashCode();
            }
        }
    }

    private static boolean isSameCharacters(String text, String searchString, int index) {
        boolean isFound = true;
        int searchStringIndex = 0;
        for (int textIndex = index; textIndex < searchString.length(); textIndex++) {
            if (isFound && searchString.charAt(searchStringIndex++) != text.charAt(textIndex)) {
                isFound = false;
            }
        }
        return isFound;
    }

    private static int findHash(String input, int endIndex) {
        int hashValue = 0;
        if (endIndex > input.length()) {
            return -1;
        }

        for (int index = 0; index < endIndex; index++) {
            String data = String.valueOf(input.charAt(index));

            hashValue = hashValue + data.hashCode();
        }

        return hashValue;
    }
}
