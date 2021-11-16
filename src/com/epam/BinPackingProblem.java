package com.epam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BinPackingProblem {
    private final int binCapacity;
    private List<Bin> bins;
    private final List<Integer> items;

    public BinPackingProblem(List<Integer> items, int binCapacity) {
        this.binCapacity = binCapacity;
        this.items = items;
        bins = new ArrayList<>();
    }

    public static void main(String[] args) {
        BinPackingProblem binPackingProblem = new BinPackingProblem(Arrays.asList(4, 8, 1, 4, 2, 1), 10);
        if (binPackingProblem.solve()) {
            binPackingProblem.bins.forEach(System.out::println);
        }

        System.out.println();
        binPackingProblem = new BinPackingProblem(Arrays.asList(9, 8, 2, 2, 5, 4), 10);
        if (binPackingProblem.solve1()) {
            binPackingProblem.bins.forEach(System.out::println);
        }
    }

    private boolean solve() {
        items.sort((item1, item2) -> item2 - item1);

        if (items.get(0) > binCapacity) {
            System.out.println("No Feasible solution exists");
            return false;
        }

        int binId = 0;
        int currentBin = 0;
        bins.add(new Bin(binCapacity, binId++));
        boolean isItemAdded = false;

        for (Integer item : items) {
            while (!isItemAdded) {
                if (currentBin >= bins.size()) {
                    bins.add(new Bin(binCapacity, binId++));
                }

                if (bins.get(currentBin).addItem(item)) {
                    isItemAdded = true;
                } else {
                    currentBin = currentBin + 1;
                }
            }
            isItemAdded = false;
            currentBin = 0;
        }
        return true;
    }

    private boolean solve1() {
        List<Integer> sortedItems = items.stream().sorted((it1, it2) -> it2 - it1).collect(Collectors.toList());

        if (sortedItems.get(0) > binCapacity) {
            System.out.println("No Feasible solution exists");
            return false;
        }

        int currentBean = 0;
        bins.add(new Bin(10, 1));

        for (Integer sortedItem : sortedItems) {
            boolean isAdded = false;
            while (!isAdded) {
                if (currentBean >= bins.size()) {
                    bins.add(new Bin(10, currentBean + 1));
                }

                if (bins.get(currentBean).addItem(sortedItem)) {
                    isAdded = true;
                } else {
                    currentBean = currentBean + 1;
                }
            }
            currentBean = 0;

        }

        return true;
    }
}

class Bin {
    int id;
    int capacity;
    int currentCapacity;
    List<Integer> items;

    Bin(int capacity, int id) {
        this.id = id;
        this.capacity = capacity;
        this.currentCapacity = 0;
        this.items = new ArrayList<>();
    }

    boolean addItem(int itemWeight) {
        if (itemWeight + currentCapacity > capacity) {
            return false;
        }
        items.add(itemWeight);
        currentCapacity = currentCapacity + itemWeight;
        return true;
    }

    public String toString() {
        StringBuilder val = new StringBuilder("Number of Available items in Bin " + this.id + " is");

        for (Integer item : items) {
            val.append(" ").append(item);
        }
        return val.toString();
    }
}