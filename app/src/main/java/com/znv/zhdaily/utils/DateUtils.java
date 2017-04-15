package com.znv.zhdaily.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间格式化文字（相互转化）的工具类
 * Created by znv on 2017/4/9.
 */

public class DateUtils {

    public static final String ZH_DATE_PATTERN = "MM月dd日" + " " + "EEEE";

    public static Date str2date(String dateStr, String pattern) {
        Date date = null;
        try {
            date = new SimpleDateFormat(pattern, Locale.CHINA).parse(dateStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String date2str(Date date, String pattern) {

        return new SimpleDateFormat(pattern, Locale.CHINA).format(date);
    }

    public static boolean isToday(String dateStr,String pattern) {
        Date todayTime = Calendar.getInstance().getTime();

        return date2str(todayTime,pattern).equals(dateStr);
    }

}
