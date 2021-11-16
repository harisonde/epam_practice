package com.epam;

import java.util.ArrayList;
import java.util.List;

public class BitVector {
    private static final List<Integer> bitvector = new ArrayList<>();

    public static void main(String[] args) {
        //addElement(1024);
        addElement(56);
        addElement(23);
        addElement(590);
        addElement(591);
        addElement(595);
        addElement(67);
        addElement(234);
        addElement(40);
        addElement(1089);

        if (verifyElementPresent(590)) {
            System.out.println("Element 590 was added to bit vector and verification was done correctly");
        }

        if (!verifyElementPresent(585)) {
            System.out.println("Element 585 was not added to bit vector and verification was done correctly");
        }

        if (verifyElementPresent(1089)) {
            System.out.println("Element 1089 was added to bit vector and verification was done correctly");
        }
    }

    private static void addElement(int element) {
        int elementIndex = getElementIndex(element);
        int elementPosInIndex = getElementPosition(element);

        initialiseElementIndexIfNotPresent(elementIndex);

        int elementValAtIndex = bitvector.get(elementIndex);

        elementValAtIndex = (1 << elementPosInIndex) | elementValAtIndex;
        bitvector.set(elementIndex, elementValAtIndex);

        System.out.println("Element value after setting " + elementValAtIndex);
    }

    private static boolean verifyElementPresent(int element) {
        int elementIndex = getElementIndex(element);

        if (bitvector.get(elementIndex) != 0) {
            int elementPosition = getElementPosition(element);
            int elementVal = bitvector.get(elementIndex);

            int result = (1 << elementPosition) & elementVal;

            return result != 0;
        }

        return false;
    }

    private static int getElementIndex(int element) {
        return element / 32;
    }

    private static int getElementPosition(int element) {
        return element % 32;
    }

    private static void initialiseElementIndexIfNotPresent(int elementIndex) {
        if (bitvector.size() < elementIndex + 1) {
            for (int index = bitvector.size(); index < elementIndex + 1; index++) {
                bitvector.add(index, 0);
            }
        }
    }
}
