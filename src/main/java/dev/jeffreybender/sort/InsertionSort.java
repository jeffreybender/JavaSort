package dev.jeffreybender.sort;

import java.util.Comparator;

/**
 * Utility class for sorting arrays using the Insertion Sort algorithm. This
 * class cannot be instantiated.
 * 
 * @author jbender
 * @version 1.0
 */
public final class InsertionSort {

    /**
     * {@code private} default constructor to prevent class from being instantiated.
     */
    private InsertionSort() {
    }

    /**
     * Sorts {@code array} using the Insertion Sort algorithm. If {@code comparator}
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
        for (int index = 1; index < array.length; index++) {
            T objectAtIndex = array[index];
            int compareToIndex = index - 1;
            while (compareToIndex >= 0 && comparator.compare(array[compareToIndex], objectAtIndex) > 0) {
                array[compareToIndex + 1] = array[compareToIndex];
                compareToIndex--;
            }
            array[compareToIndex + 1] = objectAtIndex;
        }
    }

    /**
     * Sorts {@code array} using the Insertion Sort algorithm using the
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
