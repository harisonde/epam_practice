package com.epam;

import java.util.Arrays;

public class StringSplit {

    public static void main(String... args){
        String testWithMultipleLines = "ABC.txt 100B";

        testWithMultipleLines = testWithMultipleLines + " \b\n" + "DEF.png 200B";
        testWithMultipleLines = testWithMultipleLines + "\r\n" + "XYZ.jpeg 900B";

        System.out.println(testWithMultipleLines);
        System.out.println("-------------------");

        String[] splitByNewLine = testWithMultipleLines.split("\r\n");

        Arrays.stream(splitByNewLine).forEach(System.out::println);

        for(String line: splitByNewLine){
         String[] splitBySpace = line.split("\\s");

         String memoryUsage= splitBySpace[1].trim();
         int size = Integer.parseInt(memoryUsage.substring(0, memoryUsage.length()-1));

         String[] fileType = splitBySpace[0].split("\\.");

         System.out.println(fileType[0] + " File type is " + fileType[1] + " and has memory usage of " + size) ;
        }
    }
}

