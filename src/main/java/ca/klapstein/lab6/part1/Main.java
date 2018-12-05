package ca.klapstein.lab6.part1;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String[] list = new String[]{"One", "Two", "Three"};

        System.out.println("Reversed:");
        String[] reversed = ArrayLib.reverse(list, String.class);
        System.out.println(Arrays.toString(reversed));

        System.out.println("Unique:");
        list = new String[]{"One", "Two", "Three", "Two", "Four", "One"};
        String[] unique = ArrayLib.unique(list, String.class);
        System.out.println(Arrays.toString(unique));

        System.out.println("Intersect:");
        String[] list2 = new String[]{"One", "Three", "Seven", "Five"};
        String[] intersection = ArrayLib.intersection(list, list2, String.class);
        System.out.println(Arrays.toString(intersection));

        System.out.println("Union:");
        String[] union = ArrayLib.union(list, list2, String.class);
        System.out.println(Arrays.toString(union));

        System.out.println("Compact:");
        String[] list3 = new String[]{"Dog", "Cat", null, "Tail", "Bird", null};
        String[] compact = ArrayLib.compact(list3, String.class);
        System.out.println(Arrays.toString(compact));

        System.out.println("Index Of:");
        int index = ArrayLib.indexOf(list3, "Bird");
        System.out.println(index);

        System.out.println("Contains:");
        boolean contains = ArrayLib.contains(list, "Four");
        System.out.println(contains);

        System.out.println("Without:");
        String[] without = ArrayLib.without(list, String.class, "Two", "Five", "Four");
        System.out.println(Arrays.toString(without));
    }

}
