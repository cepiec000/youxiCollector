package com.seven.collector.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
}
