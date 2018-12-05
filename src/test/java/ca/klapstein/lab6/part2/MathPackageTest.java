package ca.klapstein.lab6.part2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MathPackageTest {

    @Test
    void constructor() {
        MathPackage mathPackage = new MathPackage();
        assertNotNull(mathPackage);
    }

    @Test
    void randomEmpty() {
        assertArrayEquals(new double[]{}, MathPackage.random(0, 0.0, 0.0));
        assertEquals(0, MathPackage.random(0, 0.0, 0.0).length);
    }

    @Test
    void random() {
        assertEquals(1, MathPackage.random(1, 0.0, 0.0).length);
        assertEquals(1, MathPackage.random(1, 1.0, 0.0).length);
        assertEquals(1, MathPackage.random(1, 0.0, 1.0).length);
        assertEquals(1, MathPackage.random(1, 1.0, 1.0).length);

        assertEquals(1, MathPackage.random(1, 1, 1).length);

        assertEquals(1, MathPackage.random(1, 100.0, 100.0).length);

        assertArrayEquals(new double[]{1.0}, MathPackage.random(1, 1.0, 1.0));
        assertArrayEquals(new double[]{0, 0, 0, 0, 0}, MathPackage.random(5, 0.0, 0.0));
    }

    @Test
    void randomValidateValues() {
        double[] randomDoubles = MathPackage.random(100000, -1000000000.000, 1000000000.000);
        for (double d : randomDoubles) {
            assertTrue(d >= -1000000000.000);
            assertTrue(d <= 1000000000.000);
        }

        double[] randomDoubles2 = MathPackage.random(100000, -10, 0);
        for (double d : randomDoubles2) {
            assertTrue(d >= -10);
            assertTrue(d <= 0);
        }
    }

    @Test
    void max() {
        assertEquals(1.0, MathPackage.max(new double[]{1.0}));
        assertEquals(0.5, MathPackage.max(new double[]{0.2, 0.5}));

        assertEquals(0.000000005, MathPackage.max(new double[]{0.000000002, 0.000000005}));

        assertEquals(5.0, MathPackage.max(new double[]{-0.4, 5, 3.0, 5.0, -2.1, -10050.123}));
    }

    @Test
    void maxNull() {
        assertEquals(Double.MIN_VALUE, MathPackage.max(new double[]{}));
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
                Arguments.of(50, new double[]{50, 100}),
                Arguments.of(0.2, new double[]{0.2, 0.5}),

                Arguments.of(-10050.123, new double[]{-0.4, 5, 3.0, 5.0, -2.1, -10050.123})
        );
    }

    private static Stream<Arguments> normalizeProvider() {
        return Stream.of(
                Arguments.of(new double[]{}, new double[]{}),
                Arguments.of(new double[]{0}, new double[]{0}),
                Arguments.of(new double[]{0, 0}, new double[]{0, 0}),
                Arguments.of(new double[]{1}, new double[]{1}),
                Arguments.of(new double[]{0, 1}, new double[]{0, 1}),
                Arguments.of(new double[]{1, 0}, new double[]{1, 0}),
                Arguments.of(new double[]{0.5, 0.5}, new double[]{1, 1}),
                Arguments.of(new double[]{0, 1}, new double[]{0, -1}),
                Arguments.of(new double[]{0, 1}, new double[]{50, 100}),
                Arguments.of(new double[]{1, 0}, new double[]{100, 50}),
                Arguments.of(new double[]{0.3, 1, 0}, new double[]{65, 100, 50}),
                Arguments.of(new double[]{1, 0, 0.3, 0.7}, new double[]{100, -100, -40, 40}),
                Arguments.of(new double[]{0.25, 0.25, 0.25, 0.25}, new double[]{1000000000, 1000000000, 1000000000, 1000000000})
        );
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
    @MethodSource("minProvider")
    void min(Number expected, double[] input) {
        assertEquals(expected.doubleValue(), MathPackage.min(input));
    }

    @ParameterizedTest(name = "{index} => expected={0} input={1}")
    @MethodSource("normalizeProvider")
    void normalizeNaN(double[] expected, double[] input) {
        assertArrayEquals(expected, MathPackage.normalize(input));
    }

    @ParameterizedTest(name = "{index} => expected={0} input={1}")
    @MethodSource("sumProvider")
    void sum(Number expected, double[] input) {
        assertEquals(expected.doubleValue(), MathPackage.sum(input));
    }

    @Test
    void stddev() {
        assertEquals(0, MathPackage.stddev(new double[]{0}));
        assertEquals(0.5, MathPackage.stddev(new double[]{0, 1}));
        assertEquals(0, MathPackage.stddev(new double[]{1, 1}));
        assertEquals(1, MathPackage.stddev(new double[]{-1, 1}));

        assertEquals(0.94280904158206, MathPackage.stddev(new double[]{-1, 1, 1}), 0.00000001);
        assertEquals(0.94280904158206, MathPackage.stddev(new double[]{1, -1, 1}), 0.00000001);
        assertEquals(0.94280904158206, MathPackage.stddev(new double[]{1, -1, -1}), 0.00000001);

        assertEquals(0, MathPackage.stddev(new double[]{1000000000}));
//        assertEquals(1000000000, MathPackage.stddev(new double[]{0, 1000000000}));
        assertEquals(0, MathPackage.stddev(new double[]{1000000000, 1000000000}));
        assertEquals(0, MathPackage.stddev(new double[]{1000000000, 1000000000, 1000000000, 1000000000, 1000000000}));
    }

    @Test
    void stddevNull() {
        assertEquals(Double.NaN, MathPackage.stddev(new double[]{}));
    }

    @Test
    void arrayAddNull() {
        assertArrayEquals(new double[]{}, MathPackage.arrayAdd(new double[]{}, new double[]{}));
    }

    @Test
    void arrayAddVaryLengths() {
        // originally caused error
        assertArrayEquals(new double[]{1}, MathPackage.arrayAdd(new double[]{1}, new double[]{}));
        assertArrayEquals(new double[]{1}, MathPackage.arrayAdd(new double[]{}, new double[]{1}));

        assertArrayEquals(new double[]{1, 1}, MathPackage.arrayAdd(new double[]{1}, new double[]{0, 1}));
        assertArrayEquals(new double[]{1, 1}, MathPackage.arrayAdd(new double[]{0, 1}, new double[]{1}));
    }

    @Test
    void arrayAdd() {
        assertArrayEquals(new double[]{0}, MathPackage.arrayAdd(new double[]{0}, new double[]{0}));
        assertArrayEquals(new double[]{0, 0}, MathPackage.arrayAdd(new double[]{0, 0}, new double[]{0, 0}));

        assertArrayEquals(new double[]{2}, MathPackage.arrayAdd(new double[]{1}, new double[]{1}));
        assertArrayEquals(new double[]{0}, MathPackage.arrayAdd(new double[]{1}, new double[]{-1}));

        assertArrayEquals(new double[]{2, 2}, MathPackage.arrayAdd(new double[]{1, 1}, new double[]{1, 1}));
        assertArrayEquals(new double[]{1, 1}, MathPackage.arrayAdd(new double[]{0, 0}, new double[]{1, 1}));
        assertArrayEquals(new double[]{-1, 1}, MathPackage.arrayAdd(new double[]{-2, -2}, new double[]{1, 3}));
    }

    @Test
    void arrayNegateNull() {
        assertArrayEquals(new double[]{}, MathPackage.arrayNegate(new double[]{}));
    }

    @Test
    void arrayNegate() {
        assertArrayEquals(new double[]{0}, MathPackage.arrayNegate(new double[]{0}));
        assertArrayEquals(new double[]{-1}, MathPackage.arrayNegate(new double[]{1}));

        assertArrayEquals(new double[]{1.5}, MathPackage.arrayNegate(new double[]{-1.5}));
        assertArrayEquals(new double[]{-1.5}, MathPackage.arrayNegate(new double[]{1.5}));

        assertArrayEquals(new double[]{1.5, 2.5}, MathPackage.arrayNegate(new double[]{-1.5, -2.5}));
        assertArrayEquals(new double[]{-1.5, -2.5}, MathPackage.arrayNegate(new double[]{1.5, 2.5}));

        assertArrayEquals(new double[]{1, 1, 1, 0, -1, -1, -1}, MathPackage.arrayNegate(new double[]{-1, -1, -1, 0, 1, 1, 1}));
        assertArrayEquals(new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, MathPackage.arrayNegate(new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));

        assertArrayEquals(new double[]{1000000000, 1000000000}, MathPackage.arrayNegate(new double[]{-1000000000, -1000000000}));
        assertArrayEquals(new double[]{-1000000000, -1000000000}, MathPackage.arrayNegate(new double[]{1000000000, 1000000000}));
    }
}