package com.epam;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextFileModificationProgram {
    private static List<String> lines;

    public static void main (String [] args) throws IOException{
        File file = new File("C:\\Users\\Harikrishna_Sanjeeva\\Desktop\\Important.txt");

        lines = Files.readAllLines(file.toPath(), Charset.defaultCharset());

        changeValueOf("Georges", 1234); // the name and the value you want to modify
        Files.write(file.toPath(), changeValueOf("George", 1234));

        readFiles(file);

        readFileUsingBufferedReader();
        writeToFileUsingBufferedReader();

        readFileUsingScanner();

    }

    private static void readFiles(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            String nextLine = scanner.nextLine();

            if(nextLine.startsWith("Java")){
                System.out.println(nextLine);

                if(scanner.hasNextLine()){
                    System.out.println("The link for java related article is  " + scanner.nextLine());
                }
            }
        }
    }

    private static List<String> changeValueOf(String username, int newVal){
        List<String> newLines = new ArrayList<>();
        for(String line: lines){
            if(line.length() >= 80){
                String oldText = line.substring(72, 80);
                newLines.add(line.replace(oldText, ""));
               /* String [] vals = line.split(": ");
                newLines.add(vals[0]+": "+String.valueOf(newVal));*/
            }else{
                newLines.add(line);
            }

        }
        return newLines;
    }

    private static void readFileUsingBufferedReader() throws IOException {
        String path = "C:\\Users\\Harikrishna_Sanjeeva\\Desktop\\Important.txt";

        FileReader fileReader = new FileReader(new File(path));

        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    private static void writeToFileUsingBufferedReader() throws IOException {
        String path = "C:\\Users\\Harikrishna_Sanjeeva\\Desktop\\Important.txt";

        FileWriter fileWriter = new FileWriter(new File(path));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.append("TEXT APPENDED BY PROGRAM");
    }

    private static void readFileUsingScanner() throws FileNotFoundException {
        System.out.println("========== Started reading file using Scanner =============");

        String path = "C:\\Users\\Harikrishna_Sanjeeva\\Desktop\\Important.txt";
        File file = new File(path);

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
    }
}
