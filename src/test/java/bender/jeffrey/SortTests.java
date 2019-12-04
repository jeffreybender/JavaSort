package bender.jeffrey;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import bender.jeffrey.sample.IncomparableClass;

import java.util.Comparator;

/**
 *
 * @author jbender
 */
public abstract class SortTests {

    private String[] comparableArray;
    private IncomparableClass[] incomparableArray;
    private final Comparator<String> stringLengthCompare;
    private final Comparator<IncomparableClass> incomperableClassCompare;

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
}