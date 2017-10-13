package com.tm.common.base;
  

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import com.tm.common.util.Parames;

public class BaseController extends Controller {
	 
	protected final Gson gson=new Gson(); 
	  
	/**
	 * 获取相对路径
	 */
	public String getpath() {
		return getRequest().getContextPath();
	} 
	/**
	 * 获取绝对路径
	 */
	public String getBasePath() {
		return getRequest().getScheme() + "://" + getRequest().getServerName() + ":" + getRequest().getServerPort();
	}
	 
	/**
	 * 获取客户端ip地址
	 */
	public String getRealAddress(HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	/**
	 * UUID随机主键
	 */
	public String UUID() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}
	/**
	 * Timestamp类型时间
	 */
	public Timestamp getTimestamp(){
		return new Timestamp(new Date().getTime());
	}
	/**
	 *遍历map，分别传回前台
	 */
	@SuppressWarnings("unchecked")
	public void setReqMap(HttpServletRequest request, Map<String, String> map) {

		if (map != null) {
			Set keySet = map.keySet();
			for (Object key : keySet) {
				try {
					request.setAttribute(key + "", map.get(key));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 前台所有参数转为 Map
	 */
	public Map<?,?> getReqMap(HttpServletRequest request) {
		return request.getParameterMap();
	}

	/**
	 * @param boo
	 * @param message
	 * @return
	 * 操作状态和描述
	 */
	public String toState(boolean boo,String message){
		Map<String,String> map=new HashMap<String,String>(); 
		map.put("state", boo+"");
		map.put("message", message);
		return gson.toJson(map);
	}
	/**
	 * @param obj	需要验证格式的字符串
	 * @return  对字符串进行合理性验证，并返回验证处理后的字符串
	 */
	public static String validateParames(Object obj){
		String[] regs=new String[]{"select","*","from","'","\"","\n","\r","delete","update","alert","#","$","(",")","="};
		if(obj!=null){
			String str=obj.toString();
			for(String reg:regs){
				if(str.contains(reg))
					return "";
			}
			return str;
		}
		return "";
	}
	/**
	 * 上传文件
	 */
	public String uploadFile(){
		UploadFile files = getFile("file",Parames.filePath);
		String lastType="";
		{
			String fileName=files.getFileName();
			if(!fileName.isEmpty()){
				lastType=fileName.substring(fileName.lastIndexOf(".",fileName.length()));
			}
		} 
		String fname=UUID()+lastType;;
		String thisFile=Parames.filePath+fname;	//linux下使用全路径（解决windows、linux下 File renameTo不一致的情况） 
		files.getFile().renameTo(new File(thisFile)); 
		
		return thisFile;
	}
}
