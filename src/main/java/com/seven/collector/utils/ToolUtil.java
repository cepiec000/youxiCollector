package com.seven.collector.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: TODO
 * @Author chendongdong
 * @Date 2020/5/15 14:28
 * @Version V1.0
 **/
public class ToolUtil {
    private final static String GET_GAMEKUID = "getGameKuId=\"(\\d*?)\"";

    public static String getGameKuId(String content) {
        Pattern pattern = Pattern.compile(GET_GAMEKUID);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static String getContent(List<String> cpList) {
        StringBuffer sb = new StringBuffer();
        if (cpList != null && cpList.size() > 0) {
            for (String s : cpList) {
                sb.append(s);
            }
            return sb.toString();
        }
        return "";
    }
    public static String convertTimeToString(Long time) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }
    public static Date convertTimeToDate(Long time){
        return new Date(time);
    }

    /**
     * 计算日期差天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int differentDays(Date startDate, Date endDate) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(startDate);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(endDate);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {
            //不同年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    //闰年
                    timeDistance += 366;
                } else {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else {
            //同年
            return day2 - day1;
        }
    }

}
