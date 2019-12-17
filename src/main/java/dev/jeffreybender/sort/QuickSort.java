package dev.jeffreybender.sort;

import java.util.Comparator;

/**
 * Utility class for sorting arrays using the Quick Sort algorithm. This class
 * cannot be instantiated.
 * 
 * @author jbender
 * @version 1.0
 */
public final class QuickSort {

    /**
     * {@code private} default constructor to prevent class from being instantiated.
     */
    private QuickSort() {
    }

    /**
     * Sorts {@code array} using the Quick Sort algorithm. If {@code comparator} is
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
        if (array.length <= 1) {
            return;
        }
        if (comparator == null) {
            comparator = SortUtils.getDefaultComparator();
        }
        quickSort(array, 0, array.length - 1, comparator);
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

    private static <T> void quickSort(T[] array, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int partitionIndex = partition(array, low, high, comparator);
            quickSort(array, low, partitionIndex, comparator);
            quickSort(array, partitionIndex + 1, high, comparator);
        }
    }

    private static <T> int partition(T[] array, int low, int high, Comparator<T> comparator) {
        T pivot = array[low + ((high - low) / 2)];
        int left = low - 1;
        int right = high + 1;
        while (left < right) {
            do {
                left++;
            } while (comparator.compare(array[left], pivot) < 0);
            do {
                right--;
            } while (comparator.compare(array[right], pivot) > 0);
            if (left >= right) {
                return right;
            }
            SortUtils.swap(array, left, right);
        }
        return 0;
    }
}
