package ca.klapstein.lab6.part2;

import org.junit.jupiter.api.Test;

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
        assertArrayEquals(new double[]{0,0,0,0,0}, MathPackage.random(5, 0.0, 0.0));

    }

    @Test
    void randomValidateValues() {
        double[] randomDoubles  =  MathPackage.random(100000, -1000000000.000, 1000000000.000);
        for (double d: randomDoubles){
            assertTrue(d >= -1000000000.000);
            assertTrue(d <= 1000000000.000);
        }

        double[] randomDoubles2  =  MathPackage.random(100000, -10, 0);
        for (double d: randomDoubles2){
            assertTrue(d >= -10);
            assertTrue(d <= 0);
        }
    }
    @Test
    void max() {
        assertEquals(1.0, MathPackage.max(new double[]{1.0}));
        assertEquals(0.5, MathPackage.max(new double[]{0.2,0.5}));
        assertEquals(5.0, MathPackage.max(new double[]{-0.4, 5, 3.0, 5.0, -2.1,-10050.123}));
    }

    @Test
    void maxNull() {
        assertEquals(Double.MIN_VALUE, MathPackage.max(new double[]{}));
    }

    @Test
    void min() {
        assertEquals(1.0, MathPackage.min(new double[]{1.0}));
        assertEquals(0.2, MathPackage.min(new double[]{0.2, 0.5}));
        assertEquals(-10050.123, MathPackage.min(new double[]{-0.4, 5, 3.0, 5.0, -2.1,-10050.123}));
    }

    @Test
    void minNull() {
        // unexpected should maybe set to min val?
        assertEquals(Double.MAX_VALUE, MathPackage.min(new double[]{}));
    }

    @Test
    void normalize() {
        assertArrayEquals(new double[]{}, MathPackage.normalize(new double[]{}));
        assertArrayEquals(new double[]{0}, MathPackage.normalize(new double[]{0}));
        assertArrayEquals(new double[]{0}, MathPackage.normalize(new double[]{0}));
        assertArrayEquals(new double[]{0.0}, MathPackage.normalize(new double[]{-0.6}));
        assertArrayEquals(new double[]{0, 0}, MathPackage.normalize(new double[]{0, 0}));
        assertArrayEquals(new double[]{0, 1}, MathPackage.normalize(new double[]{0.2, 0.6}));
        assertArrayEquals(new double[]{0}, MathPackage.normalize(new double[]{-0.6}));
        assertArrayEquals(new double[]{1, 0}, MathPackage.normalize(new double[]{100, 50}));
        assertArrayEquals(new double[]{0, 1}, MathPackage.normalize(new double[]{50, 100}));
        assertArrayEquals(new double[]{0.3, 1, 0}, MathPackage.normalize(new double[]{65, 100, 50}));
        assertArrayEquals(new double[]{1, 0, 0.3, 0.7}, MathPackage.normalize(new double[]{100, -100, -40, 40}));
        assertArrayEquals(new double[]{0.25, 0.25, 0.25, 0.25}, MathPackage.normalize(new double[]{1000000000, 1000000000, 1000000000, 1000000000}), 0.0001);
    }

//    // TODO: NOTE: OLD left for example
//    @Test
//    void normalizeNaN() {
//        // all lead to NaN issues
//        assertArrayEquals(new double[]{Double.NaN, Double.NaN}, MathPackage.normalize(new double[]{1, 1}));
//        assertArrayEquals(new double[]{Double.NaN}, MathPackage.normalize(new double[]{1}));
//        assertArrayEquals(new double[]{Double.NaN}, MathPackage.normalize(new double[]{0.4}));
//        assertArrayEquals(new double[]{Double.NaN}, MathPackage.normalize(new double[]{0.6}));
//        assertArrayEquals(new double[]{Double.NaN}, MathPackage.normalize(new double[]{0.1}));
//        assertArrayEquals(new double[]{Double.NaN}, MathPackage.normalize(new double[]{55}));
//        assertArrayEquals(new double[]{Double.NaN}, MathPackage.normalize(new double[]{100000}));
//        assertArrayEquals(new double[]{Double.NaN}, MathPackage.normalize(new double[]{10000.2320}));
//    }

    @Test
    void sum() {
        assertEquals(1, MathPackage.sum(new double[]{1}));
        assertEquals(0, MathPackage.sum(new double[]{0}));
        assertEquals(2, MathPackage.sum(new double[]{1, 1}));
        assertEquals(1.5, MathPackage.sum(new double[]{2, -0.5}));
        assertEquals(1.5, MathPackage.sum(new double[]{1, 0.5}));
        assertEquals(200000, MathPackage.sum(new double[]{100000, 100000}));
        assertEquals(0, MathPackage.sum(new double[]{1000000000, -1000000000}));
        assertEquals(2000000000, MathPackage.sum(new double[]{1000000000, 1000000000}));
        assertEquals(3, MathPackage.sum(new double[]{1, 1, 1}));
        assertEquals(0, MathPackage.sum(new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0}));
    }

    @Test
    void sumNull() {
        // unknown if expected
        assertEquals(0, MathPackage.sum(new double[]{}));
    }

    @Test
    void stddev() {
        assertEquals(0, MathPackage.stddev(new double[]{0}));
        assertEquals(0.5, MathPackage.stddev(new double[]{0,1}));
        assertEquals(0, MathPackage.stddev(new double[]{1,1}));
        assertEquals(1, MathPackage.stddev(new double[]{-1,1}));
        assertEquals(0.94280904158206, MathPackage.stddev(new double[]{-1,1,1}), 0.00000001);
        assertEquals(0.94280904158206, MathPackage.stddev(new double[]{1,-1,1}), 0.00000001);
        assertEquals(0.94280904158206, MathPackage.stddev(new double[]{1,-1,-1}), 0.00000001);
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
        assertArrayEquals(new double[]{1,1}, MathPackage.arrayAdd(new double[]{1}, new double[]{0,1}));
        assertArrayEquals(new double[]{1,1}, MathPackage.arrayAdd(new double[]{0,1}, new double[]{1}));
    }

    @Test
    void arrayAdd() {
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
        assertArrayEquals(new double[]{-1}, MathPackage.arrayNegate(new double[]{1}));
        assertArrayEquals(new double[]{0}, MathPackage.arrayNegate(new double[]{0}));
        assertArrayEquals(new double[]{1.5}, MathPackage.arrayNegate(new double[]{-1.5}));
        assertArrayEquals(new double[]{1.5, 2.5}, MathPackage.arrayNegate(new double[]{-1.5, -2.5}));
        assertArrayEquals(new double[]{-1.5, -2.5}, MathPackage.arrayNegate(new double[]{1.5, 2.5}));
        assertArrayEquals(new double[]{1, 1, 1, 0, -1, -1, -1}, MathPackage.arrayNegate(new double[]{-1, -1, -1, 0, 1, 1, 1}));
        assertArrayEquals(new double[]{0,0,0,0,0,0,0,0,0,0}, MathPackage.arrayNegate(new double[]{0,0,0,0,0,0,0,0,0,0}));
        assertArrayEquals(new double[]{1000000000, 1000000000}, MathPackage.arrayNegate(new double[]{-1000000000, -1000000000}));
        assertArrayEquals(new double[]{-1000000000, -1000000000}, MathPackage.arrayNegate(new double[]{1000000000, 1000000000}));
    }
}