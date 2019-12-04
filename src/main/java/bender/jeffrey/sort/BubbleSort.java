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
    public static <T> void sort(T[] array, Comparator<? super T> comparator) {
        if(array.length <= 1) {
            return;
        }
        if(comparator == null) {
            comparator = SortUtils.getDefaultComparator();
        }
        boolean didSwap;
        do {
            didSwap = false;
            for(int i = 1; i < array.length; i++) {
                T o1 = array[i - 1];
                T o2 = array[i];
                if(comparator.compare(o1, o2) > 0) {
                    SortUtils.swap(array, i - 1, i);
                    didSwap = true;
                }
            }
        } while(didSwap);
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
        sort(array, null);
    }
}
