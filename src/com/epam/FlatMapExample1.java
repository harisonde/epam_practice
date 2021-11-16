package com.epam;

import com.epam.model.Developer;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FlatMapExample1 {
    public static void main(String[] args) {

        Developer o1 = new Developer();
        o1.setName("mkyong");
        o1.addBook("Java 8 in Action");
        o1.addBook("Spring Boot in Action");
        o1.addBook("Effective Java (3nd Edition)");

        Developer o2 = new Developer();
        o2.setName("zilap");
        o2.addBook("Learning Python, 5th Edition");
        o2.addBook("Effective Java (3nd Edition)");

        List<Developer> developers = Arrays.asList(o1, o2);

        Set<String> filteredBooks = developers
                .stream()
                .flatMap(developer -> developer.getBook().stream())
                .filter(book -> !book.toLowerCase().contains("python"))
                .collect(Collectors.toSet());

        System.out.println("Filtered book list is " + filteredBooks);
    }
}
