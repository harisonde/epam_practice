package com.epam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaRegExpressions {

    public static void main(String[] args){

        String text = "Hi, How are you? My salary is a89899. is it enough?";

        Pattern pattern = Pattern.compile("\\b(a\\d{5})\\b");

        Matcher matcher = pattern.matcher(text);

        while(matcher.find()){
            System.out.println("Matched string found at index " + matcher.start() + " end at " + matcher.end());
        }

    }
}
