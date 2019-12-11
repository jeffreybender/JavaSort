package dev.jeffreybender.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Utility class for sorting arrays using the Merge Sort algorithm. This class
 * cannot be instantiated.
 * 
 * @author jbender
 * @version 1.0
 */
public final class MergeSort {

    /**
     * {@code private} default constructor to prevent class from being instantiated.
     */
    private MergeSort() {
    }

    /**
     * Sorts {@code array} using the Merge Sort algorithm. If {@code comparator} is
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
        mergeSort(array, array.length, comparator);
    }

    /**
     * Sorts {@code array} using the Merge Sort algorithm using the
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

    private static <T> void mergeSort(T[] array, int endIndex, Comparator<T> comparator) {
        if (endIndex <= 1) {
            return;
        }
        int middleIndex = endIndex / 2;
        T[] left = Arrays.copyOfRange(array, 0, middleIndex);
        T[] right = Arrays.copyOfRange(array, middleIndex, endIndex);
        int leftStop = middleIndex;
        int rightStop = endIndex - middleIndex;
        mergeSort(left, middleIndex, comparator);
        mergeSort(right, endIndex - middleIndex, comparator);
        merge(array, left, leftStop, right, rightStop, comparator);
    }

    private static <T> void merge(T[] array, T[] left, int leftStop, T[] right, int rightStop,
            Comparator<T> comparator) {
        int leftIndex = 0;
        int rightIndex = 0;
        int arrayIndex = 0;
        while (leftIndex < leftStop && rightIndex < rightStop) {
            if (comparator.compare(left[leftIndex], right[rightIndex]) <= 0) {
                array[arrayIndex++] = left[leftIndex++];
            } else {
                array[arrayIndex++] = right[rightIndex++];
            }
        }
        while (leftIndex < leftStop) {
            array[arrayIndex++] = left[leftIndex++];
        }
        while (rightIndex < rightStop) {
            array[arrayIndex++] = right[rightIndex++];
        }
    }
}
