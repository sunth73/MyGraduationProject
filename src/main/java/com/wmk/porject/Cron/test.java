package com.wmk.porject.Cron;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sunth
 * @DateTime 2019-05-25 10:01
 * 描述
 */
public class test {
//    public static String merge1(List<String> list){
//        String result="";
//        List<String>list1=new ArrayList<>();
//        for(String chart:list){
//            String[] strs = chart.split(",");
//            for(int i=0;i<strs.length;i++){
//                list1.add(strs[i]);
//            }
//        }
//        return list1.stream().distinct().collect(Collectors.joining(","));
//    }
//    public static void main(String[] args) {
//        List<String> list=new ArrayList<>();
//        list.add("a,b,c,d");
//        list.add("a,b,e,f,g,h");
//        System.out.println(merge1(list));
//    }
//    public static void main1(String[] args) {
//        List<String> list = Arrays.asList("AA", "BB", "CC", "BB", "CC", "AA", "AA");
//        long l = list.stream().distinct().count();
//        System.out.println("No. of distinct elements:"+l);
//        String output = list.stream().distinct().collect(Collectors.joining(","));
//        System.out.println(output);
//    }
    public static String twoYearsTime(String fmt,int num) {
        Calendar cal =  Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);//yy  直接计算年数+num
        int month = cal.get(Calendar.MONTH) + 1;//MM
        int day = cal.get(Calendar.DATE)+num;//dd
        int hour = cal.get(Calendar.HOUR_OF_DAY);//HH
        int minute = cal.get(Calendar.MINUTE);//mm
        int second=cal.get(Calendar.SECOND);//ss

        if (fmt.indexOf("yyyy") != -1) {
            fmt = fmt.replaceAll("yyyy", String.valueOf(year).substring(0));
        }
        if (fmt.indexOf("MM") != -1) {
            fmt = fmt.replaceAll("MM", month < 10 ? "0" + String.valueOf(month)
                    : String.valueOf(month));
        }
        if (fmt.indexOf("dd") != -1) {
            fmt = fmt.replaceAll("dd", day < 10 ? "0" + String.valueOf(day)
                    : String.valueOf(day));
        }
        if(fmt.indexOf("HH")!=-1){
            fmt = fmt.replaceAll("HH", hour<10 ? "0" + String.valueOf(hour):String.valueOf(hour));
        }
        if (fmt.indexOf("mm") != -1) {
            fmt = fmt.replaceAll(
                    "mm",
                    minute < 10 ? "0" + String.valueOf(minute) : String
                            .valueOf(minute));
        }
        if (fmt.indexOf("ss") != -1) {
            fmt = fmt.replaceAll(
                    "ss",
                    second < 10 ? "0" + String.valueOf(second) : String
                            .valueOf(second));
        }
        return fmt;
    }

    public static void main(String[] args) {
        Calendar calendar2 = Calendar.getInstance();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        calendar2.add(Calendar.DATE, 1);
        String three_days_after = sdf2.format(calendar2.getTime());
        System.out.println(three_days_after);
    }

}
