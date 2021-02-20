/**************************************************************************************** 
 Copyright © 2003-2012 hbasesoft Corporation. All rights reserved. Reproduction or       <br>
 transmission in whole or in part, in any form or by any means, electronic, mechanical <br>
 or otherwise, is prohibited without the prior written consent of the copyright owner. <br>
 ****************************************************************************************/
package com.bottle.excelhelper.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * <Description> <br>
 * 
 * @author hejiawen <br>
 * @version 1.0 <br>
 * @CreateDate 207/5/12 <br>
 * @see com.ningpai.excel.utils <br>
 */
public final class DateUtil {

    /**
     * Description: <br>
     * 
     * @author hejiawen <br>
     * @taskId <br>
     * @param dateStr <br>
     * @return <br>
     */
    public static Date string2Date(String dateStr) {
        if (CommonUtil.isEmpty(dateStr)) {
            return null;
        }
        dateStr = dateStr.trim();
        Date date = null;
        switch (dateStr.length()) {
            case 8:
                date = string2Date(dateStr, DateConstants.DATE_FORMAT_8);
                break;
            case 10:
                date = string2Date(dateStr,
                    dateStr.indexOf("/") == -1 ? DateConstants.DATE_FORMAT_10 : DateConstants.DATE_FORMAT_10_2);
                break;
            case 11:
                date = string2Date(dateStr, DateConstants.DATE_FORMAT_11);
                break;
            case 14:
                date = string2Date(dateStr, DateConstants.DATETIME_FORMAT_14);
                break;
            case 19:
                date = string2Date(dateStr,
                    dateStr.indexOf("/") == -1 ? DateConstants.DATETIME_FORMAT_19 : DateConstants.DATETIME_FORMAT_19_2);
                break;
            case 21:
                date = string2Date(dateStr, DateConstants.DATETIME_FORMAT_21);
                break;
            case 23:
                date = string2Date(dateStr,
                    dateStr.indexOf("/") == -1 ? DateConstants.DATETIME_FORMAT_23 : DateConstants.DATETIME_FORMAT_23_2);
                break;
            default:
                throw new IllegalArgumentException(dateStr + "不支持的时间格式");
        }
        return date;
    }

    /**
     * Description: <br>
     * 
     * @author hejiawen <br>
     * @taskId <br>
     * @param date <br>
     * @param format <br>
     * @return <br>
     */
    public static Date string2Date(String date, String format) {
        if (CommonUtil.isEmpty(format)) {
            throw new IllegalArgumentException("the date format string is null!");
        }
        DateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date.trim());
        }
        catch (ParseException e) {
            throw new IllegalArgumentException("the date string " + date + " is not matching format: " + format, e);
        }
    }

    /**
     * Description: <br>
     * 
     * @author hejiawen <br>
     * @taskId <br>
     * @param date <br>
     * @return <br>
     */
    public static String date2String(Date date) {
        return date2String(date, DateConstants.DATETIME_FORMAT_19);
    }

    /**
     * Description: <br>
     * 
     * @author hejiawen <br>
     * @taskId <br>
     * @param date <br>
     * @param format <br>
     * @return <br>
     */
    public static String date2String(Date date, String format) {
        String result = null;
        if (date != null) {
            DateFormat sdf = new SimpleDateFormat(format);
            result = sdf.format(date);
        }
        return result;
    }

    public static String getCurrentTimestamp() {
        return date2String(getCurrentDate(), DateConstants.DATETIME_FORMAT_14);
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static Date getCurrentDate() {
        return new Date();
    }

    

    /**
     * 获取开始日期到今天的间隔天数
     * 
     * @param startDate 开始时间
     * @return 相差天数
     */
    public static long daysBetween(Date startDate) {
        return daysBetween(startDate, getCurrentDate());
    }

    /**
     * 获取两个日期之间间隔的天数
     * 
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 相差天数
     */
    public static long daysBetween(Date startDate, Date endDate) {
        return (startDate.getTime() - endDate.getTime()) / (1000 * 3600 * 24);
    }
}
