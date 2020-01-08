package com.ocean.utils;

public class StringUtils {


    /**
     * 判断字符串是否为空
     * null，空串，undefined 三种都是为空
     * @param s 字符串
     * @return 字符串是否为空
     */
    public static boolean isEmpty(String s) {
        if (s == null || s.trim().length() == 0 || s.equals(Constants.UNDEFINED)) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }
}
