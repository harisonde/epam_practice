package com.epam;

import java.util.ArrayList;
import java.util.List;

public class Generics {
}

class TestGenerics<T> {
    T[] elements;

    TestGenerics() {

    }

    public static void main(String[] args){
        List<Object> objects = new ArrayList<>();
        objects.add(new Object());

        printList(objects);
        System.out.println(objects);

        List<Number> numbers = new ArrayList<>();
        numbers.add(100);
        numbers.add(100.34);

        System.out.println(numbers);


    }


    public T getElement() {
        return elements[0];
    }

    //It can only access methods of Object class
    public void wildCards(List<?> elements) {
        elements.get(0).notify();

        //Below line will fail to compile
        //elements.add(10);
    }

    //It can only access methods of Object and Number class
    public void wildCards1(List<? extends Number> elements) {
        elements.get(0).intValue();

        //Below line will not compile as its possible to add elements of type Integer, Float, Double etc
        //elements.add(10);
    }

    //It can only access methods of Object
    public void wildCards2(List<? super Integer> elements) {
        elements.get(0).notify();

        //Below line will not compile as its possible to add elements of type Integer, Float, Double etc
        //elements.add(10);
    }

    public static void printList(List<? super Integer> list) {
        for (int i = 0; i < 10; i++) {
            list.add(i);// successfully compiles
        }
    }

    //gives compilation error
    public static void printList1(List<? extends Number> list) {
        for (int i = 0; i < 10; i++) {
            //list.add(i);// gives compilation error
        }
    }

    void foo(List<?> i) {
        //i.set(0, i.get(0)); compilation error

        fooHelper(i);
    }

    // Helper method created so that the wildcard can be captured
    // through type inference.
    private <T> void fooHelper(List<T> l) {
        l.set(0, l.get(0));
    }
}

