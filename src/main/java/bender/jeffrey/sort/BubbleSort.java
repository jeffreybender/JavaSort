package bender.jeffrey.sort;

import java.util.Comparator;

/**
 * Utility class for sorting arrays with various algorithm options. This class
 * cannot be instantiated.
 * 
 * @author jbender
 * @version 1.0
 */
public final class BubbleSort {

    /**
     * {@code private} default constructor to prevent class from being instantiated.
     */
    private BubbleSort() {
    }

    /**
     * Sorts {@code array} using the Bubble Sort algorithm. If {@code comparator} is
     * {@code null}, the method will try to sort according using
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
    public static <T> void bubbleSort(T[] array, Comparator<? super T> comparator) {

    }

    /**
     * Sorts {@code array} using the Bubble Sort algorithm using the
     * {@link java.lang.Comparable#compareTo(T)} implementation of the class for
     * {@code <T>}.
     * 
     * @param <T>   The type of elements in {@code array} whose class implements
     *              {@link java.lang.Comparable}.
     * @param array The array to be sorted.
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] array) {
        bubbleSort(array, null);
    }
}
