package dev.jeffreybender.sort;

import java.util.Comparator;

/**
 * Utility class for sorting arrays using the Selection Sort algorithm. This class
 * cannot be instantiated.
 * 
 * @author jbender
 * @version 1.0
 */
public final class SelectionSort {

    /**
     * {@code private} default constructor to prevent class from being instantiated.
     */
    private SelectionSort() {
    }

    /**
     * Sorts {@code array} using the Selection Sort algorithm. If {@code comparator}
     * is {@code null}, the method will try to sort according using
     * {@link java.lang.Comparable#compareTo(T)}.
     * 
     * @param <T>        The type of elements in {@code array}
     * @param array      The array to be sorted.
     * @param comparator The sort algorithm uses the comparator to determine on
     *                   which factors the elements should be sorted.
     * @throws ClassCastException if {@code comparator} is {@code null} and the
     *                            class for {@code <T>} does not implement
     *                            {@link java.lang.Comparable}.
     */
    public static <T> void sort(T[] array, Comparator<? super T> comparator) {
        if (array.length <= 1) {
            return;
        }
        if (comparator == null) {
            comparator = SortUtils.getDefaultComparator();
        }
        for (int left = 0; left < array.length; left++) {
            int minIndex = left;
            for (int checkIndex = left + 1; checkIndex < array.length; checkIndex++) {
                if (comparator.compare(array[minIndex], array[checkIndex]) > 0) {
                    minIndex = checkIndex;
                }
            }
            if(minIndex != left) {
                SortUtils.swap(array, left, minIndex);
            }
        }
    }

    /**
     * Sorts {@code array} using the Selection Sort algorithm using the
     * {@link java.lang.Comparable#compareTo(T)} implementation of the class for
     * {@code <T>}.
     * 
     * @param <T>   The type of elements in {@code array} whose class implements
     *              {@link java.lang.Comparable}.
     * @param array The array to be sorted.
     */
    public static <T extends Comparable<? super T>> void sort(T[] array) {
        sort(array, null);
    }
}
