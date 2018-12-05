package ca.klapstein.lab6.part1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ArrayLibTest {

    private String[] getExampleArray() {
        return new String[]{"foo", "bar", "ree"};
    }

    private String[] getExampleEmptyArray() {
        return new String[]{};
    }


    @Test
    public void constructor() {
        ArrayLib arrayLib = new ArrayLib();
        assertNotNull(arrayLib);
    }

    @Test
    public void reverse() {
        String[] array = getExampleArray();
        String[] arrayReversed = ArrayLib.reverse(array, String.class);
        assertArrayEquals(new String[]{"ree", "bar", "foo"}, arrayReversed);
        assertArrayEquals(new String[]{}, ArrayLib.reverse(new String[]{}, String.class));
        assertArrayEquals(new String[]{null, null}, ArrayLib.reverse(new String[]{null, null}, String.class));
    }

    @Test
    public void uniqueAlready() {
        assertArrayEquals(getExampleArray(), ArrayLib.unique(getExampleArray(), String.class));
    }

    @Test
    public void uniqueNull() {
        // removes nulls unknown if expected
        assertThrows(AssertionError.class,
                () -> {
                    assertEquals(new String[]{null}, ArrayLib.unique(new String[]{null}, String.class));
                });
    }

    @Test
    public void uniqueNotAlready() {
        assertArrayEquals(getExampleArray(), ArrayLib.unique(new String[]{"foo", "bar", "ree", "foo"}, String.class));
    }

    @Test
    public void intersection() {
        assertArrayEquals(getExampleArray(), ArrayLib.intersection(getExampleArray(), getExampleArray(), String.class));
    }

    @Test
    public void intersectionNull() {
        // removes nulls unknown if expected
        assertThrows(AssertionError.class,
                () -> {
                    assertArrayEquals(new String[]{null, null}, ArrayLib.union(new String[]{null}, new String[]{null}, String.class));
                });
    }

    @Test
    public void intersectionDisjoint() {
        assertArrayEquals(new String[]{}, ArrayLib.intersection(new String[]{"oob", "joob"}, getExampleArray(), String.class));
        assertArrayEquals(new String[]{}, ArrayLib.intersection(new String[]{null}, getExampleArray(), String.class));
    }

    @Test
    public void union() {
        assertArrayEquals(new String[]{"foo", "bar", "ree", "oob", "joob"}, ArrayLib.union(getExampleArray(), new String[]{"oob", "joob"}, String.class));
    }

    @Test
    public void unionNull() {
        // removes nulls unknown if expected
        assertThrows(AssertionError.class,
                () -> {
                    assertArrayEquals(new String[]{"foo", "bar", "ree", null}, ArrayLib.union(getExampleArray(), new String[]{null, null}, String.class));
                });
    }

    @Test
    public void compactRedundant() {
        assertArrayEquals(getExampleArray(), ArrayLib.compact(getExampleArray(), String.class));
        assertArrayEquals(getExampleEmptyArray(), ArrayLib.compact(getExampleEmptyArray(), String.class));
    }

    @Test
    public void compact() {
        String[] compactableArray1 = new String[]{"foo", "bar", null, "ree"};
        assertArrayEquals(getExampleArray(), ArrayLib.compact(compactableArray1, String.class));

        String[] compactableArray2 = new String[]{"foo", "bar", "ree", null};
        assertArrayEquals(getExampleArray(), ArrayLib.compact(compactableArray2, String.class));

        String[] compactableArray3 = new String[]{null, "foo", "bar", "ree"};
        assertArrayEquals(getExampleArray(), ArrayLib.compact(compactableArray3, String.class));

        String[] compactableArray4 = new String[]{null, "foo", "bar", "ree", null};
        assertArrayEquals(getExampleArray(), ArrayLib.compact(compactableArray4, String.class));

        String[] compactableArray5 = new String[]{null, "foo", "bar", "ree", null, null};
        assertArrayEquals(getExampleArray(), ArrayLib.compact(compactableArray5, String.class));
    }

    @Test
    public void indexOf() {
        assertEquals(0, ArrayLib.indexOf(getExampleArray(), "foo"));
        assertEquals(1, ArrayLib.indexOf(getExampleArray(), "bar"));
        assertEquals(2, ArrayLib.indexOf(getExampleArray(), "ree"));
        assertEquals(-1, ArrayLib.indexOf(getExampleArray(), "rawr"));
        assertEquals(-1, ArrayLib.indexOf(getExampleArray(), null));
    }

    @Test
    public void indexOfEmpty() {
        assertEquals(-1, ArrayLib.indexOf(getExampleEmptyArray(), "rawr"));
        assertEquals(-1, ArrayLib.indexOf(getExampleEmptyArray(), null));
    }

    @Test
    public void contains() {
        assertTrue(ArrayLib.contains(getExampleArray(), "foo"));
        assertTrue(ArrayLib.contains(getExampleArray(), "bar"));
        assertTrue(ArrayLib.contains(getExampleArray(), "ree"));

        assertFalse(ArrayLib.contains(getExampleArray(), null));
        assertFalse(ArrayLib.contains(getExampleArray(), ""));
        assertFalse(ArrayLib.contains(getExampleArray(), "NONSUCH"));

        assertFalse(ArrayLib.contains(getExampleEmptyArray(), null));
        assertFalse(ArrayLib.contains(getExampleEmptyArray(), ""));
        assertFalse(ArrayLib.contains(getExampleEmptyArray(), "NONSUCH"));
    }

    /**
     * This original thrown a java.lang.AssertionError. As removing an element at index 0 would be skipped.
     */
    @Test
    public void withoutIndex0() {
        assertArrayEquals(new String[]{"bar", "ree"}, ArrayLib.without(getExampleArray(), String.class, "foo"));
    }

    @Test
    public void withoutIndex1() {
        assertArrayEquals(new String[]{"foo", "ree"}, ArrayLib.without(getExampleArray(), String.class, "bar"));
        assertArrayEquals(new String[]{"foo", "ree"}, ArrayLib.without(getExampleArray(), String.class, "bar", null));
        assertArrayEquals(new String[]{"foo", "ree"}, ArrayLib.without(getExampleArray(), String.class, null, "bar", null));
    }

    @Test
    public void withoutNonSuch() {
        assertArrayEquals(getExampleArray(), ArrayLib.without(getExampleArray(), String.class, "NONSUCH"));
    }

    @Test
    public void withoutNull() {
        // throws java.lang.NullPointerException unknown if expected
        // fixed
        assertArrayEquals(getExampleArray(), ArrayLib.without(getExampleArray(), String.class));
    }
}