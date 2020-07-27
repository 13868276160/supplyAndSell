package com.senontech.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String stringToInt(String week) {
        if(week.equals("星期一")){
            return "1";
        }else if(week.equals("星期二")){
            return "2";
        }else if(week.equals("星期三")){
            return "3";
        }else if(week.equals("星期四")){
            return "4";
        }else if(week.equals("星期五")){
            return "5";
        }else if(week.equals("星期六")){
            return "6";
        }else if(week.equals("星期日")){
            return "7";
        }
        return null;
    }

    //通过周几获取星期数
    public static final Date toDate(String week) {
        int targetWeek= 0;
        if(week.equals("星期日")){
            targetWeek=1;
        }else if(week.equals("星期一")){
            targetWeek=2;
        }else if(week.equals("星期二")){
            targetWeek=3;
        }else if(week.equals("星期三")){
            targetWeek=4;
        }else if(week.equals("星期四")){
            targetWeek=5;
        }else if(week.equals("星期五")){
            targetWeek=6;
        }else if(week.equals("星期六")){
            targetWeek=7;
        }
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,+2);
        // 当前日期星期数
        int currWeek = c.get(Calendar.DAY_OF_WEEK);
        do {
            // 向后推一天，直到星期数与所给星期数相同
            c.add(Calendar.DAY_OF_MONTH, 1);
        } while (targetWeek != c.get(Calendar.DAY_OF_WEEK));
        return c.getTime();
    }


    //获取这周周一日期
    public static Date getWeekDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if(dayWeek==1){
            dayWeek = 8;
        }
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        Date mondayDate = cal.getTime();
        return mondayDate;
    }

    public static boolean sameDate(Date d1, Date d2){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        //fmt.setTimeZone(new TimeZone()); // 如果需要设置时间区域，可以在这里设置
        return fmt.format(d1).equals(fmt.format(d2));
    }


    public static Date addData(Date date,int num){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, num);

        return c.getTime();
    }


}
