package bender.jeffrey;

import bender.jeffrey.sample.IncomparableClass;
import bender.jeffrey.sort.ArraySortUtils;
import java.util.Arrays;
import java.util.Comparator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jbender
 */
public class ArraySortTests {

    private String[] comparableArray;
    private IncomparableClass[] incomparableArray;
    private final Comparator<String> stringLengthCompare;
    private final Comparator<IncomparableClass> incomperableClassCompare;
    private final Comparator<String> allowNull;

    public ArraySortTests() {
        stringLengthCompare = Comparator.comparing(String::length);
        incomperableClassCompare = Comparator.comparing(IncomparableClass::getRank);
        allowNull = (s1, s2) -> 0;
    }

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
        comparableArray = new String[]{
            "WE",
            "HOLD",
            "THESE",
            "TRUTHS",
            "TO",
            "BE",
            "SELF",
            "EVIDENT",
            "THAT",
            "ALL",
            "MEN",
            "ARE",
            "CREATED",
            "EQUAL"
        };
        incomparableArray = new IncomparableClass[]{
            new IncomparableClass(2),
            new IncomparableClass(0),
            new IncomparableClass(2),
            new IncomparableClass(4),
            new IncomparableClass(5),
            new IncomparableClass(6),
            new IncomparableClass(1),
            new IncomparableClass(1),
            new IncomparableClass(1),
            new IncomparableClass(1)
        };
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void isSortedEmptyList() {
        String[] emptyString = new String[]{};
        IncomparableClass[] emptyIncomperable = new IncomparableClass[]{};
        assertTrue(ArraySortUtils.isSorted(emptyString, null));
        assertTrue(ArraySortUtils.isSorted(emptyString, stringLengthCompare));
        assertTrue(ArraySortUtils.isSorted(emptyIncomperable, null));
        assertTrue(ArraySortUtils.isSorted(emptyIncomperable, incomperableClassCompare));
    }

    @Test
    public void isSortedSingleElementList() {
        String[] singleString = new String[]{
            "Hello World!"
        };
        IncomparableClass[] singleIncomperable = new IncomparableClass[]{
            new IncomparableClass(0)
        };
        assertTrue(ArraySortUtils.isSorted(singleString, null));
        assertTrue(ArraySortUtils.isSorted(singleString, stringLengthCompare));
        assertTrue(ArraySortUtils.isSorted(singleIncomperable, null));
        assertTrue(ArraySortUtils.isSorted(singleIncomperable, incomperableClassCompare));
    }

    @Test
    public void isSortedOnUnsortedComperableTest() {
        assertFalse(ArraySortUtils.isSorted(comparableArray, null));
    }

    @Test
    public void isSortedOnUnsortedComparatorTest() {
        assertFalse(ArraySortUtils.isSorted(comparableArray, stringLengthCompare));
    }

    @Test
    public void isSortedOnSortedComperableTest() {
        Arrays.sort(comparableArray);
        assertTrue(ArraySortUtils.isSorted(comparableArray, null));
    }

    @Test
    public void isSortedOnSortedComparatorTest() {
        Arrays.sort(comparableArray, stringLengthCompare);
        assertTrue(ArraySortUtils.isSorted(comparableArray, stringLengthCompare));
    }

    @Test
    public void isSortedNullElementsTest() {
        String[] nullArray = new String[] {
            null,
            null,
            null,
            null
        };
        Arrays.sort(nullArray, allowNull);
        assertTrue(ArraySortUtils.isSorted(nullArray, allowNull));
        assertThrows(NullPointerException.class, () -> ArraySortUtils.isSorted(nullArray));
    }

    @Test
    public void isSortedIncomparableClassTest() {
        assertThrows(ClassCastException.class, () -> ArraySortUtils.isSorted(incomparableArray, null));
    }
}
