package bender.jeffrey.sort.util;

import java.util.Comparator;

/**
 *
 * @author jbender
 */
public class ArraySortUtils {

    public static <T> boolean isSorted(T[] array, Comparator<T> comparator) {
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

    public static <T extends Comparable<? super T>> boolean isSorted(T[] array) {
        return isSorted(array, null);
    }
}
