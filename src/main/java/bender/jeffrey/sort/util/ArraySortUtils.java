package bender.jeffrey.sort.util;

import java.util.Comparator;

/**
 *
 * @author jbender
 */
public class ArraySortUtils {

    /**
     * 
     * Checks if an array is sorted.
     * Will return {@code true} if:
     * <ul>
     *  <li>{@code array} has a {@code length} of 0 - 1.</li>
     *  <li>If {@code comparator} is not {@code null} and {@code array} is sorted according to the comparison provided in {@code comparator}.</li>
     *  <li>If {@code comparator} is {@code null} and the class for type of objects in {@code array} implement {@link java.lang.Comparable} and {@code array} is sorted according to the comparison provided from {@link java.lang.Comparable#compareTo(T)} for the class of {@code <T>}.</li>
     * </ul>
     * 
     * @param <T> type of objects in {@code array}
     * @param array The array to check if it is sorted
     * @param comparator The comparator to be used to compare objects in the array to determine order.
     * @return {@code true} if the array is sorted. {@code false} if the array is not sorted.
     * @throws ClassCastException if the {@code comparator} is null and the class for {@code <T>} does not implement {@link java.lang.Comparable}
     */
    public static <T> boolean isSorted(T[] array, Comparator<? super T> comparator) {
        if (array.length > 1) {
            if (comparator == null) {
                comparator = (o1, o2) -> ((Comparable) o1).compareTo(o2);
            }
            for (int i = 1; i < array.length; i++) {
                T o1 = array[i - 1];
                T o2 = array[i];
                if (comparator.compare(o1, o2) > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 
     * Checks if an array is sorted.
     * Will return true if:
     * <ul>
     *  <li>{@code array} has a {@code length} of 0 - 1.</li>
     *  <li>If {@code array} is sorted according to the comparison provided from {@link java.lang.Comparable#compareTo(T)} for the class of {@code <T>}.</li>
     * </ul>
     * 
     * @param <T> type of objects in {@code array} whose class implements {@link java.lang.Comparable}
     * @param array The array to check if it is sorted
     * @return {@code true} if the array is sorted. {@code false} if the array is not sorted.
     */
    public static <T extends Comparable<? super T>> boolean isSorted(T[] array) {
        return isSorted(array, null);
    }
}