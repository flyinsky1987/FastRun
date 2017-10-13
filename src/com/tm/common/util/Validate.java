package com.tm.common.util;

public class Validate {
	/**
	 * null类型，“”空数据验证
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj){
		return (obj==null||obj.toString().trim().length()<1)?true:false;
	}
	/**
	 * 截取掉最后一个字符
	 */
	public static String subLast(String str){
		if(isEmpty(str)){
			return str;
		}else{
			return str.substring(0,str.length()-1);
		}
	}
}
