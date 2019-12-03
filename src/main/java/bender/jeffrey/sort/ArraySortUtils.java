package bender.jeffrey.sort;

import java.util.Comparator;

/**
 *
 * @author jbender
 */
public class ArraySortUtils {

    public static <T> boolean isSorted(T[] array, Comparator<T> comparator) {
        if (array.length > 1) {
            if (comparator != null) {
                for (int i = 1; i < array.length; i++) {
                    T o1 = array[i - 1];
                    T o2 = array[i];
                    if (comparator.compare(o1, o2) > 0) {
                        return false;
                    }
                }
            } else {
                if (array[0] != null && array[0] instanceof Comparable) {
                    return isSorted((Comparable[]) array);
                } else {
                    throw new ClassCastException(array[0].getClass() + "cannot be cast as " + Comparable.class);
                }
            }
        }
        return true;
    }

    public static <T extends Comparable<? super T>> boolean isSorted(T[] array) {
        if (array.length > 1) {
            for (int i = 1; i < array.length; i++) {
                T o1 = array[i - 1];
                T o2 = array[i];
                if(o1.compareTo(o2) < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static <T> int compare(T o1, T o2, Comparator<T> comparator) {
        if (comparator != null) {
            return comparator.compare(o1, o2);
        } else if (o1 instanceof Comparable) {
            return ((Comparable) o1).compareTo(o2);
        }
        throw new ClassCastException();
    }
}
