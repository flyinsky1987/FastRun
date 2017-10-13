package com.tm.common.util;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

public class Tool {

	public static String real(String str) {
		// 字符串的空处理
		return (str != null) ? str : "";
	}

	public static String[] real(String str[]) {
		// 数组的空处理
		return (str != null) ? str : (new String[] {});
	}

	public static int real(int a) {
		// 数字的空处理
		return (a != 0) ? a : 0;
	}
	
	/**
	 * @param obj
	 * @return
	 * 空验证
	 */
	public static boolean isEmpty(Object obj){
		return (obj!=null&&obj.toString().trim().length()>0&&!obj.toString().toUpperCase().equals("NULL"))?false:true;
	}
	
	/**
	 * 将制定字符串，截取为len长度
	 */
	public static String sub(String str,int len){
		if(str==null){
			return str;
		}else{
			return (str.length()<len)?str:str.substring(0, len);
		}
	}
	/**
	 * Timestamp类型时间
	 */
	public static Timestamp getTimestamp(){
		return new Timestamp(new Date().getTime());
	}
	public void MD5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			System.out.println("result: " + buf.toString());// 32位的加密
			System.out.println(buf.toString().length());
		} catch (Exception e) {
			e.printStackTrace();
		} // }
	}

	public static String read(String key) {
		// 读取配置文件
		ResourceBundle resB = ResourceBundle.getBundle("default");
		return resB.getString(key);
	}

	@SuppressWarnings("deprecation")
	public static String getNowTime() {
		// 当前时间
		return new Date().toLocaleString();
	}

	public String getOnlyIdByTime() {
		// 时间戳+4位随机码
		long random = Math.round(Math.random() * 10000);
		return String.valueOf((new Date().getTime())) + String.valueOf(random);
	}

	public static String getOnlyByUUID() {
		// UUID值
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

	public static void main(String args[]){
		System.out.println("8667CCC57B2C4BC3830905CCCE1B8B85".length());
	}
	// 自定义下拉框
	public static String createSelect(String style, String name, String values,
			String options, String checked) {

		String str = "<select " + style + " name=\"" + name + "\">";
		String[] val = values.split(",");
		String[] opt = options.split(",");
		for (int i = 0; i < val.length; i++) {
			String v = val[i];
			if (v.equals(checked)) {
				// 选中
				str += "<option selected value=\"" + v + "\">" + opt[i]
						+ "</option>";
			} else {
				// 未选中
				str += "<option  value=\"" + v + "\">" + opt[i] + "</option>";
			}
		}
		return str + "</select>";
	}

	public static String createSelect(String name, String values,
			String options, String checked) {

		String str = "<select  name=\"" + name + "\"   id=\"" + name + "\">";
		String[] val = values.split(",");
		String[] opt = options.split(",");
		for (int i = 0; i < val.length; i++) {
			String v = val[i];
			if (v.equals(checked)) {
				// 选中
				str += "<option selected value=\"" + v + "\">" + opt[i]
						+ "</option>";
			} else {
				// 未选中
				str += "<option  value=\"" + v + "\">" + opt[i] + "</option>";
			}
		}
		return str + "</select>";
	}

	// 自定义radio单选框
	public static String createRadio(String name, String values,
			String options, String checked) {

		String str = "";
		String[] val = values.split(",");
		String[] opt = options.split(",");
		for (int i = 0; i < val.length; i++) {
			String v = val[i];
			if (v.equals(checked)) {
				str += "<input type=\"radio\" name=\"" + name+ "\"	 id=\"" + name + "\" checked=\"checked\" value=\"" + val[i] + "\">"+ opt[i];
			} else {
				str += "<input type=\"radio\" name=\"" + name + "\"   id=\"" + name + "\" value=\""+ val[i] + "\">" + opt[i];
			}
		}
		return str;
	}
	 
	/**
	 * 自定义复选框
	 * @param name 复选框名称
	 * @param values 复选框项目value数组
	 * @param shows 复选框项目显示shows数组
	 * @param checkeds 选中项数组
	 * @return
	 */
	public static String createCheckbox(String name,String values,String options,String checkeds){
		String str = "";
		String[] val = values.split(",");
		String[] opt = options.split(",");
		for (int i = 0; i < val.length; i++) {
			String v = val[i];
			if (checkeds.contains(v)) {
				str += "<p><input type=\"checkbox\" name=\"" + name
						+ "\" checked value=\"" + val[i] + "\">"
						+ opt[i]+"</p>";
			} else {
				str += "<p><input type=\"checkbox\" name=\"" + name + "\"  value=\""
						+ val[i] + "\">" + opt[i]+"</p>";
			}
		}
		return str;
	}
	public static String getDate() {
		Calendar cal = Calendar.getInstance();
		// 使用日历类
		int year = cal.get(Calendar.YEAR);
		// 得到年
		int month = cal.get(Calendar.MONTH) + 1;
		// 得到月，因为从0开始的，所以要加1
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return year + "-" + month + "-" + day;
	} 
	
	/**
	 * @param str
	 * @return
	 * 字符串是否包含中文
	 */
	public static boolean containChinese(String str) {
		boolean boo=false;
		for (int i = str.length(); --i >= 0;) {
			String b = str.substring(i, i + 1);
			boolean c = java.util.regex.Pattern.matches("[\u4E00-\u9FA5]", b);
			if (c) {
				boo=true;
				break;
			}
		}
		return boo;
	}
	/**
	 * textarea 最大字数限制
	 */
	public static String textareaLength(int count){
		return " title=\"最大限制"+count+"个字\" maxlength=\""+count+"\" onchange=\"this.value=this.value.substring(0, "+count+")\" onkeydown=\"this.value=this.value.substring(0, "+count+")\" onkeyup=\"this.value=this.value.substring(0, "+count+")\" ";
	}
}
