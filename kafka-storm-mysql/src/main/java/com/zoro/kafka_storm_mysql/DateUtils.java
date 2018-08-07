package com.zoro.kafka_storm_mysql;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created on 2018/8/6.
 *
 * @author dubber
 */
public class DateUtils {



    public static final String FMT_DATE = "yyyy-MM-dd";
    public static final String FMT_DATE_ = "yyyyMMdd";
    public static final String FMT_MINUTE = "yyyy-MM-dd HH:mm";
    public static final String FMT_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String FMT_SSS = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String FMT_MONTH = "yyyy-MM";
    public static final String FMT_SECOND_INT = "yyyyMMddHHmmss";

    public static String getString(Date date, String fmt) {
        try {
            return (new SimpleDateFormat(fmt)).format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getIntFmt(Date date) {
        return getString(date, FMT_DATE_);
    }

    public static String getString(Date date) {
        return getString(date, FMT_DATE);
    }

    public static final Date parseDate(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date parseDate(String strDate) {
        return parseDate(strDate, FMT_DATE);
    }

    /**
     * 获取两个日期之间的天数
     */
    public static int getDays(Date fromDate, Date toDate) throws ParseException {
        Calendar c = Calendar.getInstance();
        fromDate = parseDate(getString(fromDate));
        toDate = parseDate(getString(toDate));
        c.setTime(fromDate);
        Calendar cEnd = Calendar.getInstance();
        cEnd.setTime(toDate);
        return (int) ((cEnd.getTimeInMillis() - c.getTimeInMillis()) / (24 * 60 * 60 * 1000));
    }

    public static Date getPrevious(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.DAY_OF_MONTH, -1);
        return gc.getTime();
    }

    public static Date getNext(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.DAY_OF_MONTH, 1);
        return gc.getTime();
    }

    /**
     * n天后的date时间点
     * @param date
     * @param n  天数
     * @return
     */
    public static Date getNextN(Date date,int n) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.DAY_OF_MONTH, n);
        return gc.getTime();
    }

    public static void main(String[] args) throws ParseException {
        int days = DateUtils.getDays(DateUtils.parseDate("20160104", "yyyyMMdd"), new Date());
        System.out.println(days);
    }

}
