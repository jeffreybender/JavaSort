package dev.jeffreybender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import dev.jeffreybender.sample.IncomparableClass;
import dev.jeffreybender.sort.BubbleSort;
import dev.jeffreybender.sort.SortUtils;

/**
 *
 * @author jbender
 */
public class SortTests {

    private String[] comparableArray;
    private IncomparableClass[] incomparableArray;
    private final Comparator<String> stringLengthCompare;
    private final Comparator<IncomparableClass> incomparableClassCompare;

    private Method sortWithoutComparatorMethod;
    private Method sortWithComparatorMethod;

    private static final Class<?>[] sortWithComparatorMethodParameters = new Class[] { Object[].class,
            Comparator.class };

    private static final Class<?>[] sortWithOutComparatorMethodParameters = new Class[] { Comparable[].class };

    public SortTests() {
        stringLengthCompare = Comparator.comparing(String::length);
        incomparableClassCompare = Comparator.comparing(IncomparableClass::getRank);
    }

    @BeforeAll
    public static void setUpClass() throws Exception {

    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
        sortWithComparatorMethod = null;
        sortWithoutComparatorMethod = null;
        comparableArray = new String[] { "WE", "HOLD", "THESE", "TRUTHS", "TO", "BE", "SELF", "EVIDENT", "THAT", "ALL",
                "MEN", "ARE", "CREATED", "EQUAL" };
        incomparableArray = new IncomparableClass[] { new IncomparableClass(2), new IncomparableClass(0),
                new IncomparableClass(2), new IncomparableClass(4), new IncomparableClass(5), new IncomparableClass(6),
                new IncomparableClass(1), new IncomparableClass(1), new IncomparableClass(1),
                new IncomparableClass(1) };
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @ParameterizedTest
    @MethodSource("sortClasses")
    public void sortEmptyArrayTest(Class<?> sortClass) {
        try {
            String[] emptyComparable = new String[] {};
            IncomparableClass[] emptyIncomparable = new IncomparableClass[] {};
            sortWithoutComparatorMethod = getSortWithOutComparatorMethod(sortClass);
            sortWithComparatorMethod = getSortWithComparatorMethod(sortClass);
            sortWithoutComparatorMethod.invoke(null, new Object[] { emptyComparable });
            assertTrue(SortUtils.isSorted(emptyComparable));
            sortWithComparatorMethod.invoke(null, new Object[] { emptyComparable, stringLengthCompare });
            assertTrue(SortUtils.isSorted(emptyComparable, stringLengthCompare));
            sortWithComparatorMethod.invoke(null, new Object[] { emptyIncomparable, null });
            assertTrue(SortUtils.isSorted(emptyIncomparable, null));
            sortWithComparatorMethod.invoke(null, new Object[] { emptyIncomparable, incomparableClassCompare });
            assertTrue(SortUtils.isSorted(emptyIncomparable, incomparableClassCompare));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @MethodSource("sortClasses")
    public void sortSingleElementArrayTest(Class<?> sortClass) {
        try {
            String[] singleComparable = new String[] { "Hello World!" };
            IncomparableClass[] singleIncomparable = new IncomparableClass[] { new IncomparableClass(0) };
            sortWithoutComparatorMethod = getSortWithOutComparatorMethod(sortClass);
            sortWithComparatorMethod = getSortWithComparatorMethod(sortClass);
            sortWithoutComparatorMethod.invoke(null, new Object[] { singleComparable });
            assertTrue(SortUtils.isSorted(singleComparable));
            sortWithComparatorMethod.invoke(null, new Object[] { singleComparable, stringLengthCompare });
            assertTrue(SortUtils.isSorted(singleComparable, stringLengthCompare));
            sortWithComparatorMethod.invoke(null, new Object[] { singleIncomparable, null });
            assertTrue(SortUtils.isSorted(singleIncomparable, null));
            sortWithComparatorMethod.invoke(null, new Object[] { singleIncomparable, incomparableClassCompare });
            assertTrue(SortUtils.isSorted(singleIncomparable, incomparableClassCompare));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @MethodSource("sortClasses")
    public void sortComparableTest(Class<?> sortClass) {
        try {
            sortWithoutComparatorMethod = getSortWithOutComparatorMethod(sortClass);
            sortWithoutComparatorMethod.invoke(null, new Object[] { comparableArray });
            assertTrue(SortUtils.isSorted(comparableArray));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @MethodSource("sortClasses")
    public void sortComparableWithComparatorTest(Class<?> sortClass) {
        try {
            sortWithComparatorMethod = getSortWithComparatorMethod(sortClass);
            sortWithComparatorMethod.invoke(null, new Object[] { comparableArray, stringLengthCompare });
            assertTrue(SortUtils.isSorted(comparableArray, stringLengthCompare));
            assertFalse(SortUtils.isSorted(comparableArray));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @MethodSource("sortClasses")
    public void sortIncomparableWithComparatorTest(Class<?> sortClass) {
        try {
            sortWithComparatorMethod = getSortWithComparatorMethod(sortClass);
            sortWithComparatorMethod.invoke(null, new Object[] { incomparableArray, incomparableClassCompare });
            assertTrue(SortUtils.isSorted(incomparableArray, incomparableClassCompare));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @MethodSource("sortClasses")
    public void sortIncomparableClassCastExceptionTest(Class<?> sortClass) {
        try {
            sortWithComparatorMethod = getSortWithComparatorMethod(sortClass);
            assertThrows(InvocationTargetException.class,
                    () -> sortWithComparatorMethod.invoke(null, new Object[] { incomparableArray, null }));
            try {
                sortWithComparatorMethod.invoke(null, new Object[] { incomparableArray, null });
            } catch (InvocationTargetException e) {
                assertEquals(ClassCastException.class, e.getCause().getClass());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @MethodSource("sortClasses")
    public void sortNullElementsTest(Class<?> sortClass) {
        try {
            Comparator<String> allowNull = (s1, s2) -> 0;
            String[] nullArray = new String[] { null, null, null, null };
            sortWithComparatorMethod = getSortWithComparatorMethod(sortClass);
            sortWithComparatorMethod.invoke(null, new Object[] { nullArray, allowNull });
            assertTrue(SortUtils.isSorted(nullArray, allowNull));
            assertThrows(InvocationTargetException.class,
                    () -> sortWithComparatorMethod.invoke(null, new Object[] { nullArray, null }));
            try {
                sortWithComparatorMethod.invoke(null, new Object[] { nullArray, null });
            } catch (InvocationTargetException e) {
                assertEquals(NullPointerException.class, e.getCause().getClass());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Class<?>[] sortClasses() {
        return new Class<?>[] { BubbleSort.class };
    }

    private Method getSortWithComparatorMethod(Class<?> sortClass) throws NoSuchMethodException {
        return sortClass.getMethod("sort", sortWithComparatorMethodParameters);
    }

    private Method getSortWithOutComparatorMethod(Class<?> sortClass) throws NoSuchMethodException {
        return sortClass.getMethod("sort", sortWithOutComparatorMethodParameters);
    }
}