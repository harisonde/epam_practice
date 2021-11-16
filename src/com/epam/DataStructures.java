package com.epam;

import com.epam.model.Person;

import java.util.*;
import java.util.stream.Collectors;

public class DataStructures {

    public static void main(String... args) {
        treeMapOperations();
        treeSetOperations();
        hashmapOperations();
    }

    private static void treeMapOperations() {
        System.out.println("======= TreeMap Practice =========");

        TreeMap<Person, Integer> personsWithAge = new TreeMap<>(Comparator.comparingInt(Person::getAge));

        personsWithAge.put(new Person("abc", 27), 27);
        personsWithAge.put(new Person("def", 43), 43);
        personsWithAge.put(new Person("xyz", 10), 10);
        personsWithAge.put(new Person("mnc", 88), 88);
        personsWithAge.put(new Person("mno", 65), 65);

        System.out.println("Person with minimum age is " + personsWithAge.firstKey().getName());
        System.out.println("Person with Maximum age is " + personsWithAge.lastKey().getName());

        System.out.println("Immediate Person with age less than 68 is " + personsWithAge.floorKey(new Person("mno", 68)).getName());
        System.out.println("Immediate Person with age greater than 68 is " + personsWithAge.ceilingKey(new Person("mno", 68)).getName());

        System.out.println("All persons with age more than 30 are " + personsWithAge.tailMap(new Person("test", 30)));
        System.out.println("All persons with age less than 66 are " + personsWithAge.headMap(new Person("test", 66)));
    }

    private static void treeSetOperations() {
        System.out.println("======= TreeSet Practice =========");
        TreeSet<Person> persons = new TreeSet<>();

        persons.add(new Person("abc", 27));
        persons.add(new Person("def", 43));
        persons.add(new Person("def", 43));
        persons.add(new Person("xyz", 10));
        persons.add(new Person("mnc", 88));
        persons.add(new Person("mno", 65));

        System.out.println("Person with minimum age is " + persons.first().getName());
        System.out.println("Person with minimum age is " + persons.stream().min(Comparator.naturalOrder()).orElse(null));
        System.out.println("Person with Maximum age is " + persons.last().getName());
        System.out.println("All persons with age more than 30 are ");

        persons.tailSet(new Person("test", 30)).forEach(System.out::println);

        System.out.println("All persons with age less than 65 are ");
        persons.headSet(new Person("test", 65)).forEach(System.out::println);
    }

    private static void hashmapOperations() {
        System.out.println("++++++++++ Hashmap Operations testing ++++++++");

        Map<String, Integer> personsWithAge = new HashMap<>();
        personsWithAge.put("abc", 10);
        personsWithAge.put("xyz", 23);
        personsWithAge.put("yuy", 78);
        personsWithAge.put("der", 35);

        personsWithAge.forEach((key, val) -> System.out.println(key + " : " + val));

        System.out.println("++++++++++ Persons with age more than 30 ++++++++");
        personsWithAge.entrySet().stream()
                .filter(entry -> entry.getValue() > 30)
                .forEach(System.out::println);

    }
}



