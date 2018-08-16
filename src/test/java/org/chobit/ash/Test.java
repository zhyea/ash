package org.chobit.ash;

import java.util.TreeSet;

public class Test {

    public static void main(String[] args) {

        TreeSet<Integer> set = new TreeSet<>();

        set.add(6);
        set.add(2);
        set.add(9);
        set.add(12);
        set.add(5);
        set.add(4);
        set.add(6);
        set.add(18);

        System.out.println(set.first());
        System.out.println(set.last());

        System.out.println("C85A3DBF40634296BFD956AD033C2C6B".hashCode() % 9);
    }

}
