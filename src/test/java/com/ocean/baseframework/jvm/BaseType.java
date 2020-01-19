package com.ocean.baseframework.jvm;

/**
 * 基本类型传参机制
 *
 * 由于基本类型的值在虚拟机中存放位置是
 */
public class BaseType {
    public static void main(String[] args) {

        Integer n = new Integer(10);
        Integer m = new Integer(20);
        swap(n, m);
        System.out.println(n);
        System.out.println(m);

        String s1 = "String-first";
        String s2 = "String-second";
        swapString(s1, s2);
        System.out.println(s1);
        System.out.println(s2);

    }

    public static void swap(Integer n, Integer m) {
        Integer temp = n;
        n = m;
        m = temp;

        System.out.println("n:" + n + "; m:" + m);
    }
    public static void swapString(String n, String m) {
        String temp = n;
        n = m;
        m = temp;

        System.out.println("n:" + n + "; m:" + m);
    }
}
