package com.lky.project.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
        /*获取当前时间*/
        public String getNowTime(){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
            return df.format(new Date());
        }

        /*获取时间戳*/
        public String getTimeStamp(){
            DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            return sdf.format(new Date());
        }

}
