package bender.jeffrey;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.BeforeAll;

import bender.jeffrey.sample.IncomparableClass;
import bender.jeffrey.sort.BubbleSort;
import bender.jeffrey.sort.SortUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;
import java.util.Comparator;

/**
 *
 * @author jbender
 */
public class SortTests {

    private String[] comparableArray;
    private IncomparableClass[] incomparableArray;
    private final Comparator<String> stringLengthCompare;
    private final Comparator<IncomparableClass> incomperableClassCompare;

    private Method sortWithoutComparatorMethod;
    private Method sortWithComparatorMethod;

    private static final Class<?>[] sortWithComparatorMethodParameters = new Class[] {
        Object[].class,
        Comparator.class
    };

    private static final Class<?>[] sortWithOutComparatorMethodParameters = new Class[] {
        Comparable[].class
    };


    public SortTests() {
        stringLengthCompare = Comparator.comparing(String::length);
        incomperableClassCompare = Comparator.comparing(IncomparableClass::getRank);
    }

    @BeforeAll
    public static void setUpClass() throws Exception {

    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
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
    public void simpleParameterizedTest(Class<?> sortClass) {
        try {
            sortWithoutComparatorMethod = getSortWithOutComparatorMethod(sortClass);
            sortWithoutComparatorMethod.invoke(null, new Object[]{comparableArray});
            assertTrue(SortUtils.isSorted(comparableArray));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static Class<?>[] sortClasses() {
        return new Class<?>[] {
            BubbleSort.class
        };
    }

    private Method getSortWithComparatorMethod(Class<?> sortClass) throws NoSuchMethodException {
        return sortClass.getMethod("sort", sortWithComparatorMethodParameters);
    }

    private Method getSortWithOutComparatorMethod(Class<?> sortClass) throws NoSuchMethodException {
        return sortClass.getMethod("sort", sortWithOutComparatorMethodParameters);
    }
}