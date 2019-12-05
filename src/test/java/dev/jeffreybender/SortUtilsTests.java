package dev.jeffreybender;

import dev.jeffreybender.sample.IncomparableClass;
import dev.jeffreybender.sort.SortUtils;
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
public class SortUtilsTests {

    private String[] comparableArray;
    private IncomparableClass[] incomparableArray;
    private final Comparator<String> stringLengthCompare;
    private final Comparator<IncomparableClass> incomperableClassCompare;
    private final Comparator<String> allowNull;

    public SortUtilsTests() {
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

    @Test
    public void isSortedEmptyList() {
        String[] emptyString = new String[] {};
        IncomparableClass[] emptyIncomperable = new IncomparableClass[] {};
        assertTrue(SortUtils.isSorted(emptyString, null));
        assertTrue(SortUtils.isSorted(emptyString, stringLengthCompare));
        assertTrue(SortUtils.isSorted(emptyIncomperable, null));
        assertTrue(SortUtils.isSorted(emptyIncomperable, incomperableClassCompare));
    }

    @Test
    public void isSortedSingleElementList() {
        String[] singleString = new String[] { "Hello World!" };
        IncomparableClass[] singleIncomperable = new IncomparableClass[] { new IncomparableClass(0) };
        assertTrue(SortUtils.isSorted(singleString, null));
        assertTrue(SortUtils.isSorted(singleString, stringLengthCompare));
        assertTrue(SortUtils.isSorted(singleIncomperable, null));
        assertTrue(SortUtils.isSorted(singleIncomperable, incomperableClassCompare));
    }

    @Test
    public void isSortedOnUnsortedComperableTest() {
        assertFalse(SortUtils.isSorted(comparableArray, null));
    }

    @Test
    public void isSortedOnUnsortedComparatorTest() {
        assertFalse(SortUtils.isSorted(comparableArray, stringLengthCompare));
    }

    @Test
    public void isSortedOnSortedComperableTest() {
        Arrays.sort(comparableArray);
        assertTrue(SortUtils.isSorted(comparableArray, null));
    }

    @Test
    public void isSortedOnSortedComparatorTest() {
        Arrays.sort(comparableArray, stringLengthCompare);
        assertTrue(SortUtils.isSorted(comparableArray, stringLengthCompare));
    }

    @Test
    public void isSortedNullElementsTest() {
        String[] nullArray = new String[] { null, null, null, null };
        Arrays.sort(nullArray, allowNull);
        assertTrue(SortUtils.isSorted(nullArray, allowNull));
        assertThrows(NullPointerException.class, () -> SortUtils.isSorted(nullArray));
    }

    @Test
    public void isSortedIncomparableClassTest() {
        assertThrows(ClassCastException.class, () -> SortUtils.isSorted(incomparableArray, null));
    }
}
