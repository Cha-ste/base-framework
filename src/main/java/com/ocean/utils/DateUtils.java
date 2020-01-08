package com.ocean.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间、日期工具
 */
@Slf4j
public class DateUtils {


    /**
     * 指定时间的前或者后几天
     *
     * @param date 时间
     * @param days 步数
     * @return 前或者后几天
     */
    public static Date addDays(Date date, int days) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);

        return calendar.getTime();
    }

    /**
     * 根据所给字符串和格式，转换成Date对象
     *
     * @param s        日期字符串
     * @param patterns 字符串字符串格式【数组】
     * @return Date 对象
     */
    public static Date parseStringToDate(String s, String[] patterns) {
        if (StringUtils.isEmpty(s) || patterns == null || patterns.length == 0) {
            return null;
        }

        for (String pattern : patterns) {
            Date date = parseStringToDate(s, pattern, false);
            if (date != null) return date;
        }
        return null;
    }

    /**
     * 根据所给字符串和格式，转换成Date对象
     *
     * @param s       日期字符串
     * @param pattern 字符串字符串格式
     * @return Date 对象
     */
    public static Date parseStringToDate(String s, String pattern) {
        return parseStringToDate(s, pattern, true);
    }

    /**
     * 根据所给字符串和格式，转换成Date对象
     *
     * @param s             日期字符串
     * @param pattern       字符串字符串格式
     * @param showException 是否打印 log
     * @return Date 对象
     */
    public static Date parseStringToDate(String s, String pattern, Boolean showException) {
        if (!StringUtils.isEmpty(s)) {
            StringUtils.isEmpty(pattern);
        }

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = format.parse(s);
        } catch (ParseException e) {
            if (showException) {
                log.error("时间转换错误：" + e.getMessage());
            }
            return null;
        }

        return date;
    }

    public static void main(String[] args) {
//        System.out.println(addDays(new Date(), 2));
        String dateStr = "2020";
        String pattern = "yyyy-MM-dd";
        String[] patterns = new String[]{"yyyy-MM-dd", "yyyy"};
        System.out.println(parseStringToDate(dateStr, pattern));
    }

}
