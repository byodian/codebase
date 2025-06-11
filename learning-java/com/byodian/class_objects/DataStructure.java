package com.byodian.class_objects;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DataStructure {
    private static int SIZE = 15;
    private int[] arrayOfInts = new int[SIZE];

    DataStructure() {
        for (int i = 0; i < SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }


    public void printEven() {
        EvenIterator evenIterator = new EvenIterator();
        while(evenIterator.hasNext()) {
            System.out.print(evenIterator.next() + " ");
        }
        System.out.println();
    }

    interface DataStructureIterator extends Iterator<Integer> {}
    class EvenIterator implements DataStructureIterator {
        private int nextIndex = 0;

        @Override
        public boolean hasNext() {
            return nextIndex < SIZE;
        }

        @Override
        public Integer next() {
            if (Objects.isNull(arrayOfInts[nextIndex])) {
                throw new NoSuchElementException();
            }

            Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);
            nextIndex += 2;
            return retValue;
        }
    }

    public static void main (String[] args) {
        DataStructure ds = new DataStructure();
        ds.printEven();
    }
}
