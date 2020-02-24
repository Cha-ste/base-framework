package com.ocean.baseframework.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * java8 的StreamAPI 流操作
 */
public class StreamApiTest {
    static List<Integer> arr = new ArrayList<>(Arrays.asList(2, 3, 5, 6, 8, 4, 1, 7, 9, 0, 10));

    public static void main(String[] args) {

        Stream stream;

        stream = skip();
        System.out.println("----------------- skip -----------------");
        System.out.println(stream.collect(toList()));

        stream = sorted();
        System.out.println("----------------- sorted -----------------");
        System.out.println(stream.collect(toList()));

        stream = sorted().skip(2);
        System.out.println("----------------- sorted -> skip -----------------");
        System.out.println(stream.collect(toList()));

        List<Integer> result = arr.stream().map(num -> 10 - num).collect(toList());
        System.out.println("----------------- map -----------------");
        System.out.println(result);

        map();

    }

    public static Stream skip() {
        return arr.stream().filter(num -> num > 5).skip(2);
    }

    public static Stream sorted() {
        // 默认升序
        return arr.stream().sorted((a, b) -> a > b ? -1 : 1);
    }

    public static void map() {
        List<Integer> a = new ArrayList<>(Arrays.asList(1, 3));
        List<Integer> b = new ArrayList<>(Arrays.asList(2, 4, 6));
//        a.stream().map()
    }
}
