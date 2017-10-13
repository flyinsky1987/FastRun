package com.tm.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyDate {
	/**
	 * 当前时间
	 */
	public static String getDateTime(){
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return from.format(new Date());  
	}
	/**
	 * 当前年月日
	 */
	public static String getDate(){
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd");
		return from.format(new Date());  
	}
	/**
	 * 当前年月
	 */
	public static String getYearMonth(){
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM");
		return from.format(new Date());  
	}
	/**
	 * 下一个月
	 */
	public static String getNextYearMonth(){
		SimpleDateFormat   sdf=new   SimpleDateFormat("yyyy-MM");  
        Calendar   calendar=Calendar.getInstance();  
        calendar.setTime(new   java.util.Date());    
        calendar.set(Calendar.MONDAY,calendar.get(Calendar.MONDAY)+1);   
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH));  
        return sdf.format(calendar.getTime());
	}
	/**
	 * 下一个月
	 */
	public static String getLastYearMonth(){
		SimpleDateFormat   sdf=new   SimpleDateFormat("yyyy-MM");  
        Calendar   calendar=Calendar.getInstance();  
        calendar.setTime(new   java.util.Date());     
        calendar.set(Calendar.MONDAY,calendar.get(Calendar.MONDAY)-1);   
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH));  
        return sdf.format(calendar.getTime());
	}
	 
	/**
	 * 当前年
	 */
	public static int getYear(){
		SimpleDateFormat from = new SimpleDateFormat("yyyy");
		return Integer.parseInt(from.format(new Date()));  
	}
	/**
	 * 当前月
	 */
	public static int getMonth(){
		SimpleDateFormat from = new SimpleDateFormat("M");
		return Integer.parseInt(from.format(new Date()));  
	}
	/**
	 * 字符串格式化为日期
	 */
	public static Date toDate(String date){
		Date d=new Date();
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			if(date!=null&&date.trim().length()>0)
			d= from.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	/**
	 * 格式化日期字符串，获的年月
	 */
	public static String getYearMonth(String date){
		Date d=new Date();
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(date!=null&&date.trim().length()>0)
			d= from.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getYearMonth(d);
	}
	/**
	 * 获取日期：三个月前的今天
	 */
	public static String  getLast3Month(){
		Date dNow = new Date(); //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(calendar.MONTH, -3); //设置为前3月
		dBefore = calendar.getTime(); //得到前3月的时间

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
		return sdf.format(dBefore); //格式化前3月的时间
		
	}
	/**
	 * 获取日期：六个月前的今天
	 */
	public static String getLast6Month(){
		Date dNow = new Date(); //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(calendar.MONTH, -6); //设置为前6月
		dBefore = calendar.getTime(); //得到前3月的时间

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
		return sdf.format(dBefore); //格式化前3月的时间
	}
	/**
	 * 日期格式化为年月
	 */
	public static String getYearMonth(Date date){ 
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM");
		return from.format(date);
	}  
	
	 
	/**
	 * 获取指定月的所有工作日
	 */
	public static List<String> getDaysInMonth(int year,int month){
		Date firstDate=getFisrtDayOfMonth(year,month);
		Date lastDate=getLastDayOfMonth(year,month);
		return findDates(firstDate,lastDate);
	}

	/**
	 * 获取本月第一天到今天的所有日期
	 */
	public static List<String> getDaysBeforeThisDay(){
		Date firstDate=getFisrtDayOfMonth(getYear(),getMonth());
		Date lastDate=new Date();
		List<String> list=findDates(firstDate,lastDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		list.add(sdf.format(new Date()));
		return list;
	}
  
    /**
     * 返回指定时间段内所有日期
     */
    @SuppressWarnings("unchecked")
	public static List<String> findDates(Date dBegin, Date dEnd) {  
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List lDate = new ArrayList();  
        lDate.add(sdf.format(dBegin));  
        Calendar calBegin = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间    
        calBegin.setTime(dBegin);  
        Calendar calEnd = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间    
        calEnd.setTime(dEnd);  
        // 测试此日期是否在指定日期之后    
        while (dEnd.after(calBegin.getTime())) {  
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量    
            calBegin.add(Calendar.DAY_OF_MONTH, 1);  
           
            String s=sdf.format(calBegin.getTime()); 
            lDate.add(s);  
        }  
        return lDate;  
    }  
   
    /**
     * 获取指定月第一天日期
     */
    public static Date getFisrtDayOfMonth(int year,int month)
    {
      Calendar cal = Calendar.getInstance();
      //设置年份
      cal.set(Calendar.YEAR,year);
      //设置月份
      cal.set(Calendar.MONTH, month-1);
      //获取某月最小天数
      int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
      //设置日历中月份的最小天数
      cal.set(Calendar.DAY_OF_MONTH, firstDay);
      
      return cal.getTime();
    }
    /**
     * 获取指定月最后一天日期
     */
    public static Date getLastDayOfMonth(int year,int month)
    {
      Calendar cal = Calendar.getInstance();
      //设置年份
      cal.set(Calendar.YEAR,year);
      //设置月份
      cal.set(Calendar.MONTH, month-1);
      //获取某月最小天数
      int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
      //设置日历中月份的最小天数
      cal.set(Calendar.DAY_OF_MONTH, maxDay);
      return cal.getTime();
    }
    /**
     * 当月最后一天
     */
    public static String getLastDayInThisMonth(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date date=getLastDayOfMonth(getYear(),getMonth());
    	return sdf.format(date);
    }
    /**
     * 当月最第一天
     */
    public static String getFirstDayInThisMonth(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date date=getFisrtDayOfMonth(getYear(),getMonth());
    	return sdf.format(date);
    }
}
