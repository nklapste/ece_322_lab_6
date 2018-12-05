package ca.klapstein.lab6.part2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CommitOriginalTest {

    @Test
    void constructor() {
        CommitOriginal CommitOriginal = new CommitOriginal();
        assertNotNull(CommitOriginal);
    }

    @Test
    void randomEmpty() {
        assertArrayEquals(new double[]{}, CommitOriginal.random(0, 0.0, 0.0));
        assertEquals(0, CommitOriginal.random(0, 0.0, 0.0).length);
    }

    @Test
    void random() {
        assertEquals(1, CommitOriginal.random(1, 0.0, 0.0).length);
        assertEquals(1, CommitOriginal.random(1, 1.0, 0.0).length);
        assertEquals(1, CommitOriginal.random(1, 0.0, 1.0).length);
        assertEquals(1, CommitOriginal.random(1, 1.0, 1.0).length);

        assertEquals(1, CommitOriginal.random(1, 1, 1).length);

        assertEquals(1, CommitOriginal.random(1, 100.0, 100.0).length);

        assertArrayEquals(new double[]{1.0}, CommitOriginal.random(1, 1.0, 1.0));
        assertArrayEquals(new double[]{0, 0, 0, 0, 0}, CommitOriginal.random(5, 0.0, 0.0));
    }

    @Test
    void randomValidateValues() {
        double[] randomDoubles = CommitOriginal.random(100000, -1000000000.000, 1000000000.000);
        for (double d : randomDoubles) {
            assertTrue(d >= -1000000000.000);
            assertTrue(d <= 1000000000.000);
        }

        double[] randomDoubles2 = CommitOriginal.random(100000, -10, 0);
        for (double d : randomDoubles2) {
            assertTrue(d >= -10);
            assertTrue(d <= 0);
        }
    }
    private static Stream<Arguments> maxProvider() {
        return Stream.of(
                Arguments.of(Double.MIN_VALUE, new double[]{}),

                // NEW: fail assert
                // fail assert
                Arguments.of(0, new double[]{0}),
                // NEW: fail assert
                // fail assert
                Arguments.of(0, new double[]{0, 0}),

                Arguments.of(1, new double[]{1}),
                Arguments.of(1, new double[]{0, 1}),
                Arguments.of(1, new double[]{1, 0}),
                Arguments.of(1, new double[]{1, 1}),
                // NEW: fail assert
                // fail assert
                Arguments.of(0, new double[]{0, -1}),
                // NEW: fail assert
                // fail assert
                Arguments.of(0, new double[]{-1, 0}),
                // NEW: fail assert
                // fail assert
                Arguments.of(-1, new double[]{-1, -1}),
                Arguments.of(1, new double[]{1, -1}),

                Arguments.of(0.5, new double[]{0.2, 0.5}),

                Arguments.of(100, new double[]{50, 100}),
                Arguments.of(100, new double[]{100, 50}),

                Arguments.of(Double.MAX_VALUE, new double[]{0, 1, 2, 3, 5, Double.MAX_VALUE}),
                Arguments.of(Double.MAX_VALUE, new double[]{Double.MIN_VALUE, Double.MAX_VALUE}),
                Arguments.of(Double.MIN_VALUE, new double[]{Double.MIN_VALUE}),

                Arguments.of(5.0, new double[]{-0.4, 5, 3.0, 5.0, -2.1, -10050.123}),

                Arguments.of(0.000000005, new double[]{0.000000002, 0.000000005})
            );
    }
    @ParameterizedTest(name = "{index} => expected={0} input={1}")
    @MethodSource("maxProvider")
    void max(Number expected, double[] input) {
        assertEquals(expected.doubleValue(), CommitOriginal.max(input));
    }

    private static Stream<Arguments> minProvider() {
        return Stream.of(
                Arguments.of(Double.MAX_VALUE, new double[]{}),  // unexpectedish

                Arguments.of(0, new double[]{0}),
                Arguments.of(0, new double[]{0, 0}),

                Arguments.of(1, new double[]{1}),

                Arguments.of(0, new double[]{0, 1}),
                Arguments.of(0, new double[]{1, 0}),
                Arguments.of(1, new double[]{1, 1}),

                Arguments.of(-1, new double[]{0, -1}),
                Arguments.of(-1, new double[]{-1, 0}),
                Arguments.of(-1, new double[]{-1, -1}),

                Arguments.of(50, new double[]{50, 100}),
                Arguments.of(0.2, new double[]{0.2, 0.5}),

                Arguments.of(-10050.123, new double[]{-0.4, 5, 3.0, 5.0, -2.1, -10050.123})
        );
    }
    @ParameterizedTest(name = "{index} => expected={0} input={1}")
    @MethodSource("minProvider")
    void min(Number expected, double[] input) {
        assertEquals(expected.doubleValue(), CommitOriginal.min(input));
    }

    private static Stream<Arguments> normalizeProvider() {
        return Stream.of(
                Arguments.of(new double[]{}, new double[]{}),

                Arguments.of(new double[]{0}, new double[]{0}),
                Arguments.of(new double[]{0, 0}, new double[]{0, 0}),

                // NEW: still fail
                // fail assert
                Arguments.of(new double[]{1}, new double[]{1}),

                Arguments.of(new double[]{0, 1}, new double[]{0, 1}),
                Arguments.of(new double[]{1, 0}, new double[]{1, 0}),

                // NEW: fail assert
                // still fails
                Arguments.of(new double[]{0.5, 0.5}, new double[]{1, 1}),
                // NEW: these pass now
                Arguments.of(new double[]{1, 0}, new double[]{0, -1}),

                Arguments.of(new double[]{0, 1}, new double[]{50, 100}),
                Arguments.of(new double[]{1, 0}, new double[]{100, 50}),

                Arguments.of(new double[]{0.3, 1, 0}, new double[]{65, 100, 50}),
                Arguments.of(new double[]{1, 0, 0.3, 0.7}, new double[]{100, -100, -40, 40}),

                // NEW: fail assert
                // assert fail
                Arguments.of(new double[]{0.25, 0.25, 0.25, 0.25}, new double[]{1000000000, 1000000000, 1000000000, 1000000000})
        );
    }
    @ParameterizedTest(name = "{index} => expected={0} input={1}")
    @MethodSource("normalizeProvider")
    void normalize(double[] expected, double[] input) {
        assertArrayEquals(expected, CommitOriginal.normalize(input));
    }

    private static Stream<Arguments> sumProvider() {
        return Stream.of(
                Arguments.of(0, new double[]{}),

                Arguments.of(0, new double[]{0}),
                Arguments.of(0, new double[]{0, 0}),
                Arguments.of(0, new double[]{0, 0, 0, 0, 0, 0, 0}),

                Arguments.of(1, new double[]{1}),
                Arguments.of(1, new double[]{0, 1}),
                Arguments.of(1, new double[]{1, 0}),
                Arguments.of(2, new double[]{1, 1}),
                Arguments.of(3, new double[]{1, 1, 1}),

                Arguments.of(-1, new double[]{0, -1}),
                Arguments.of(-2, new double[]{-1, -1}),
                Arguments.of(0, new double[]{1, -1}),

                Arguments.of(150, new double[]{50, 100}),

                Arguments.of(1.5, new double[]{2, -0.5}),
                Arguments.of(1.5, new double[]{1, 0.5}),

                Arguments.of(2000000000, new double[]{1000000000, 1000000000}),
                Arguments.of(0, new double[]{1000000000, -1000000000})
        );
    }
    @ParameterizedTest(name = "{index} => expected={0} input={1}")
    @MethodSource("sumProvider")
    void sum(Number expected, double[] input) {
        assertEquals(expected.doubleValue(), CommitOriginal.sum(input));
    }

    private static Stream<Arguments> stddevProvider() {
        return Stream.of(
                Arguments.of(Double.NaN, new double[]{}),

                Arguments.of(0, new double[]{0}),
                Arguments.of(0, new double[]{0, 0}),
                Arguments.of(0, new double[]{0, 0, 0, 0, 0, 0, 0}),

                Arguments.of(0, new double[]{1}),
                Arguments.of(0.5, new double[]{0, 1}),
                Arguments.of(0.5, new double[]{1, 0}),
                Arguments.of(0, new double[]{1, 1}),
                Arguments.of(0, new double[]{1, 1, 1}),

                Arguments.of(0.5, new double[]{-1, 0}),
                Arguments.of(0.5, new double[]{0, -1}),
                Arguments.of(0, new double[]{-1, -1}),
                Arguments.of(1, new double[]{1, -1}),

                Arguments.of(25, new double[]{50, 100}),
                Arguments.of(25, new double[]{100, 50}),

                Arguments.of(1.25, new double[]{2, -0.5}),
                Arguments.of(0.25, new double[]{1, 0.5}),

                Arguments.of(0, new double[]{1000000000}),
                Arguments.of(0, new double[]{1000000000, 1000000000}),
                Arguments.of(1000000000, new double[]{1000000000, -1000000000})
        );
    }
    @ParameterizedTest(name = "{index} => expected={0} input={1}")
    @MethodSource("stddevProvider")
    void stddev(Number expected, double[] input) {
        assertEquals(expected.doubleValue(), CommitOriginal.stddev(input));
    }

    private static Stream<Arguments> stddevImpreciseProvider() {
        return Stream.of(
                Arguments.of(Double.NaN, new double[]{}),

                Arguments.of(0.94280904158206, new double[]{1, -1, -1}),
                Arguments.of(0.94280904158206, new double[]{1, 1, -1}),
                Arguments.of(0.94280904158206, new double[]{1, -1, 1}),
                Arguments.of(0.94280904158206, new double[]{-1, -1, 1})
        );
    }
    @ParameterizedTest(name = "{index} => expected={0} input={1}")
    @MethodSource("stddevImpreciseProvider")
    void stddevImprecise(Number expected, double[] input){
        assertEquals(expected.doubleValue(), CommitOriginal.stddev(input), 0.00000001);

    }

    private static Stream<Arguments> arrayAddProvider() {
        return Stream.of(
                Arguments.of(new double[]{}, new double[]{}, new double[]{}),

                // NEW: still fail
                // fail assert
                Arguments.of(new double[]{0}, new double[]{}, new double[]{0}),
                // NEW: still fail
                // fail java.lang.ArrayIndexOutOfBoundsException
                Arguments.of(new double[]{0}, new double[]{0}, new double[]{}),

                // NEW: still fail
                // fail assert
                Arguments.of(new double[]{1}, new double[]{}, new double[]{1}),
                // NEW: still fail
                // fail java.lang.ArrayIndexOutOfBoundsException
                Arguments.of(new double[]{1}, new double[]{1}, new double[]{}),

                Arguments.of(new double[]{0}, new double[]{0}, new double[]{0}),
                // NEW: still fail
                // fail assert
                Arguments.of(new double[]{0, 0}, new double[]{0}, new double[]{0, 0}),
                Arguments.of(new double[]{0, 0}, new double[]{0, 0}, new double[]{0, 0}),

                Arguments.of(new double[]{1}, new double[]{0}, new double[]{1}),
                Arguments.of(new double[]{1}, new double[]{1}, new double[]{0}),

                // NEW: still fail
                // all fail assert
                Arguments.of(new double[]{0, 1}, new double[]{0}, new double[]{0, 1}),
                Arguments.of(new double[]{1, 0}, new double[]{0}, new double[]{1, 0}),
                Arguments.of(new double[]{1, 1}, new double[]{0}, new double[]{1, 1}),

                // all below pass
                Arguments.of(new double[]{0, 1}, new double[]{0, 0}, new double[]{0, 1}),
                Arguments.of(new double[]{1, 0}, new double[]{0, 0}, new double[]{1, 0}),
                Arguments.of(new double[]{1, 1}, new double[]{0, 0}, new double[]{1, 1}),

                Arguments.of(new double[]{1, 2}, new double[]{0, 1}, new double[]{1, 1}),
                Arguments.of(new double[]{2, 1}, new double[]{1, 0}, new double[]{1, 1}),
                Arguments.of(new double[]{2, 2}, new double[]{1, 1}, new double[]{1, 1}),

                Arguments.of(new double[]{1, 0}, new double[]{0, -1}, new double[]{1, 1}),
                Arguments.of(new double[]{0, 1}, new double[]{-1, 0}, new double[]{1, 1}),
                Arguments.of(new double[]{0, 0}, new double[]{-1, -1}, new double[]{1, 1}),

                Arguments.of(new double[]{101, 20, 101}, new double[]{100, 10, 1}, new double[]{1,10,100}),
                Arguments.of(new double[]{-99, 0, 99}, new double[]{-100, -10, -1}, new double[]{1,10,100}),

                Arguments.of(new double[]{1}, new double[]{0.5}, new double[]{0.5})
                );
    }
    @ParameterizedTest(name = "{index} => expected={0} input1={1} input2={2}")
    @MethodSource("arrayAddProvider")
    void arrayAdd(double[] expected, double[] input1, double[] input2) {
        assertArrayEquals(expected, CommitOriginal.arrayAdd(input1, input2));
    }

    private static Stream<Arguments> negateProvider() {
        return Stream.of(
                // NEW: only passing test all others fail
                Arguments.of(new double[]{}, new double[]{}),

                // NEW: all fail assert
                Arguments.of(new double[]{0}, new double[]{0}),
                Arguments.of(new double[]{-1}, new double[]{1}),
                Arguments.of(new double[]{1}, new double[]{-1}),

                Arguments.of(new double[]{0, -1}, new double[]{0, 1}),
                Arguments.of(new double[]{-1, 0}, new double[]{1, 0}),
                Arguments.of(new double[]{-1, -1}, new double[]{1, 1}),

                Arguments.of(new double[]{0, 1}, new double[]{0, -1}),
                Arguments.of(new double[]{1, 0}, new double[]{-1, 0}),
                Arguments.of(new double[]{1, 1}, new double[]{-1, -1}),

                Arguments.of(new double[]{-1.5}, new double[]{1.5}),
                Arguments.of(new double[]{1.5}, new double[]{-1.5}),

                Arguments.of(new double[]{-0.5, -1.5}, new double[]{0.5, 1.5}),
                Arguments.of(new double[]{0.5, 1.5}, new double[]{-0.5, -1.5}),

                Arguments.of(new double[]{10, 100, 1000.05, 0, -10, -100, -1000.05}, new double[]{-10, -100, -1000.05, 0, 10, 100, 1000.05}),

                Arguments.of(new double[]{-1000000000, -1000000000}, new double[]{1000000000, 1000000000}),
                Arguments.of(new double[]{1000000000, 1000000000}, new double[]{-1000000000, -1000000000})
            );
    }
    @ParameterizedTest(name = "{index} => expected={0} input1={1}")
    @MethodSource("negateProvider")
    void arrayNegate(double[] expected, double[] input) {
        assertArrayEquals(expected, CommitOriginal.arrayNegate(input));
    }
}