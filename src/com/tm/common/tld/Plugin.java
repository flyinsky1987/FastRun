package com.tm.common.tld;

import com.tm.common.util.Tool;

/**
 * @author fuwei
 * 自定义控件
 */
public class Plugin { 
	/**
	 * 下拉框插件
	 * @param name		下拉框的name、id
	 * @param values	下拉框选项的值集合
	 * @param options	下拉框选项的显示集合
	 * @param checked	选中的value
	 * @return
	 */
	public static String select(String name,String values,String options,String checked){
		return Tool.createSelect(name, values, options, checked);
	}
	/**
	 * 单选按钮组
	 * @param name		下拉框的name、id
	 * @param values	下拉框选项的值集合
	 * @param options	下拉框选项的显示集合
	 * @param checked	选中的value
	 * @return
	 */
	public static String radio(String name,String values,String options,String checked){
		return Tool.createRadio(name, values, options, checked);
	}
}
